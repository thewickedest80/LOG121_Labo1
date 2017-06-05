/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
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
 * Base d'une communication via un fil d'ex�cution parall�le.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * Arrête la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	
	/**
	 * Fonction qui lance et �tablit une connexion avec le serveur de formes. L'utilisateur
	 * sera demand� d'entrer les informations n�cessaires.
	 */
	protected void creerCommunication(){		
		// Cr�e un fil d'ex�cution parall�le au fil courant,
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				
				//la string obtenue par la m�thode GET
				String sForme;
				
				//obtenir le nom d'hote et # de port et les mettre dans un tableau
				String inputBox = JOptionPane.showInputDialog(
						"Entrez un nom d'hote et un # de port, s�par�s par \":\"", "localhost:10000");
				String[] nomConnexion = inputBox.split(":");
				int numPort = Integer.parseInt(nomConnexion[1]);
				
				//Lancer le serveur
				Runtime rt = Runtime.getRuntime();
				rt.exec("java -jar ./ServeurForme.jar -port "+numPort);
				
				System.out.println("Le fils d'execution parallele est lance");
				
				//Cr�ation du socket, du reader et du writer.
				Socket socket = new Socket(nomConnexion[0], numPort);				
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);				
				
				//Boucle pour ex�cuterdes requetes
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
		   threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'ex�cution en parallele est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
