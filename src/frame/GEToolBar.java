package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constant.GEConstants;
import constant.GEConstants.EToolBarButtons;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelect;

public class GEToolBar extends JToolBar {
	
	private GEDrawingPanel drawingPanel;
	private GEToolBarHandler toolBarHandler;
	
	
	public GEToolBar(String label) {
		super(label);
		toolBarHandler = new GEToolBarHandler();
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rButton = null; //�ʱ�ȭ ��������Ѵ�.
		
		for(EToolBarButtons btn : EToolBarButtons.values()) {
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
			rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			rButton.addActionListener(toolBarHandler);
			rButton.setActionCommand(btn.toString());
			this.add(rButton);
			buttonGroup.add(rButton);
		}
	}
	//�̺�Ʈ�ҽ��� ������ư
	//�̺�Ʈ��鷯�� innerŬ������ �ۼ�
	
	public void init(GEDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.clickDefaultButton();
	}

	private void clickDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(EToolBarButtons.Rectangle.ordinal());
		rButton.doClick();
	}
	
	
	private class GEToolBarHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JRadioButton rButton = (JRadioButton)e.getSource();
			if(rButton.getActionCommand().equals(EToolBarButtons.Rectangle.name())) {
				drawingPanel.setCurrentShape(new GERectangle());
			} else if(rButton.getActionCommand().equals(EToolBarButtons.Ellipse.name())){
				drawingPanel.setCurrentShape(new GEEllipse());
			} else if(rButton.getActionCommand().equals(EToolBarButtons.Line.name())){
				drawingPanel.setCurrentShape(new GELine());
			}  else if(rButton.getActionCommand().equals(EToolBarButtons.Polygon.name())){
				drawingPanel.setCurrentShape(new GEPolygon());
			}else if(rButton.getActionCommand().equals(EToolBarButtons.Select.name())){
				drawingPanel.setCurrentShape(new GESelect());
			}
			
		}
		
	}
	
}
