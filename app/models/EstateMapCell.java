package models;

import com.google.gson.annotations.Expose;

public class EstateMapCell extends MapCell {

	public int level;
	
	public int price;
	
	public EstateMapCell(MapCell next, MapCell previous, int id, String roadName, String cellName, int price) {
		super(next, previous, id, roadName, cellName);
		this.level = 0;
		this.price = price;
	}
	
	public EstateMapCell(int id, String roadName, String cellName, int price) {
		super(id, roadName, cellName);
		this.level = 0;
		this.price = 0;
	}


}
