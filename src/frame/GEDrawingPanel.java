package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import constant.GEConstants;
import constant.GEConstants.EAnchorTypes;
import constant.GEConstants.EState;
import constant.GEConstants.EToolBarButtons;
import shapes.GEEllipse;
import shapes.GEGroup;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelect;
import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotater;
//import transformer.GERotater;
import transformer.GEResizer;
import transformer.GETransformer;
import transformer.GEGrouper;
import utils.GEClipBoard;
import utils.GECursorManager;
import utils.GEHistory;

public class GEDrawingPanel extends JPanel {
	
	private GEShape currentShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private MouseDrawingHandler drawingHandler;
	private GEShape selectedShape;
	private GETransformer transformer;
	private Color fillColor, lineColor;
	private GECursorManager cursorManager;
	private GEHistory history;
	private GEClipBoard clipboard;
	private GEDrawingPanel instance;
	
	public GEDrawingPanel() { //Panel에서 발생하기 때문에 등록함
		super();
		currentState = EState.Idle;
		instance = this;
		shapeList = new ArrayList<GEShape>();
		drawingHandler = new MouseDrawingHandler();
		fillColor = GEConstants.DEFAULT_FILL_COLOR;
		lineColor = GEConstants.DEFAULT_LINE_COLOR;
		cursorManager = new GECursorManager();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		clipboard = new GEClipBoard();
		history = new GEHistory();
	}

	public void setFillColor(Color fillColor) {
		if(selectedShape != null) {
			selectedShape.setFillColor(fillColor);
			addHistory();
			repaint();
		} else {
			this.fillColor = fillColor;
		}
	}
	
	public void addHistory(){
		history.push(shapeList);
	}
	

	public void setLineColor(Color lineColor) {
		if(selectedShape != null) {
			selectedShape.setLineColor(lineColor);
			addHistory();
			repaint();
		} else {
			this.lineColor = lineColor;
		}
	}
	
	
	
	private GEShape onShape(Point p){
		for(int i=shapeList.size()-1; i>=0; i--){
			GEShape shape = shapeList.get(i);
			if(shape.onShape(p) == true){
				return shape;
			}
		}
		return null;
	}
	
	private void clearSelectedShapes(){
		for(GEShape shape : shapeList){
			shape.setSelected(false);
		}
	}
	
	
	public void setCurrentShape(GEShape currentShape) {
		this.currentShape = currentShape;
	}



