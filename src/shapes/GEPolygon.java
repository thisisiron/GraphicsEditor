package shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GEPolygon extends GEShape {

	public GEPolygon(){
		super(new Polygon());
	}
	
	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x, startP.y);

	}

	@Override
	public void setCoordinate(Point currentP) {
		Polygon polygon = (Polygon)myShape;
		polygon.xpoints[polygon.npoints - 1] = currentP.x;
		polygon.ypoints[polygon.npoints - 1] = currentP.y;
		if(anchorList!=null){
			anchorList.setPosition(myShape.getBounds());
		}

	}
	
	public void continueDrawing(Point currentP) {
		((Polygon)myShape).addPoint(currentP.x, currentP.y);
	}
	
	
	
	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
	
	public GEShape deepCopy(){
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(myShape);
		GEPolygon shape = new GEPolygon();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
