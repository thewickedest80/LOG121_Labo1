/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.ButtonGroup;


/**
 * Crée le menu de la fenêtre de l'applicationé
 */
public class MenuFenetre extends JMenuBar{
	
	private static final long serialVersionUID = 1536336192561843187L;
	private static final int  MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A;
	private static final int  MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int  MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int  MENU_FICHIER_OBTENIRFORMES_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_OBTENIRFORMES_TOUCHE_RACC = KeyEvent.VK_F;
//	private static final int  MENU_ORDRE_TOUCHE_MASK = ActionEvent.CTRL_MASK;
//	private static final char MENU_ORDRE_TOUCHE_RACC = KeyEvent.VK_O;
	private static final int  MENU_ORDRE_NSEQ_CROI_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_ORDRE_NSEQ_CROI_TOUCHE_RACC = KeyEvent.VK_1;
	private static final int  MENU_ORDRE_NSEQ_DECROI_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_ORDRE_NSEQ_DECROI_TOUCHE_RACC = KeyEvent.VK_2;
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
			MENU_FICHIER_OBTENIRFORMES = "app.frame.menus.file.formes",
			MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
			MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
			MENU_ORDRE_TITRE="app.frame.menus.ordre.title",
			MENU_ORDRE_NSEQ_CROI="app.frame.menus.ordre.nseq_croissant",
			MENU_ORDRE_NSEQ_DECROI="app.frame.menus.ordre.nseq_decroissant",
//			app.frame.menus.ordre.aire_croissant=Aire de forme (croissant)
//			app.frame.menus.ordre.aire_decroissant=Aire de forme (décroissant)
//			app.frame.menus.ordre.type_forme=Type de forme
//			app.frame.menus.ordre.type_forme_inverse=Type de forme (inversé)
//			app.frame.menus.ordre.distance=Distance maximale entre deux points de la forme (croissante)
			MENU_AIDE_TITRE = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS = "app.frame.menus.help.about";
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  

	private JMenuItem arreterMenuItem, demarrerMenuItem;
	private static final int DELAI_QUITTER_MSEC = 200;
 	   
	CommBase comm; // Pour activer/désactiver la communication avec le serveur
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuDessiner();
		addMenuFichier();
		addMenuOrdre();
		addMenuAide();
	}

	/**
	 *  Créer le menu "Draw". 
	 */
	protected void addMenuDessiner() {
		JMenu menu = creerMenu(MENU_DESSIN_TITRE,new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });

		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0) {
			comm.start();
			rafraichirMenus();
		  }
		});
		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			comm.stop();
			rafraichirMenus();
		    }
	    });
		
		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));
		add(menu);
	}

	/** 
	 * Créer le menu "File". 
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_OBTENIRFORMES, MENU_FICHIER_QUITTER });
			
		
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Obtenir 10 formes
				
			}
		});
		
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_OBTENIRFORMES_TOUCHE_RACC,
						MENU_FICHIER_OBTENIRFORMES_TOUCHE_MASK));
		
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.stop();
			    try {
						Thread.sleep(DELAI_QUITTER_MSEC);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		menu.getItem(1).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));
		
		
		add(menu);
	}

	/**
	 *  Créer le menu "Help". 
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,  LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}
	
	protected void addMenuOrdre(){
		JMenu menu = creerJRadioMenu(MENU_ORDRE_TITRE, new String[] { MENU_ORDRE_NSEQ_CROI, MENU_ORDRE_NSEQ_DECROI });
		
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Trier les formes en ce basant sur les numéros de séquence (croissant)
			}
		});
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Trier les formes en ce basant sur les numéros de séquence (décroissant)
			}
		});
		
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_ORDRE_NSEQ_CROI_TOUCHE_RACC,
						MENU_ORDRE_NSEQ_CROI_TOUCHE_MASK));
		menu.getItem(1).setAccelerator(
				KeyStroke.getKeyStroke(MENU_ORDRE_NSEQ_DECROI_TOUCHE_RACC,
						MENU_ORDRE_NSEQ_DECROI_TOUCHE_MASK));
		add(menu);

	}

	/**
	 *  Activer ou désactiver les items du menu selon la sélection. 
	 */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}
	
	/**
	 * Créer un élément de menu à partir d'un champs principal et ses éléments
	 * @param titleKey champs principal
	 * @param itemKeys éléments
	 * @return le menu
	 */
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for(int i=0; i < itemKeys.length; ++i) {
           menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
        }
        return menu;
   }
	
	private static JMenu creerJRadioMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        ButtonGroup group = new ButtonGroup();
        for(int i=0; i < itemKeys.length; ++i) {
           JRadioButtonMenuItem jradio = new JRadioButtonMenuItem(LangueConfig.getResource(itemKeys[i]));
           group.add(jradio);
           menu.add(jradio);
        }
        return menu;
   }
}
