package transformer;  
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import constant.GEConstants;
import shapes.GEShape;

public class GEGrouper extends GETransformer{  
	// GEGroup의 생성자
	public GEGrouper(GEShape shape){ 
		super(shape);
	}  
	
	// 초기 지점을 지정
	public void init(Point p) { 
		shape.initDraw(p); 
	}  
	
	// 그룹으로 선택된 그림들을 다시 그림
	public void transformer(Graphics2D g2D, Point p) {   
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(dashedLineStroke); 
		shape.draw(g2D);  
		shape.setCoordinate(p); 
		shape.draw(g2D); 
	}  
	
	// 선택된 그림들이 담긴 tempList를 받아 모두 selected가 true가 되게함
	public void finalize(ArrayList<GEShape> shapeList) {
		for (GEShape tempShape : shapeList) { 
			if (shape.getBounds().contains(tempShape.getBounds())) {     
				tempShape.setSelected(true);   
			}   
		}  
	}
} 