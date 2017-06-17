import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Carre extends Rectangle{
	public Carre(int id, Point pos, Dimension dim){
		super(id, pos,dim);
		col = rouge;
	}
	@Override
	public void dessiner(Graphics g){
		g.setColor(col);
		g.fillRect(pos.x,pos.y,dim.width,dim.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(pos.x,pos.y,dim.width,dim.height);
	}

}
