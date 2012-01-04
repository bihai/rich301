package models;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EmptyCell extends Cell {

    public EmptyCell(int id, String roadName, String cellName) {
        super(id, roadName, cellName);
    }

    @Override
    public boolean canBuy() {
        return false;
    }

    @Override
    public boolean needPass() {
        return false;
    }
    
    public static class Serializer implements JsonSerializer<EmptyCell> {

        @Override
        public JsonElement serialize(EmptyCell src, Type type,
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

}
