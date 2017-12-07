package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constant.GEConstants.EAnchorTypes;
import utils.GEAnchorList;

public abstract class GEShape {
	protected Shape myShape;
	protected Point startP;
	protected GEAnchorList anchorList;
	protected boolean selected;
	protected EAnchorTypes selectedAnchor; //선택된것이 무엇인지
	protected Color fillColor, lineColor;
	protected AffineTransform affineTransform;
	
	
	public GEShape(Shape shape){
		this.myShape = shape;
		anchorList = null;
		selected = false;
		affineTransform = new AffineTransform();
	}
	
	
	public GEAnchorList getAnchorList() {
		return anchorList;
	}


	public boolean isSelected() {
		return selected;
	}

	
	public Shape getMyShape() {
		return myShape;
	}

	public EAnchorTypes getSelectedAnchor() {
		return selectedAnchor;
	}
	
	
	public Rectangle getBounds() {
		return myShape.getBounds();
	}

		
	public EAnchorTypes onAnchor(Point p) {
		selectedAnchor = anchorList.onAnchors(p);
		return selectedAnchor;
	}

	public void setMyShape(Shape myShape) {
		this.myShape = myShape;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}



	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	
	public Color getLineColor(){
		return lineColor;
	}
	
	public Color getFillColor(){
		return fillColor;
	}


	public void draw(Graphics2D g2D) {
		if(fillColor != null) {
			g2D.setColor(fillColor);
			g2D.fill(myShape);
		}
		
		if(lineColor != null) {
			g2D.setColor(lineColor);
			g2D.draw(myShape);
		}
		

		if(selected  == true){
			anchorList.setPosition(myShape.getBounds());
			anchorList.draw(g2D);
		}
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
		if(selected == true){
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		} else {
			anchorList = null;
		}
	}
	
	protected void setShape(Shape shape){
		myShape = shape;
	}
	public void setAnchorList(GEAnchorList anchorList){
		this.anchorList = anchorList;
	}
	
	
	public void setGraphicsAttributes(GEShape shape){
		setLineColor(shape.getLineColor());
		setFillColor(shape.getFillColor());
		setAnchorList(shape.getAnchorList());
		setAnchorList(shape.getAnchorList());
		setSelected(shape.isSelected());	
	}
	
	
	public boolean onShape(Point P)	{
		if(anchorList != null){
			selectedAnchor = anchorList.onAnchors(P);
			if(selectedAnchor != EAnchorTypes.NONE){
				return true;
			}
		}
		
		return myShape.intersects(new Rectangle(P.x,P.y,2,2));
	}
	
	public void resizeCoordinate(Point2D resizeFactor) {
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		myShape = affineTransform.createTransformedShape(myShape);
		
	}

	public void moveCoordinate(Point moveP) {
		affineTransform.setToTranslation(moveP.getX(), moveP.getY());
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	
	public void move(Point resizeAnchor) {//원위치시키기
		affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	public void moveReverse(Point resizeAnchor) {//원점으로이동
		affineTransform.setToTranslation(-resizeAnchor.getX(), -resizeAnchor.getY());
		myShape = affineTransform.createTransformedShape(myShape);
		
	}
	
	
	public abstract void initDraw(Point startP);
	public abstract void setCoordinate(Point currentP);
	public abstract GEShape clone(); 
	abstract public GEShape deepCopy();
}
