package models;

import java.lang.reflect.Type;

import models.Player.Serializer;
import util.RichUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;

/**
 * The cell in map.
 * 
 * @author xiaoxiao
 * 
 */
public abstract class Cell {

    public Cell next;

    public Cell previous;

    public final int id;

    public final String roadName;

    public String cellName;

    public final int size;

    public String type;

    public Cell(Cell next, Cell previous, int id, String roadName,
            String cellName) {
        this.next = next;
        this.previous = previous;
        this.id = id;
        this.roadName = roadName;
        this.cellName = cellName;
        this.size = 1;
        this.type = this.getClass().getSimpleName();
    }

    public Cell(int id, String roadName, String cellName) {
        this(null, null, id, roadName, cellName);
    }

    /**
     * Pend the previous cell to the current cell. It would also set previous
     * cell id.
     * @param previous
     *            The previous cell.
     */
    public void pendPrevious(Cell previous) {
        this.previous = previous;
        previous.next = this;
    }

    /**
     * Pend the next cell to the current cell. It would also set the next cell
     * id.
     * 
     * @param next
     *            The next cell.
     */
    public void pendNext(Cell next) {
        this.next = next;
        next.previous = this;
    }

    public abstract boolean canBuy();

    public abstract boolean needPass();

    public static class Serializer implements JsonSerializer<Cell> {

        @Override
        public JsonElement serialize(Cell src, Type type,
                JsonSerializationContext ctx) {
            JsonObject cellObject = new JsonObject();
            cellObject.add("id", new JsonPrimitive(src.id));
            cellObject.add("nextId", new JsonPrimitive(src.next.id));
            cellObject.add("previousId", ctx.serialize(src.previous.id));
            cellObject.add("roadName", new JsonPrimitive(src.roadName));
            cellObject.add("cellName", new JsonPrimitive(src.cellName));
            cellObject.add("size", new JsonPrimitive(src.size));
            cellObject.add("type", new JsonPrimitive(src.type));
            return cellObject;
        }
    }
    
    static {
        RichUtil.builder.registerTypeAdapter(Cell.class, new Serializer());
    }
}
