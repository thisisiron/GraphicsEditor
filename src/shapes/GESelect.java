package shapes;  
import java.awt.Point; 
import java.awt.Rectangle;  
public class GESelect extends GEShape {
	// GESelect�� ������
	public GESelect() {
		super(new Rectangle());  
	}  
	
	// �ʱ� ������ ����
	public void initDraw(Point startP){   
		this.startP = startP;  
	}  

	// �׸��� �����ϸ鼭 �׸�
	public void setCoordinate(Point currentP){   
		Rectangle tempRectangle = (Rectangle)myShape;   
		tempRectangle.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);  
	}  
	
	// ������� ������ Ŭ���� ��ȯ
	@Override
	public GESelect clone(){   
		return new GESelect();  
	}  
	
	@Override
	public GEShape deepCopy(){
		return null;
	}
	
}