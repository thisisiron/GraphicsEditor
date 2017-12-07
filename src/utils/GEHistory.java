package utils;

import java.util.ArrayList;
import java.util.Vector;

import shapes.GEShape;

public class GEHistory {
	private Vector<ArrayList<GEShape>> history;
	private int layerNum; //���� ���̾�
	
	public GEHistory(){
		history = new Vector<ArrayList<GEShape>>();
		layerNum = 0;
	}
	

	public void push(ArrayList<GEShape> shapeList){
		System.out.println("history push");
		
		ArrayList<GEShape> temp = new ArrayList<GEShape>();
		if(layerNum < history.size()){
			for(int i = history.size() -1; layerNum <= i; i--){
				history.remove(i);
			}
		}
		
		for(int i =0; i<shapeList.size(); i++){
			temp.add(shapeList.get(i).deepCopy());
		}
		history.add(temp);
		layerNum++; 
		System.out.println("history size : " + history.size() + ", num : " + layerNum);
	}
	
	public ArrayList<GEShape> undo(){
		if(0 > layerNum-1){
			System.out.println("undo error");
//			System.out.println("�ձ�� ����");
			return new ArrayList<GEShape>();
		}
		layerNum = layerNum-1;
		System.out.println("history size : " + history.size() + ", num : " + layerNum);
		if(layerNum == 0){
//			System.out.println("0 �̴�");
			return new ArrayList<GEShape>();
		}
		else {
//			System.out.println("�ڷ� ���ư�");
			ArrayList<GEShape> temp = new ArrayList<GEShape>();
			ArrayList<GEShape> base = history.get(layerNum - 1); 
			for(int i =0; i<base.size(); i++){
				temp.add(base.get(i).deepCopy());
			}
			return temp;
		}
	}
	
	public ArrayList<GEShape> redo(){
		ArrayList<GEShape> temp = new ArrayList<GEShape>();
		if(history.size() < layerNum+1){
			System.out.println("redo error");
			if(history.size() == 0){
				return new ArrayList<GEShape>();
			}
			else {
				ArrayList<GEShape> base = history.get(layerNum - 1); 
				for(int i =0; i<base.size(); i++){
					temp.add(base.get(i).deepCopy());
				}
				return temp;
			}
		}
		layerNum = layerNum+1;
		System.out.println("history size : " + history.size() + ", num : " + layerNum);
		ArrayList<GEShape> base = history.get(layerNum - 1); 
		for(int i =0; i<base.size(); i++){
			temp.add(base.get(i).deepCopy());
		}
		return temp;
	}
	
	public int getLayerNum(){
		return layerNum;
	}
	
	public void clear(){
		history.clear();
	}

}
