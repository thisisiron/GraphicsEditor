package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}

	@Override
	public void initDraw(Point startP) {
		this.startP = startP;
	}

	@Override
	public void setCoordinate(Point currentP) {
		Ellipse2D ellipse = (Ellipse2D)myShape;
		ellipse.setFrame(startP.x, startP.y, currentP.x-startP.x, currentP.y-startP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}

	@Override
	public GEShape clone() {
		return new GEEllipse();
	}

	public GEShape deepCopy(){
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(myShape);
		GEEllipse shape = new GEEllipse();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;	
	}
}
