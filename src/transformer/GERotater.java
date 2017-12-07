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
		// ó�� ���� ������ �̵��� ��(���콺 �����ͷ� ������ ����)�� ���մϴ�
				double theta2 = theta - Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x);
				
				// affineTransformfineTrasform���� ȸ����ȯ��Ŵ
				affineTransform.setToRotation(theta2, ROrigin.x, ROrigin.y);
				//shape.getAnchor().rotate(theta2, ROrigin);
				shape.getAnchorList().resize(shape.getMyShape().getBounds());
				
				if(shape instanceof GEGroup) { // �׷��̸� ���� ���Һ���
					GEShape temp;
					for(int i=0; i < shapelist.size(); i++) {
						temp = shapelist.get(i);
						temp.setMyShape(affineTransform.createTransformedShape(temp.getMyShape()));
						shape.setMyShape(shape.getMyShape().getBounds().createUnion(temp.getMyShape().getBounds()));
					}
				} else {
					shape.setMyShape(affineTransform.createTransformedShape(shape.getMyShape())); // �ڽ� ����
				}
				
				//shape.setShape(affineTransform.createTransformedShape(shape.getShape())); // �ڽ� ����
				theta = Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x); // �̵��� �� ����
		

	}

	@Override
	public void init(Point p) {
		// ȸ���� ������ �߽��� ���մϴ�
				ROrigin = new Point2D.Double(
						shape.getMyShape().getBounds().
						getCenterX(), shape.getMyShape().
						getBounds().getCenterY());
				//  ���콺 �����Ϳ� �߽����� �̷�� ���� ���մϴ�
				theta = Math.atan2(ROrigin.y - p.y, p.x - ROrigin.x);
		// TODO Auto-generated method stub

	}

}
