import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
/**
 *  Cette classe dessine une ligne
 *  
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class Ligne extends Forme{
	public Ligne(int id, Point pos, Dimension dim){
		super(id, pos,dim);
		col = orange;
	}
	@Override
	public void dessiner(Graphics g){
		g.setColor(col);
		g.drawLine(pos.x,pos.y,dim.width,dim.height);
	}
}
