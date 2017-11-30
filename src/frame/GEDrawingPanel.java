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
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GERsizer;
import transformer.GETransformer;
import utils.GECursorManager;

public class GEDrawingPanel extends JPanel {
	
	private GEShape currentShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private MouseDrawingHandler drawingHandler;
	private GEShape selectedShape;
	private GETransformer transformer;
	private Color fillColor, lineColor;
	private GECursorManager cursorManager;
	
	public GEDrawingPanel() { //Panel에서 발생하기 때문에 등록함
		super();
		currentState = EState.Idle;

		shapeList = new ArrayList<GEShape>();
		drawingHandler = new MouseDrawingHandler();
		fillColor = GEConstants.DEFAULT_FILL_COLOR;
		lineColor = GEConstants.DEFAULT_LINE_COLOR;
		cursorManager = new GECursorManager();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
	}

	public void setFillColor(Color fillColor) {
		if(selectedShape != null) {
			selectedShape.setFilColor(fillColor);
			repaint();
		} else {
			this.fillColor = fillColor;
		}
	}

	public void setLineColor(Color lineColor) {
		if(selectedShape != null) {
			selectedShape.setLineColor(lineColor);
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
		currentShape.setFilColor(fillColor);
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
	}
	
	private void continueDrawing(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(currentState != EState.Idle ){
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(currentState == EState.Idle){
				if(currentShape != null){ //도형을 그리기위해 press
					clearSelectedShapes();
					selectedShape = null;
					initDraw(e.getPoint());
					transformer = new GEDrawer(currentShape);
					transformer.init(e.getPoint());
					if(currentShape instanceof GEPolygon){ // 뒤쪽이 Type
						currentState = EState.NPointDrawing;
					} else {
						currentState = EState.TwoPointDrawing;
					}
				}else { // 도형을 선택하기위해 press
					selectedShape = onShape(e.getPoint());
					clearSelectedShapes();
					if(selectedShape != null){
						selectedShape.setSelected(true);
						if(selectedShape.onAnchor(e.getPoint()) == EAnchorTypes.NONE) {
							transformer = new GEMover(selectedShape);
							currentState = EState.Moving;
							transformer.init(e.getPoint());
						} else {
							transformer = new GERsizer(selectedShape);
							currentState = EState.Resizing;
							transformer.init(e.getPoint());
						}
						
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(currentState == EState.TwoPointDrawing){
				finishDraw();
			} else if(currentState == EState.NPointDrawing){
				return;
			} else if (currentState == EState.Resizing) {
				((GERsizer)transformer).finalize();
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
