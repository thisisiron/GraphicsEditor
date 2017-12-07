package shapes;  
import java.awt.Point; 
import java.awt.Rectangle;  
public class GESelect extends GEShape {
	// GESelect의 생성자
	public GESelect() {
		super(new Rectangle());  
	}  
	
	// 초기 지점을 지정
	public void initDraw(Point startP){   
		this.startP = startP;  
	}  

	// 그림을 조정하면서 그림
	public void setCoordinate(Point currentP){   
		Rectangle tempRectangle = (Rectangle)myShape;   
		tempRectangle.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);  
	}  
	
	// 만들어진 도형의 클론을 반환
	@Override
	public GESelect clone(){   
		return new GESelect();  
	}  
	
	@Override
	public GEShape deepCopy(){
		return null;
	}
	
}