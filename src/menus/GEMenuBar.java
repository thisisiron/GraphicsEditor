package menus;

import javax.swing.JMenuBar;

import constants.GEConstants;
import frames.GEDrawingPanel;

public class GEMenuBar extends JMenuBar{
	private GEMenuFile fileMenu;
	private GEMenuEdit editMenu;
	private GEMenuColor colorMenu;
	public GEMenuBar() {
		super();
		fileMenu = new GEMenuFile(GEConstants.TITLE_FILEMENU);
		editMenu = new GEMenuEdit(GEConstants.TITLE_EDITMENU);
		colorMenu = new GEMenuColor(GEConstants.TITLE_COLORMENU);
		this.add(fileMenu);
		this.add(editMenu);
		this.add(colorMenu);
	}
	
	public void init(GEDrawingPanel drawingPanel){
		colorMenu.init(drawingPanel);
		editMenu.init(drawingPanel);
	}
}
