package models;

import com.google.gson.annotations.Expose;

/**
 * The cell in map.
 * @author xiaoxiao
 *
 */
public abstract class MapCell {

	public MapCell next;
	
	public MapCell previous;
	
	public final int id;
	
	public final String roadName;
	
	public String cellName;
	
	public final int size;
	
	public int previousId;
	
	public int nextId;
	
	public String type;
	
	public MapCell(MapCell next, MapCell previous, int id, String roadName, String cellName) {
		this.next = next;
		this.previous = previous;
		this.id = id;
		this.roadName = roadName;
		this.cellName = cellName;
		this.size = 1;
		this.type = this.getClass().getSimpleName();
	}
	
	public MapCell(int id, String roadName, String cellName) {
		this(null, null, id, roadName, cellName);
	}
	
	/**
	 * Pend the previous cell to the current cell.
	 * It would also set previous cell id.
	 * @param previous The previous cell.
	 */
	public void pendPrevious(MapCell previous) {
		this.previous = previous;
		this.previousId = previous.id;
		previous.next = this;
		previous.nextId = this.id;
	}
	
	/**
	 * Pend the next cell to the current cell.
	 * It would also set the next cell id.
	 * @param next The next cell.
	 */
	public void pendNext(MapCell next) {
		this.next = next;
		this.nextId = next.id;
		next.previous = this;
		next.previousId = this.id;
	}
}
