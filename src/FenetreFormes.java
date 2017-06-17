/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JComponent;

/**
 * Cette fen�tre g�re l'affichage des formes 
 * @author Patrice Boucher
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class FenetreFormes extends JComponent{
	
	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500,500);
	private static final int QUEUE_LENGTH = 10;
	
	private FormeQueue fileFormes;
	private CreateurFormes createur;
		
	/**
	 * Constructeur
	 */
	public FenetreFormes(){
		this.setBackground(Color.WHITE);
		fileFormes = new FormeQueue(QUEUE_LENGTH);
		createur = new CreateurFormes();
	}
	
	/*
	 * Affiche la liste de formes 
	 */
	@Override 
	public void paintComponent(Graphics g){
		
		for(int i=fileFormes.getLength()-1; i>=0; i--){
			Forme f = fileFormes.get(i);
			f.dessiner(g);
		}
		this.repaint();
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver 
	 * l'espace nécessaire à son affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}
	/**
	 * Re�oit une chaine de caract�re, la transforme en forme puis l'ajoute � la file de formes.
	 * 
	 * @param s
	 */
	public void addForme(String s){
		fileFormes.add(createur.creerForme(s));
	}
}
