import java.awt.Dimension;
import java.awt.Point;

import ca.etsmtl.log.util.IDLogger;
/**
 * Cette classe crée des formes à partir d'une chaine de caractère
 * 
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class CreateurFormes {
	
	private Decomposeur decomposeur;
	private IDLogger logger;
	
	public CreateurFormes(){
		decomposeur = new Decomposeur();
		logger = IDLogger.getInstance();
	}
	/**
	 * Décompose la chaine reçue puis crée la forme appropriée
	 * 
	 * @param string
	 * @return forme
	 */
	public Forme creerForme(String s){
		
		
		String[] tabString = decomposeur.decomposerString(s);
		/*
		 * tabString[0] id
		 * tabString[1] type
		 * tabString[2] posX
		 * tabString[3] posY
		 * tabString[4] width
		 * tabString[5] height
		 */
		
		String type = tabString[1];
		int id = Integer.parseInt(tabString[0]);
		logger.logID(id);
		Point pos = new Point(Integer.parseInt(tabString[2]),Integer.parseInt(tabString[3]));
		Dimension dim = new Dimension(Integer.parseInt(tabString[4]), Integer.parseInt(tabString[5]));
		
		Forme forme = null;
		
		switch (type){
		case "CARRE": 
			forme = new Carre(id, pos, dim);
			break;
		case "RECTANGLE":
			forme = new Rectangle(id, pos, dim);
			break;
		case "CERCLE": 
			forme = new Cercle(id, pos, dim);
			break;
		case "OVALE":
			forme = new Ovale(id, pos, dim);
			break;
		case "LIGNE":
			forme = new Ligne(id, pos, dim);
			break;
		}
		return forme;	
	}
}
