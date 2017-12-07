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
	
	public void continueDraw(Point currentP){
		((Polygon)myShape).addPoint(currentP.x, currentP.y);
	}

	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[tempPolygon.npoints - 1] = currentP.x;
		tempPolygon.ypoints[tempPolygon.npoints - 1] = currentP.y;
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
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
