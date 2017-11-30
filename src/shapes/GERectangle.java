package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape {

	public GERectangle() {
		super(new Rectangle());
	}

	public void initDraw(Point startP) {
		this.startP = startP;
	}

	public void setCoordinate(Point currentP) { // 절대값으로 씌우면 왼쪽으로도 증가하게하기
		Rectangle rectangle = (Rectangle) myShape;
		rectangle.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
		if (anchorList != null) {
			anchorList.setPosition(myShape.getBounds());
		}
	}

	@Override
	public GEShape clone() {
		// TODO Auto-generated method stub
		return new GERectangle();
	}

}
