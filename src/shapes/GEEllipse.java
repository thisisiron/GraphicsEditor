package shapes;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GEEllipse  extends GEShape{

	public GEEllipse() {
		super( new Ellipse2D.Double());
		
	}
	
	public void initDraw(Point startP) {
		this.startP = startP;
	}
	
	public void setCoordinate(Point currentP) {
		Ellipse2D ellipse = (Ellipse2D)myShape;
		ellipse.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
		if(anchorList!=null){
			anchorList.setPosition(myShape.getBounds());
		}
	}

	public GEShape clone(){
		return new GEEllipse();
	}
	
}
