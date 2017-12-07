package transformer;  
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import constant.GEConstants;
import shapes.GEShape;

public class GEGrouper extends GETransformer{  
	// GEGroup�� ������
	public GEGrouper(GEShape shape){ 
		super(shape);
	}  
	
	// �ʱ� ������ ����
	public void init(Point p) { 
		shape.initDraw(p); 
	}  
	
	// �׷����� ���õ� �׸����� �ٽ� �׸�
	public void transformer(Graphics2D g2D, Point p) {   
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(dashedLineStroke); 
		shape.draw(g2D);  
		shape.setCoordinate(p); 
		shape.draw(g2D); 
	}  
	
	// ���õ� �׸����� ��� tempList�� �޾� ��� selected�� true�� �ǰ���
	public void finalize(ArrayList<GEShape> shapeList) {
		for (GEShape tempShape : shapeList) { 
			if (shape.getBounds().contains(tempShape.getBounds())) {     
				tempShape.setSelected(true);   
			}   
		}  
	}
} 