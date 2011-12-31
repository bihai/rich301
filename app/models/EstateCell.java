package models;

import com.google.gson.annotations.Expose;

public class EstateCell extends Cell {

	public int level;
	
	public int price;

	public Role owner;
	
	public EstateCell(Cell next, Cell previous, int id, String roadName, String cellName, int price) {
		super(next, previous, id, roadName, cellName);
		this.level = 0;
		this.price = price;
	}
	
	public EstateCell(int id, String roadName, String cellName, int price) {
		this(null, null, id, roadName, cellName, price);
	}


}
