package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constant.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	//static 필드말고 getInstance에서 생성할 수 없을까?
	private static GEMainFrame uniqueMainFrame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	
	private GEDrawingPanel drawingPanel;
	private GEMenuBar menuBar;
	private GEToolBar toolbar;
	
	private GEMainFrame(String titleMainframe) {
		// TODO Auto-generated constructor stub
		super(titleMainframe);
		drawingPanel = new GEDrawingPanel();
		this.add(drawingPanel);
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		toolbar = new GEToolBar(GEConstants.TITLE_TOOLBAR);
		this.add(BorderLayout.NORTH, toolbar);
		
	}
	
	public static GEMainFrame getInstance() {
		return uniqueMainFrame;
	}
	
	public void init() {
		toolbar.init(drawingPanel);
		menuBar.init(drawingPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GEConstants.WIDTH_MAINFRAME, GEConstants.HEIGHT_MAINFRAME);
		this.setVisible(true);
	}
}
