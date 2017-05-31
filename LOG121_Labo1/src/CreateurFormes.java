import java.awt.Dimension;
import java.awt.Point;
/**
 * Cette classe crée des formes à partir d'une chaine de caractère
 * 
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class CreateurFormes {

	private Decomposeur decomposeur;
	
	public CreateurFormes(){
		decomposeur = new Decomposeur();
	}
	/**
	 * Décompose la chaine reçue puis crée la forme appropriée
	 * 
	 * @param string
	 * @return forme
	 */
	public Forme creerForme(String s){
		String[] tabString = decomposeur.decomposerString(s);
		
		int id = Integer.parseInt(tabString[0]);
		String type = tabString[1];
		Point pos = new Point(Integer.parseInt(tabString[2]),Integer.parseInt(tabString[3]));
		Dimension dim = new Dimension(Integer.parseInt(tabString[4]), Integer.parseInt(tabString[5]));
		
		Forme forme = null;
		
		switch (type){
		case "CARRE": case "RECTANGLE":
			forme = new Rectangle(pos, dim);
			break;
		case "CERCLE": case "OVALE":
			forme = new Ovale(pos, dim);
			break;
		case "LIGNE":
			forme = new Ligne(pos, dim);
			break;
		}
		return forme;	
	}
}
