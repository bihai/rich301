package models;

import com.google.gson.annotations.Expose;

public class EstateMapCell extends MapCell {

	public int level;
	
	public int price;

	public Role owner;
	
	public EstateMapCell(MapCell next, MapCell previous, int id, String roadName, String cellName, int price) {
		super(next, previous, id, roadName, cellName);
		this.level = 0;
		this.price = price;
	}
	
	public EstateMapCell(int id, String roadName, String cellName, int price) {
		this(null, null, id, roadName, cellName, price);
	}


}
