package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelect;

public class GEToolBar extends JToolBar{
	
	private ButtonGroup buttonGroup;
	private GEDrawingPanel drawingPanel;
	private ToolBarHandler toolBarHandler;
	
	public GEToolBar(String label) {
		super(label);
		buttonGroup = new ButtonGroup();
		toolBarHandler = new ToolBarHandler();
		JRadioButton rButton;
		for(EToolBarButtons button : EToolBarButtons.values()){
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + button.toString() + GEConstants.TOOLBAR_BTN));
			rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + button.toString() + GEConstants.TOOLBAR_BTN_SLT));
			rButton.setActionCommand(button.name());
			rButton.addActionListener(toolBarHandler);
//			System.out.println("button.name : " + button.name() + ", button.toString() : " + button.toString());
			this.add(rButton);
			buttonGroup.add(rButton);
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
		this.clickDefault();
	}
	
	private void clickDefault(){
//		System.out.println("clickDefault");
		JRadioButton rButton = (JRadioButton)this.getComponent(EToolBarButtons.Rectangle.ordinal());
		rButton.doClick();
	}
	
	private class ToolBarHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton rButton = (JRadioButton)e.getSource();
			if(rButton.getActionCommand().equals(EToolBarButtons.Rectangle.toString())){
//				System.out.println("actionPerformed");
				drawingPanel.setCurrentShape(new GERectangle());
			}else if(rButton.getActionCommand().equals(EToolBarButtons.Ellipse.toString())){
				drawingPanel.setCurrentShape(new GEEllipse());
			}else if(rButton.getActionCommand().equals(EToolBarButtons.Line.toString())){
				drawingPanel.setCurrentShape(new GELine());
			}else if(rButton.getActionCommand().equals(EToolBarButtons.Polygon.toString())){
				drawingPanel.setCurrentShape(new GEPolygon());
			}else if(rButton.getActionCommand().equals(EToolBarButtons.Select.toString())){
				drawingPanel.setCurrentShape(new GESelect());
			}
		}
		
	}
}
