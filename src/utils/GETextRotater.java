package utils;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import frames.GEDrawingPanel;
import shapes.GEShape;

public class GETextRotater {
	TextField textField;
	GEShape shape;
	InputListener inputListener;
	GEDrawingPanel drawingPanel;
	public GETextRotater(){
		textField = new TextField();
		inputListener = new InputListener();
	}
	
	public void init(GEShape shape, GEDrawingPanel drawingPanel){
		this.shape = shape;
		this.drawingPanel = drawingPanel;
		Rectangle2D rec = shape.getAnchorList().getAnchors().get(EAnchorTypes.RR.ordinal()).getFrame();
		int x = (int)rec.getX();
		int y = (int)rec.getY();
		textField.addKeyListener(inputListener);
		textField.setBounds(x + GEConstants.TEXT_POS_X_OFFSET, y - GEConstants.TEXT_POS_Y_OFFSET, 
				GEConstants.TEXT_WIDTH_LENGTH, GEConstants.TEXT_HEIGHT_LENGTH);
	}
	
	public TextField getTextField(){
		return textField;
	}
	
	public void requestFocus(){
		textField.requestFocus();
	}
	
	private class InputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				System.out.println("엔터누름");
				if(!textField.getText() .equals("")){
					try{
						double delta = Math.toRadians(Double.parseDouble(textField.getText()));
						Point2D.Double ROrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
						shape.rotaterCoordinate(delta, ROrigin);
						drawingPanel.freshTextRotater();
						drawingPanel.repaint();
						drawingPanel.addHistory();
					}
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
					
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			  char c = e.getKeyChar();
			  
			  if (!Character.isDigit(c)) {
				   e.consume();
				   return;
			  }
		}

	}
	
}
