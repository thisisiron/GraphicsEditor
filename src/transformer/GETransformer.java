package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

import constant.GEConstants;
import shapes.GEShape;

public abstract class GETransformer {
	protected GEShape shape;
	protected BasicStroke dashedLineStroke;
	
	public GETransformer(GEShape shape){
		this.shape = shape;
		float dashes[] = {GEConstants.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				GEConstants.DEFAULT_DASHEDLINE_WIDTH, //점선의 두께
				BasicStroke.CAP_ROUND, //선의 끝 모양
				BasicStroke.JOIN_ROUND, // 선의 교차점 모양
				10, dashes, 0);
	}

	
	
	
	public abstract void transformer(Graphics2D g2D, Point p);
	public abstract void init(Point P);
	
	
	
}
