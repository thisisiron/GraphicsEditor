package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import constant.GEConstants;
import constant.GEConstants.EAnchorTypes;

public class GEGroup extends GEShape {  
	private ArrayList<GEShape> shapeList; 
	private BasicStroke dashedLineStroke; 
	
	// GEGroup�� ������
	public GEGroup() {  
		super(new Rectangle());  
		shapeList = new ArrayList<GEShape>(); 
		float dashes[] = {GEConstants.DEFAULT_DASH_OFFSET };  
		dashedLineStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH ,BasicStroke. CAP_ROUND ,BasicStroke. JOIN_ROUND , 10, dashes, 0); 
	}  

	
	// �ʿ�� ������ abstract�� ���� ����
	public GEShape clone() {  
		return null;  
	} 
	
	// �ʱ� ������ ����
	public void initDraw(Point startP) {
		for(GEShape shape : shapeList){  
			shape.initDraw(startP); 
		} 
	} 
	
	// lineColor�� ����
	public void setLineColor(Color lineColor){  
		for(GEShape shape : shapeList){  
			shape.setLineColor(lineColor);   
		}  
	}  
	
	// fillColor�� ����
	public void setFillColor(Color fillColor){  
		for(GEShape shape : shapeList){ 
			shape.setFillColor(fillColor);
		}  
	}     
	
	// �׸��� �����ϸ鼭 �׸�
	public void setCoordinate(Point currentP) {
		for(GEShape shape : shapeList){   
			shape.setCoordinate(currentP);  
		} 
	}  
	
	// ���õ� �������� shapeList�� �����ϰ� size�� 1�̶�� �ش絵���� ��ȯ�ϰ� ���� �����̸� ���������� �ϳ��� ���ļ� ��ȯ�Ѵ�.
	public void addShape(GEShape shape){  
		shapeList.add(0,shape);  
		if(shapeList.size() == 1){ 
			myShape = shape.getBounds();  
		} 
		else { 
			myShape = myShape.getBounds().createUnion(shape.getBounds());  
		}  
	}  

	// shapeList�� ��ȯ�Ѵ�.
	public ArrayList<GEShape> getChildList(){  
		return shapeList;
	} 

	// �ش絵�������� ���콺�� ��Ŀ���� �ִ��� �ƴ����� �Ǵ�
	public boolean onShape(Point p){  
		if (anchorList!=null){ 
			selectedAnchor = anchorList.onAnchors(p);  
			if (selectedAnchor != EAnchorTypes.NONE )  
				return true;  
		}  
		for(GEShape shape : shapeList){   
			if(shape.onShape(p)){   
				return true;
			}   
		} 
		return false;
	}

	// ��� ������ �ٽ� �׷��ְ� ���� �׷��� group�� ���É����� anchorList�� �׷���
	public void draw(Graphics2D g2D){  
		for(GEShape shape : shapeList){
			shape.draw(g2D);   
		}   
		if(this.isSelected()){        
			g2D.setColor(GEConstants.DEFAULT_LINE_COLOR );  
			anchorList.setPosition(myShape.getBounds());
			anchorList.draw(g2D);   
		} 
	} 
	
//	//����� �⺻ ������ ���� �ش� ������ ȸ�������� �ٲ�
//	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
//		super.rotaterCoordinate(theta, rotaterAnchor);
//		for(GEShape shape :shapeList){
//			shape.rotaterCoordinate(theta, rotaterAnchor);
//		} 
//	}
//	
//	//������ �Է°��� ���� ������ ȸ����Ŵ
//	public void rotaterCoordinate_for_userInput(double theta, Point2D rotaterAnchor){
//		super.rotaterCoordinate_for_userInput(theta, rotaterAnchor);
//		for(GEShape shape :shapeList){
//			shape.rotaterCoordinate_for_userInput(theta, rotaterAnchor);
//		}
//	}
	
	// ����� �⺻�࿬���� ���� �׷��� �������� �̵���Ŵ
	public void moveCoordinate(Point tempP){ 
		super.moveCoordinate(tempP);  
		for(GEShape shape : shapeList){ 
			shape.moveCoordinate(tempP);
		}
	} 
	
	// ����� �⺻�࿬���� ���� �׷��� �������� ũ�⸦ ������
	public void resizeCoordinate(Point2D resizeFactor){  
		super.resizeCoordinate(resizeFactor); 
		for(GEShape shape : shapeList){  
			shape.resizeCoordinate(resizeFactor); 
		} 
	}     
	
	// ����� �⺻�࿬���� ���� �׷��� �������� ũ�⸦ ������
	public void moveReverse(Point resizeAnchor){  
		super.moveReverse(resizeAnchor); 
		for(GEShape shape : shapeList){ 
			shape.moveReverse(resizeAnchor); 
		} 
	}       
	
	// ����� �⺻�࿬���� ���� �׷��� �������� ũ�⸦ ������
	public void move(Point resizeAnchor){    
		super.move(resizeAnchor);  
		for(GEShape shape : shapeList){  
			shape.move(resizeAnchor);  
		}  
	} 
	
	
	@Override
	public GEShape deepCopy(){
		return null;
	}
}