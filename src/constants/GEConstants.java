package constants;

import java.awt.Color;

public class GEConstants {
		//GEMainFrame
		public static final int WIDTH_MAINFRAME = 400;
		public static final int HEIGHT_MAINFRAME = 600;
		public static final String TITLE_MAINFRAME = "Graphics Editor";

		//GEMenu
		public static final String TITLE_FILEMENU = "파일";
		public static final String TITLE_EDITMENU = "편집";
		public static final String TITLE_COLORMENU = "컬러";
		
		//GEmenuItems
		public static enum EFileMenuItems{새로만들기, 열기, 저장, 다른이름으로저장, 종료};
		public static enum EEditMenuItems{Undo, Redo, 삭제, 잘라내기, 복사, 붙이기, Group, Ungroup};
		public static enum EColorMenuItems{SetLineColor, ClearLineColor, SetFillColor, ClearFillColor};

		//GEToolBar
		public static String TITLE_TOOLBAR = "shape tools";
		public static enum EToolBarButtons{Select, Rectangle, Line, Ellipse, Polygon}
		public static String IMG_URL = "images/";
		public static String TOOLBAR_BTN = ".gif";
		public static String TOOLBAR_BTN_SLT = "SLT.gif";
		
		//GEDrawingPanel
		public static final Color FOREGROUND_COLOR = Color.black;
		public static final Color BACKGROUND_COLOR = Color.white;
		public static final Color LINE_COLOR_DEFAULT = Color.black;
		public static final Color FILL_COLOR_DEFAULT = Color.white;
		public static enum EState {Idle, TwoPointsDrawing, NPointsDrawing, Moving, Resizing, Selecting, Rotating, Shift}
		
		//GEAnchorList
		public static final int ANCHOR_W = 6;
		public static final int ANCHOR_H = 6;
		public static final int RR_OFFSET = 30;
		public static final Color ANCHOR_LINECOLOR = Color.black;
		public static final Color ANCHOR_FILLCOLOR = Color.white;
		public static enum EAnchorTypes {NW, NN, NE, WW, EE, SW, SS, SE, RR, NONE}
		
		//GEMenuColor
		public static final String LINECOLOR_TITLE = "Select line color";
		public static final String FILLCOLOR_TITLE = "Select fill color";
		
		//GETransformer
		public final static int DEFAULT_DASH_OFFSET = 4;
		public final static int DEFAULT_DASHEDLINE_WIDTH = 1;
		
		//GETextRotater
		public final static int TEXT_POS_X_OFFSET = 10;
		public final static int TEXT_POS_Y_OFFSET = 5;
		public final static int TEXT_WIDTH_LENGTH = 40;
		public final static int TEXT_HEIGHT_LENGTH = 20;
}
