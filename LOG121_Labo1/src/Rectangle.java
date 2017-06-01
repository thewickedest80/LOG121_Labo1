import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
/**
 * cette classe dessine un rectangle
 * 
 * @author Louis Raymond-Poirier
 * @date 2017/05/31
 */
public class Rectangle extends Forme{
	public Rectangle(int id, Point pos, Dimension dim){
		super(id, pos,dim);
		
		if(dim.width==dim.height){
			col = rouge;
		}else{
			col = jaune;
		}
	}
	@Override
	public void dessiner(Graphics g){
		g.setColor(col);
		g.fillRect(pos.x,pos.y,dim.width,dim.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(pos.x,pos.y,dim.width,dim.height);
	}
}
