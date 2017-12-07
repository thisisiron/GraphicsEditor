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
	
	// GEGroup의 생성자
	public GEGroup() {  
		super(new Rectangle());  
		shapeList = new ArrayList<GEShape>(); 
		float dashes[] = {GEConstants.DEFAULT_DASH_OFFSET };  
		dashedLineStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH ,BasicStroke. CAP_ROUND ,BasicStroke. JOIN_ROUND , 10, dashes, 0); 
	}  

	
	// 필요는 없지만 abstract에 의해 생성
	public GEShape clone() {  
		return null;  
	} 
	
	// 초기 지점을 지정
	public void initDraw(Point startP) {
		for(GEShape shape : shapeList){  
			shape.initDraw(startP); 
		} 
	} 
	
	// lineColor을 지정
	public void setLineColor(Color lineColor){  
		for(GEShape shape : shapeList){  
			shape.setLineColor(lineColor);   
		}  
	}  
	
	// fillColor을 지정
	public void setFillColor(Color fillColor){  
		for(GEShape shape : shapeList){ 
			shape.setFillColor(fillColor);
		}  
	}     
	
	// 그림을 조정하면서 그림
	public void setCoordinate(Point currentP) {
		for(GEShape shape : shapeList){   
			shape.setCoordinate(currentP);  
		} 
	}  
	
	// 선택된 도형들을 shapeList에 저장하고 size가 1이라면 해당도형을 반환하고 여러 도형이면 여러도형을 하나로 합쳐서 반환한다.
	public void addShape(GEShape shape){  
		shapeList.add(0,shape);  
		if(shapeList.size() == 1){ 
			myShape = shape.getBounds();  
		} 
		else { 
			myShape = myShape.getBounds().createUnion(shape.getBounds());  
		}  
	}  

	// shapeList를 반환한다.
	public ArrayList<GEShape> getChildList(){  
		return shapeList;
	} 

	// 해당도형위에서 마우스가 앵커위에 있는지 아닌지를 판단
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

	// 모든 도형을 다시 그려주고 새로 그려진 group이 선택됬을때 anchorList를 그려줌
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
	
//	//행렬의 기본 연상을 통해 해당 도형의 회전각도를 바꿈
//	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
//		super.rotaterCoordinate(theta, rotaterAnchor);
//		for(GEShape shape :shapeList){
//			shape.rotaterCoordinate(theta, rotaterAnchor);
//		} 
//	}
//	
//	//유저의 입력값에 따라 도형의 회전시킴
//	public void rotaterCoordinate_for_userInput(double theta, Point2D rotaterAnchor){
//		super.rotaterCoordinate_for_userInput(theta, rotaterAnchor);
//		for(GEShape shape :shapeList){
//			shape.rotaterCoordinate_for_userInput(theta, rotaterAnchor);
//		}
//	}
	
	// 행렬의 기본행연산을 통해 그룹의 도형들을 이동시킴
	public void moveCoordinate(Point tempP){ 
		super.moveCoordinate(tempP);  
		for(GEShape shape : shapeList){ 
			shape.moveCoordinate(tempP);
		}
	} 
	
	// 행렬의 기본행연산을 통해 그룹의 도형들의 크기를 조절함
	public void resizeCoordinate(Point2D resizeFactor){  
		super.resizeCoordinate(resizeFactor); 
		for(GEShape shape : shapeList){  
			shape.resizeCoordinate(resizeFactor); 
		} 
	}     
	
	// 행렬의 기본행연산을 통해 그룹의 도형들의 크기를 조절함
	public void moveReverse(Point resizeAnchor){  
		super.moveReverse(resizeAnchor); 
		for(GEShape shape : shapeList){ 
			shape.moveReverse(resizeAnchor); 
		} 
	}       
	
	// 행렬의 기본행연산을 통해 그룹의 도형들의 크기를 조절함
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