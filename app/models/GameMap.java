package models;

import util.GsonUtils;
import util.RichUtil;

import com.google.gson.annotations.Expose;

/**
 * The map of the game.
 * @author xiaoxiao
 *
 */
public class GameMap {

	public final int height;
	
	public final int width;
	
	public final Cell[][] mapCells;
	
	public GameMap(int height, int width) {
		this.height = height;
		this.width = width;
		mapCells = new Cell[height][width];
	}

	public static String randomMap() {
		GameMap gameMap = MapGenerator.generateTestMap();
		return RichUtil.mapToJson(gameMap);
	}
}
