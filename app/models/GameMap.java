package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import util.GsonUtils;
import util.RichUtil;

import com.google.gson.annotations.Expose;

/**
 * The map of the game.
 * 
 * @author xiaoxiao
 * 
 */
public class GameMap {

    public final int height;

    public final int width;

    public final Cell[][] mapCells;
    
    public Game game;
    
    private List<EmptyCell> emptyCells;
    
    private static Random cellIndexSeed = new Random(System.currentTimeMillis());
    
    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        mapCells = new Cell[height][width];
    }

    public Cell getCell(int id) {
        int height = id / this.height;
        int width = id % this.height;
        return mapCells[height][width];
    }
    
    public void initEmptyCells() {
        if (emptyCells == null) {
            emptyCells = new ArrayList<EmptyCell>();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0;j < width; j++) {
                if (mapCells[i][j] instanceof EmptyCell) {
                    emptyCells.add((EmptyCell)mapCells[i][j]);
                }
            }
        }
    }
    
    /**
     * Randomly get an empty cell as a start cell for player.
     * @return The random empty cell.
     */
    public Cell randomStartCell() {
        int index = cellIndexSeed.nextInt(emptyCells.size());
        return emptyCells.get(index);
    }
    
    /**
     * Clear the owner of all the estate cell.
     * @param player The owner of the cell.
     */
    public void clearOwner(Player player) {
        List<Cell> cellsToClear = new ArrayList<Cell>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapCells[i][j] instanceof EstateCell) {
                    EstateCell currentEstate = (EstateCell) mapCells[i][j];
                    if (player.equals(currentEstate.owner)) {
                        currentEstate.owner = null;
                        cellsToClear.add(currentEstate);
                    }
                }
            }
        }
        this.game.changeCellOwner(null, cellsToClear.toArray(new Cell[0]));
    }

    public static String randomMap() {
        GameMap gameMap = MapGenerator.generateMap();
        return RichUtil.mapToJson(gameMap);
    }
    
}
