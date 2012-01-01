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

public class EstateCell extends Cell {

    public int level;

    public int price;

    public Player owner;

    public EstateCell(Cell next, Cell previous, int id, String roadName,
            String cellName, int price) {
        super(next, previous, id, roadName, cellName);
        this.level = 0;
        this.price = price;
    }

    public EstateCell(int id, String roadName, String cellName, int price) {
        this(null, null, id, roadName, cellName, price);
    }

    @Override
    public boolean canBuy() {
        return owner == null;
    }
    
    /**
     * Change the owner of the cell to the given player.
     * An {@link OwnerChangeEvent} would be generated.
     * @param player The new owner of the cell.
     */
    public void changeOwner(Player player) {
        this.owner = player;
    }
    
    @Override
    public boolean needPass() {
        return true;
    }

    public static class Serializer implements JsonSerializer<EstateCell> {

        @Override
        public JsonElement serialize(EstateCell src, Type type,
                JsonSerializationContext ctx) {
            JsonObject cellObject = new JsonObject();
            cellObject.add("id", new JsonPrimitive(src.id));
            cellObject.add("nextId", new JsonPrimitive(src.next.id));
            cellObject.add("previousId", ctx.serialize(src.previous.id));
            cellObject.add("roadName", new JsonPrimitive(src.roadName));
            cellObject.add("cellName", new JsonPrimitive(src.cellName));
            cellObject.add("size", new JsonPrimitive(src.size));
            cellObject.add("type", new JsonPrimitive(src.type));
            cellObject.add("level", new JsonPrimitive(src.level));
            cellObject.add("price", new JsonPrimitive(src.price));
            if (src.owner != null) {
                cellObject.add("owner", new JsonPrimitive(src.owner.name));
            }
            return cellObject;
        }
    }
    
    static {
        RichUtil.builder.registerTypeAdapter(EstateCell.class, new Serializer());
    }
}
