package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constant.GEConstants;
import constant.GEConstants.EColorMenuItems;
import frame.GEDrawingPanel;

public class GEMenuColor extends JMenu {

	private GEDrawingPanel drawingPanel;
	private ColorMenuHandler colorMenuHandler;
	
	public GEMenuColor(String s) {
		super(s);
		colorMenuHandler = new ColorMenuHandler();
		
		for(EColorMenuItems btn : EColorMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.addActionListener(colorMenuHandler);
			menuItem.setActionCommand(btn.toString());
			this.add(menuItem);
		}
	}
	
	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, GEConstants.TITLE_LINECOLOR, null);
		drawingPanel.setLineColor(lineColor);
	}
	
	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, GEConstants.TITLE_FILLCOLOR, null);
		drawingPanel.setFillColor(fillColor);
	}
	
	private void clearLineColor() {
		drawingPanel.setLineColor(GEConstants.DEFAULT_LINE_COLOR);
	}
	
	private void clearFillColor() {
		drawingPanel.setFillColor(GEConstants.DEFAULT_FILL_COLOR);
	}
	
	public void init(GEDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	
	
	private class ColorMenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(EColorMenuItems.valueOf(e.getActionCommand())) {
			case SetLineColor:
				setLineColor();
				break;
			case SetFillColor:
				setFillColor();
				break;
			case ClearLineColor:
				clearLineColor();
				break;
			case ClearFillColor:
				clearFillColor();
				break;
			}
			
		}
		
	}
}
