package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EFileMenuItems;

public class GEMenuFile extends JMenu{

	public GEMenuFile(String label){
		super(label);
		JMenuItem item;
		for(EFileMenuItems btn : EFileMenuItems.values() ){
			item = new JMenuItem(btn.toString());
			add(item);
		}
	}
}
