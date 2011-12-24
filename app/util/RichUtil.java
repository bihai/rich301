package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Map;

public class RichUtil {

	/**
	 * Retrieve the id of a cell in the map.
	 * @param gameMap The given map.
	 * @param cellHeight The height index of the cell in the map.
	 * @param cellWidth The width index of the cell in the map.
	 * @return The id of the cell.
	 */
	public static int retrieveCellId(Map gameMap, int cellHeight, int cellWidth) {
		return gameMap.height * cellHeight + cellWidth;
	}
	
	/**
	 * You know.
	 * This method would turn a {@link Map} object into a json string.
	 * @param gameMap The given map.
	 * @return The json string represents the map object.
	 */
	public static String mapToJson(Map gameMap) {
		GsonBuilder builder = new GsonBuilder();
		builder.setExclusionStrategies(new GsonUtils.GsonPropertyExclusionStrategy());
		Gson gson = builder.create();
		return gson.toJson(gameMap);
	}
}
