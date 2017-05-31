import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
/**
 * Cette classe abstraite sert de gabarit pour des formes spécialisées
 * 
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public abstract class Forme {
	
	protected Point pos;
	protected Dimension dim;
	protected Color col;
	
	protected Color rouge = new Color(255,52,52);
	protected Color bleu = new Color(52,153,255);
	protected Color vert = new Color(50,252,52);
	protected Color jaune = new Color(255,252,52);
	protected Color orange = new Color(255,180,100);
	
	public Forme(Point pos, Dimension dim){
		this.pos = pos;
		this.dim = dim;
	}
	public abstract void dessiner(Graphics g);
}
