/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
 
/**
 * Cette classe repr�sente la fen�tre principale de l'application 
 * @authors Patrice Boucher, Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{
	
	private static final long serialVersionUID = -1210804336046370508L;
	private FenetreFormes fenetreFormes;

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(CommBase comm){

		MenuFenetre menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH); 
		fenetreFormes = new FenetreFormes();
		this.add(fenetreFormes, BorderLayout.SOUTH); // Ajoute la fenêtre de forme à la fenètre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //... à réviser selon le comportement que vous désirez ...
	}
	
	// Appel� lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		
		if(arg0.getPropertyName().equals("ENVOIE-TEST")){
			fenetreFormes.addForme((String) arg0.getNewValue());
			System.out.println(arg0.getNewValue());
		}
	}
}
