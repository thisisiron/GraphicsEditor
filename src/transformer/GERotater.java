package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import shapes.GEShape;


public class GERotater extends GETransformer{
	private ArrayList<GEShape> shapelist;
	private Point2D.Double ROrigin;
	private double theta; 
	private boolean moved;

	public GERotater(GEShape shape) {
		super(shape);
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		double nextTheta = theta - Math.atan2(ROrigin.y- p.getY(), p.getX() - ROrigin.x);
		shape.draw(g2d);
		shape.rotaterCoordinate(nextTheta, ROrigin);
		shape.draw(g2d);
		theta = Math.atan2(ROrigin.y - p.getY(), p.getX() - ROrigin.x); 
	}
	
	public boolean isMoved(){
		return moved;
	}
	
	public void setMove(boolean moved){
		this.moved = moved;
//		moved = false;
	}
	

	@Override
	public void init(Point p) {
		ROrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		theta = Math.atan2(ROrigin.y - p.getY(), ROrigin.x - p.getX());

	}
}
