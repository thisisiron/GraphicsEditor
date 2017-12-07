package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEShape;

public class GEMover extends GETransformer{
	
	private Point previousP;
	private boolean moved;
	
	public GEMover(GEShape shape) {
		super(shape);
	}

	@Override
	public void init(Point p) {
		previousP = p;
		moved = false;
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		shape.draw(g2d);
		Point tempP = new Point(p.x - previousP.x, p.y - previousP.y);
		shape.moveCoordinate(tempP);
		shape.draw(g2d);
		previousP = p;
	}
	
	public boolean isMoved(){
		return moved;
	}
	
	public void setMove(boolean moved){
		this.moved = moved;
	}

}
