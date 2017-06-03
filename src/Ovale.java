import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
/**
 * cette classe dessine une ellipse
 * 
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class Ovale extends Forme{
	public Ovale(int id, Point pos, Dimension dim){
		super(id, pos,dim);
		
		//Si les dimensions de l'ellipse sont égales, on lui assigne une couleur différente,
		//afin de différencier les cercles des l'ellipses.
		if(dim.width==dim.height){
			col = bleu;
		}else{
			col = vert;
		}
	}
	@Override
	public void dessiner(Graphics g){
		g.setColor(col);
		g.fillOval(pos.x,pos.y,dim.width,dim.height);
		
		g.setColor(Color.BLACK);
		g.drawOval(pos.x,pos.y,dim.width,dim.height);
	}

}
