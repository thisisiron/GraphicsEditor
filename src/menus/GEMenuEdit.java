package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constant.GEConstants.EEditMenuItems;
import frame.GEDrawingPanel;
import shapes.GEGroup;

public class GEMenuEdit extends JMenu {

	private GEDrawingPanel drawingPanel;
	private EditMenuHandler editMenuHandler;
	
	public GEMenuEdit(String s) {
		super(s);
		// TODO Auto-generated constructor stub
		for(EEditMenuItems btn : EEditMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.addActionListener(new EditMenuHandler());
			this.add(menuItem);
		}
	}
	
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	private void unGroup(){
		drawingPanel.unGroup();
	}
	
	private void group(){
		drawingPanel.group(new GEGroup());
	}
	
	
	
	private class EditMenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			switch(EEditMenuItems.valueOf(e.getActionCommand())){
			case Undo :
				drawingPanel.undo();
			case Redo :
				drawingPanel.redo();
//			case 삭제 :
//				drawingPanel.delete(); break;
//			case 잘라내기 :
//				drawingPanel.cut(); break;
//			case 복사 :
//				drawingPanel.copy(); break;
//			case 붙이기 :
//				drawingPanel.paste(); break;
			case Group :
				group(); break;
			case Ungroup :
				unGroup(); break;
			default:
				break;
			}
		}
	}
	
	
	

}
