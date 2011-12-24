package models;

import util.GsonUtils;
import util.RichUtil;

import com.google.gson.annotations.Expose;

/**
 * The map of the game.
 * @author xiaoxiao
 *
 */
public class Map {

	public final int height;
	
	public final int width;
	
	public final MapCell[][] mapCells;
	
	public Map(int height, int width) {
		this.height = height;
		this.width = width;
		mapCells = new MapCell[height][width];
	}

	public static String randomMap() {
		Map gameMap = MapGenerator.generateTestMap();
		return RichUtil.mapToJson(gameMap);
	}
}
