import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Cercle extends Ovale{
	
	public Cercle(int id, Point pos, Dimension dim){
		super(id, pos,dim);
		col = bleu;
	}
	@Override
	public void dessiner(Graphics g){
		g.setColor(col);
		g.fillOval(pos.x,pos.y,dim.width,dim.height);
		
		g.setColor(Color.BLACK);
		g.drawOval(pos.x,pos.y,dim.width,dim.height);
	}
	@Override
	public double getAire() {
		// TODO Auto-generated method stub
		aire = 2 * pi * dim.width;
		return aire;
	}
}


