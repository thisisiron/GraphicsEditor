package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import utils.GEAnchorList;

public abstract class GEShape {
	protected Point startP;
	protected Shape myShape;
	protected Color lineColor, fillColor;
	protected boolean selected;
	protected GEAnchorList anchorList;
	protected EAnchorTypes selectedAnchor;
	protected AffineTransform affineTransform;
	
	public GEShape(Shape shape){
		this.myShape = shape;
		anchorList = null;
		selected = false;
		affineTransform = new AffineTransform();
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public GEAnchorList getAnchorList(){
		return anchorList;
	}
	
	public EAnchorTypes getSelectedAnchor() {
		return selectedAnchor;
	}
	
	public Rectangle getBounds(){
		return myShape.getBounds();
	}
	
	public EAnchorTypes onAnchor(Point p){
		selectedAnchor = anchorList.onAnchors(p);
		return selectedAnchor;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
		if(selected == true){
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		} 
		else {
			anchorList = null;
		}
	}
	
	public boolean onShape(Point p){
		if(anchorList != null){		
			selectedAnchor = anchorList.onAnchors(p);
			if(selectedAnchor != GEConstants.EAnchorTypes.NONE){
				return true;
			}
		}
		return myShape.intersects(p.x, p.y, 2 ,2);
	}
	
	public void draw(Graphics2D g2d){
		if(fillColor != null){
			g2d.setColor(fillColor);
			g2d.fill(myShape);
		}
		if(lineColor != null){
			g2d.setColor(lineColor);
			g2d.draw(myShape);
		}
		if(selected){
			anchorList.setPosition(myShape.getBounds());
			anchorList.draw(g2d);
		}
	}
	
	protected void setShape(Shape shape){
		myShape = shape;
	}
	
	public void setGraphicsAttributes(GEShape shape){
		setLineColor(shape.getLineColor());
		setFillColor(shape.getFillColor());
		setAnchorList(shape.getAnchorList());
		setAnchorList(shape.getAnchorList());
		setSelected(shape.isSelected());	
	}
	
	public Color getLineColor(){
		return lineColor;
	}
	
	public Color getFillColor(){
		return fillColor;
	}
	
	public void setAnchorList(GEAnchorList anchorList){
		this.anchorList = anchorList;
	}
	
	public void move(Point resizeAnchor){
		affineTransform.setToTranslation(resizeAnchor.x, resizeAnchor.y);
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	public void moveReverse(Point resizeAnchor){
		affineTransform.setToTranslation(-resizeAnchor.x, -resizeAnchor.y);
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	public void resizeCoordinate(Point2D resizeFactor){
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		myShape= affineTransform.createTransformedShape(myShape);
	}
	
	public void moveCoordinate(Point moveP){
		affineTransform.setToTranslation(moveP.x, moveP.y);
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	public void moveReverse(Point2D resizeAnchor){
		affineTransform.setToTranslation(-resizeAnchor.getX(), -resizeAnchor.getY());
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY() );
		myShape = affineTransform.createTransformedShape(myShape);
		
	}
	public void move(Point2D resizeAnchor){
		affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();
	abstract public GEShape deepCopy();
}
