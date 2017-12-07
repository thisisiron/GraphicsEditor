package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import shapes.GEGroup;
import shapes.GEShape;

public class GERotater extends GETransformer {
	protected Point2D.Double ROrigin;
	protected double theta;
	private boolean moved;
	public GERotater(GEShape shape) {
		super(shape);
		if(shape instanceof GEGroup) {
			shapelist = new ArrayList<GEShape>();
			for(GEShape subshape : ((GEGroup) shape).getChildList()) {
				shapelist.add(subshape);
			}
		}
		affineTransform = new AffineTransform();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isMoved(){
		return moved;
	}
	
	public void setMove(boolean moved){
		this.moved = moved;
//		moved = false;
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		// 처음 구한 각에서 이동한 각(마우스 포인터로 움직인 각도)을 구합니다
				double theta2 = theta - Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x);
				
				// affineTransformfineTrasform으로 회전변환시킴
				affineTransform.setToRotation(theta2, ROrigin.x, ROrigin.y);
				//shape.getAnchor().rotate(theta2, ROrigin);
				shape.getAnchorList().resize(shape.getMyShape().getBounds());
				
				if(shape instanceof GEGroup) { // 그룹이면 내부 원소변경
					GEShape temp;
					for(int i=0; i < shapelist.size(); i++) {
						temp = shapelist.get(i);
						temp.setMyShape(affineTransform.createTransformedShape(temp.getMyShape()));
						shape.setMyShape(shape.getMyShape().getBounds().createUnion(temp.getMyShape().getBounds()));
					}
				} else {
					shape.setMyShape(affineTransform.createTransformedShape(shape.getMyShape())); // 자신 변경
				}
				
				//shape.setShape(affineTransform.createTransformedShape(shape.getShape())); // 자신 변경
				theta = Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x); // 이동한 각 저장
		

	}

	@Override
	public void init(Point p) {
		// 회전할 도형의 중심을 구합니다
				ROrigin = new Point2D.Double(
						shape.getMyShape().getBounds().
						getCenterX(), shape.getMyShape().
						getBounds().getCenterY());
				//  마우스 포인터와 중심점이 이루는 각을 구합니다
				theta = Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x);
		// TODO Auto-generated method stub

	}

}
