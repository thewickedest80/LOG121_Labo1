/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date crÃ©Ã©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {
	
	private final int DELAI = 1000;
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private boolean isActif = false;
	
	/**
	 * Constructeur
	 */
	public CommBase(){
	}
	
	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * @param listener sera alertÃ© lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * DÃ©marre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * ArrÃªte la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	
	/**
	 * Fonction qui lance et établit une connexion avec le serveur de formes. L'utilisateur
	 * sera demandé d'entrer les informations nécessaires.
	 */
	protected void creerCommunication(){		
		// Crée un fil d'exécution parallèle au fil courant,
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				
				//la string obtenue par la méthode GET
				String sForme;
				
				//obtenir le nom d'hote et # de port et les mettre dans un tableau
				String inputBox = JOptionPane.showInputDialog(
						"Entrez un nom d'hote et un # de port, séparés par \":\"", "localhost:10000");
				String[] nomConnexion = inputBox.split(":");
				int numPort = Integer.parseInt(nomConnexion[1]);
				
				//Lancer le serveur
				Runtime rt = Runtime.getRuntime();
				rt.exec("java -jar ./ServeurForme.jar -port "+numPort);
				
				System.out.println("Le fils d'execution parallele est lance");
				
				//Création du socket, du reader et du writer.
				Socket socket = new Socket(nomConnexion[0], numPort);				
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);				
				
				//Boucle pour exécuterdes requetes
				while(true){
					//intervalle entre chaque requete
					Thread.sleep(DELAI);
					//Requete
					out.println("GET");
					//Lire la ligne
					reader.readLine();
					sForme = reader.readLine();
					
 					//alerterl'observateur
					if(listener!=null)
					   firePropertyChange("ENVOIE-TEST", null, (Object) (sForme)); 
				}
			}
		};
		if(listener!=null)
		   threadComm.addPropertyChangeListener(listener); // La mÃ©thode "propertyChange" de ApplicationFormes sera donc appelÃ©e lorsque le SwinkWorker invoquera la mÃ©thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exÃ©cution parallÃ¨le.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'exécution en parallele est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
