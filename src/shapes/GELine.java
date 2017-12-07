package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class GELine  extends GEShape{


	//	private Point startP;
//	private Line2D line;
//	
	public GELine() {
		super(new Line2D.Double());
	}
	
	public void initDraw(Point startP) {
		this.startP = startP;
	}
	
	public void setCoordinate(Point currentP) {
		Line2D line = (Line2D)myShape;
		line.setLine(startP.x, startP.y, currentP.x, currentP.y);
		if(anchorList!=null){
			anchorList.setPosition(myShape.getBounds());
		}
	}

	@Override
	public GEShape clone() {
		// TODO Auto-generated method stub
		return new GELine();
	}	
	
	
	public GEShape deepCopy(){
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(myShape);
		GELine shape = new GELine();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;
	}
	
}