	private void initDraw(Point startP) {
		currentShape = currentShape.clone();
		currentShape.setFillColor(fillColor);
		currentShape.setLineColor(lineColor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		for(GEShape shape: shapeList){
			shape.draw(g2D);
		}
		
	}


	
	
	private void finishDraw(){
		shapeList.add(currentShape);
		addHistory();
	}
	
	private void continueDrawing(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	
	//paste
		public void paste(){
			ArrayList<GEShape> pasteShapes = clipboard.paste();
			for(int i=pasteShapes.size()-1; i>=0; i--){
				shapeList.add(pasteShapes.get(i).deepCopy());
			}
			addHistory();
			repaint();
		}
		
		//copy
		public void copy(){
			clipboard.copy(shapeList);
			
		}
		
		public void cut(){
			clipboard.cut(shapeList);
			addHistory();
			repaint();
		}
		
		public void delete(){
			for(int i = shapeList.size(); i > 0 ; i--){
				GEShape shape = shapeList.get(i-1);
				if(shape.isSelected()){
					shape.setSelected(false);
					shapeList.remove(shape);
				}
			}
			addHistory();
			repaint();
		}
	
	
	
	
	// 그룹 생성
	public void group(GEGroup group) {   
		boolean check = false;   
		for (int i = shapeList.size(); i > 0; i--) { 
			GEShape shape = shapeList.get(i - 1);  
			if(shape.isSelected()){ 
				shape.setSelected(false);   
				group.addShape(shape); 
				shapeList.remove(shape);
				check = true;
			}  
		}  
		if(check){  
			shapeList.add(group); 
			group.setSelected(true);  
		}
		repaint();  
	} 
	
	// 그룹해제
	public void unGroup() { 
		ArrayList<GEShape> tempList = new ArrayList<GEShape>();
		for (int i = shapeList.size(); i > 0; i--) { 
			GEShape shape = shapeList.get(i - 1);  
			if(shape instanceof GEGroup && shape.isSelected()){
				for(GEShape childShape: ((GEGroup)shape).getChildList()){   
					childShape.setSelected(true);   
					tempList.add(childShape);   
				} 
				shapeList.remove(shape); 
			}
		}
		shapeList.addAll(tempList);  
		repaint();  
	} 
	
	
	
	public void undo(){
		shapeList = history.undo();
		selectedShape = null;
		repaint();
	}
	
	public void redo(){
		shapeList = history.redo();
		selectedShape = null;
		repaint();
	}
	
	
	
	
	
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(currentState != EState.Idle ){
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			}
			
			
			
			
			transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			if(currentState == EState.Rotating){
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (currentState == EState.Idle ) {  
				if (currentShape instanceof GESelect && (e.isShiftDown())){
					selectedShape = onShape(e.getPoint());
					 if(selectedShape != null) {
						selectedShape.setSelected(true);  
						selectedShape.onAnchor(e.getPoint());
						currentState = EState.Shift; 
					}else{
						clearSelectedShapes();
					}
				}else if (currentShape instanceof GESelect){  
					selectedShape = onShape(e.getPoint());
					if (selectedShape != null) {       
						 clearSelectedShapes();  
						 selectedShape.setSelected(true);  
						 selectedShape.onAnchor(e.getPoint());     
						 if (selectedShape.getSelectedAnchor() == EAnchorTypes.NONE ){   
							 transformer = new GEMover(selectedShape);    
							 ((GEMover)transformer).init(e.getPoint());   
							 currentState = EState.Moving;     
						 }else if( selectedShape.getSelectedAnchor() == EAnchorTypes.RR ){ 
								transformer = new GERotater(selectedShape);
								((GERotater)transformer).init(e.getPoint());
								currentState = EState.Rotating;
						}else {       
							 transformer = new GEResizer(selectedShape);   
							 ((GEResizer) transformer).init(e.getPoint());    
							 currentState = EState.Resizing;     
						 }  
					}else {   
						 currentState = EState.Selecting;  
						 clearSelectedShapes();
						 initDraw(e.getPoint());   
						 transformer = new GEGrouper(currentShape);   
						 ((GEGrouper)transformer).init(e.getPoint());
					 }  				
				}else {  
					clearSelectedShapes();    
					initDraw(e.getPoint());    
					transformer = new GEDrawer(currentShape);     
					((GEDrawer) transformer).init(e.getPoint());  
					if (currentShape instanceof GEPolygon) {     
						currentState = EState.NPointDrawing;  
					} else {   
						currentState = EState.TwoPointDrawing; 
					}    
				}   
			}   
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointDrawing){
				finishDraw();
			}else if(currentState == EState.NPointDrawing){
				return;
			}else if(currentState == EState.Resizing){
				((GEResizer)transformer).finalize();
				addHistory();
			}else if(currentState == EState.Selecting){
				((GEGrouper)transformer).finalize(shapeList);
			}else if(currentState == EState.Moving){
				if(((GEMover)transformer).isMoved()){
					addHistory();
				}
			}else if(currentState == EState.Rotating){
				if(((GERotater)transformer).isMoved()){
					addHistory();
				} 
			}
			currentState = EState.Idle;
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointDrawing){
					if(e.getClickCount() == 1){
						continueDrawing(e.getPoint());
					} else if(e.getClickCount() == 2){
						finishDraw();
						currentState = EState.Idle;
						repaint();
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointDrawing){
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			}else if(currentState == EState.Idle) {
				GEShape shape = onShape(e.getPoint());
				if(shape==null) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else if(shape.isSelected() == true) {
					EAnchorTypes anchorType = shape.onAnchor(e.getPoint());
					setCursor(cursorManager.get(anchorType.ordinal()));
				}
			}
		}
		
	}
}
