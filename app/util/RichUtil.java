package util;

import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Cell;
import models.GameMap;

public class RichUtil {

    /**
     * Retrieve the id of a cell in the map.
     * @param gameMap The given map.
     * @param cellHeight The height index of the cell in the map.
     * @param cellWidth The width index of the cell in the map.
     * @return The id of the cell.
     */
    public static int retrieveCellId(GameMap gameMap, int cellHeight, int cellWidth) {
        return gameMap.height * cellHeight + cellWidth;
    }

    /**
     * You know. This method would turn a {@link GameMap} object into a json string.
     * @param gameMap The given map.
     * @return The json string represents the map object.
     */
    public static String mapToJson(GameMap gameMap) {
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new GsonUtils.GsonPropertyExclusionStrategy());
        Gson gson = builder.create();
        return gson.toJson(gameMap);
    }

    /**
     * Of cause you know what this method means.
     * @return The value of the dice.
     */
    public static int randomDice() {
       Random diceRandom = new Random(System.currentTimeMillis());
       return diceRandom.nextInt(6) + 1;
    }
    
    /**
     * Randomly choose a cell in the map.
     * The cell could not be an empty one.
     * @param gameMap The given map.
     * @return The random none empty cell.
     */
    public static Cell randomCell(GameMap gameMap) {
        Random heightRandom = new Random(System.currentTimeMillis());
        Random widthRandom = new Random(System.currentTimeMillis());
        while(true) {
            int height = heightRandom.nextInt(gameMap.height);
            int width = widthRandom.nextInt(gameMap.width);
            if (gameMap.mapCells[height][width] != null) {
                return gameMap.mapCells[height][width];
            }
        }
    }
}
