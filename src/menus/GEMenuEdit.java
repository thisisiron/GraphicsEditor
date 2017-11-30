package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constant.GEConstants.EEditMenuItems;

public class GEMenuEdit extends JMenu {

	public GEMenuEdit(String s) {
		super(s);
		// TODO Auto-generated constructor stub
		for(EEditMenuItems btn : EEditMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
		}
	}

}
