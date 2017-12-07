package utils;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import constant.GEConstants;
import constant.GEConstants.EAnchorTypes;

public class GEAnchorList {
	private ArrayList<Ellipse2D.Double> anchors;
	
	public GEAnchorList(){
		anchors = new ArrayList<Ellipse2D.Double>();
		for(int i=0;i<GEConstants.EAnchorTypes.values().length - 1 ; i++){
			anchors.add(new Ellipse2D.Double());
		}
	}
	
	public EAnchorTypes onAnchors(Point P){
		for(Ellipse2D ellipse : anchors){
			if(ellipse.contains(P)){
				return EAnchorTypes.values()[anchors.indexOf(ellipse)];
			}
		}
		return EAnchorTypes.NONE;
	}

	
	public ArrayList<Ellipse2D.Double> getAnchors() {
		return anchors;
	}

	public void draw(Graphics2D g2D){
		for(int i=0; i<EAnchorTypes.values().length - 1 ; i++){
			Ellipse2D.Double ellipse = anchors.get(i);
			g2D.setColor(GEConstants.ANCHOR_FILL_COLOR);
			g2D.fill(ellipse);
			g2D.setColor(GEConstants.ANCHOR_LINE_COLOR);
			g2D.draw(ellipse);
		}
	}
	
	public void setPosition(Rectangle r){
		int x = r.x;
		int y = r.y;
		int w = r.width;
		int h = r.height;
		int dx = GEConstants.ANCHOR_W / 2;
		int dy = GEConstants.ANCHOR_H / 2;
		
		anchors.get(EAnchorTypes.NW.ordinal()).setFrame(x-dx, y-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.NN.ordinal()).setFrame(x+w/2-dx, y-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.NE.ordinal()).setFrame(x+w-dx, y-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.WW.ordinal()).setFrame(x-dx, y+h/2-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.EE.ordinal()).setFrame(x+w-dx, y+h/2-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.SW.ordinal()).setFrame(x-dx, y+h-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.SS.ordinal()).setFrame(x+w/2-dx, y+h-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.SE.ordinal()).setFrame(x+w-dx, y+h-dy, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
		anchors.get(EAnchorTypes.RR.ordinal()).setFrame(x+w/2-dx, y-GEConstants.RR_OFFSET, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H);
	}
	
	
	public void resize(Rectangle bRect) {
		for(int i=0; i < GEConstants.EAnchorTypes.values().length-1; i++) {
			Point p = getPosition(bRect, GEConstants.EAnchorTypes.values()[i]);
			anchors.get(i).setFrame(new Rectangle(p.x, p.y, GEConstants.ANCHOR_W, GEConstants.ANCHOR_H));
		}
	}
	
	
	public Point getPosition(Rectangle bRect, EAnchorTypes ePointerState) {
		int x = bRect.x;
		int y = bRect.y;
		int w = bRect.width;
		int h = bRect.height;
		
		Point p = null;
		switch (ePointerState) {
			case NW: p = new Point(x, y); break;
			case WW: p = new Point(x, y+h/2); break;
			case SW: p = new Point(x, y+h); break;
			case NN: p = new Point(x+w/2, y); break;
			case SS: p = new Point(x+w/2, y+h); break;
			case NE: p = new Point(x+w, y); break;
			case EE: p = new Point(x+w, y+h/2); break;
			case SE: p = new Point(x+w, y+h); break;
			case RR: p = new Point(x+w/2, y-h/4); break;
			default: break;
		}
		
		p.x = p.x-GEConstants.ANCHOR_W/2;
		p.y = p.y-GEConstants.ANCHOR_H/2;
		
		return p;
	}
	
	
}
