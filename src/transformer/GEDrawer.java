package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEPolygon;
import shapes.GEShape;

public class GEDrawer extends GETransformer{
	
	public GEDrawer(GEShape shape) {
		super(shape);
	}

	@Override
	public void init(Point p) {
		shape.initDraw(p);
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		shape.draw(g2d);
		shape.setCoordinate(p);
		shape.draw(g2d);
	}
	
	public void continueDrawing(Point p){
		((GEPolygon)shape).continueDraw(p);
	}

}
