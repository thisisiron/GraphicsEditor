package utils;

import java.awt.Cursor;
import java.util.ArrayList;

// NW NN NE WW EE SW SS SE RR NONE
public class GECursorManager extends ArrayList<Cursor> {
	public GECursorManager() {
		add(new Cursor(Cursor.NW_RESIZE_CURSOR));
		add(new Cursor(Cursor.N_RESIZE_CURSOR));
		add(new Cursor(Cursor.NE_RESIZE_CURSOR));
		add(new Cursor(Cursor.W_RESIZE_CURSOR));
		add(new Cursor(Cursor.E_RESIZE_CURSOR));
		add(new Cursor(Cursor.SW_RESIZE_CURSOR));
		add(new Cursor(Cursor.S_RESIZE_CURSOR));
		add(new Cursor(Cursor.SE_RESIZE_CURSOR));
		add(new Cursor(Cursor.HAND_CURSOR));
		add(new Cursor(Cursor.MOVE_CURSOR));
	}
}
