/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date cr√©√©: 2013-05-03
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

import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exÈcution parallËle.
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
	 * DÈfinir le rÈcepteur de l'information reÁue dans la communication avec le serveur
	 * @param listener sera alert√© lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * D√©marre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * Arr√™te la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	
	/**
	 * Cr√©er le n√©cessaire pour la communication avec le serveur
	 */
	protected void creerCommunication(){		
		// CrÈe un fil d'exÈcution parallËle au fil courant,
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'execution parallele est lance");
				
				//la string obtenue par la mÈthode GET
				String sForme;
				
				Runtime rt = Runtime.getRuntime();
				rt.exec("java -jar ./ServeurForme.jar -port 10000");
				
				final int numPort = 10000;
				Socket socket = new Socket("localhost", numPort);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);				
				
				while(true){
					
					Thread.sleep(DELAI);
					out.println("GET");
					reader.readLine();
					sForme = reader.readLine();
					
 					//La m√©thode suivante alerte l'observateur 
					if(listener!=null)
					   firePropertyChange("ENVOIE-TEST", null, (Object) (sForme)); 
				}
			}
		};
		if(listener!=null)
		   threadComm.addPropertyChangeListener(listener); // La m√©thode "propertyChange" de ApplicationFormes sera donc appel√©e lorsque le SwinkWorker invoquera la m√©thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'ex√©cution parall√®le.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'ex√©cution parall√®le est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
