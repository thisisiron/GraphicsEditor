package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEShape;

public class GEMover extends GETransformer {

	private Point previousP;
	private boolean moved;
	
	public GEMover(GEShape shape) {
		super(shape);
		previousP = new Point();
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		Point tempP = new Point(p.x - previousP.x, p.y - previousP.y);
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke); // 점선으로 그리도록한다.
		shape.draw(g2d); //점선으로 따라서 계속 그린다.
		shape.moveCoordinate(tempP); 
		shape.draw(g2d);
		previousP = p; //갱신시켜주도록한다.
	}
	
	public boolean isMoved(){
		return moved;
	}
	
	public void setMove(boolean moved){
		this.moved = moved;
	}

	@Override
	public void init(Point P) {
		previousP = P;
	}

}
