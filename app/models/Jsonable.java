package models;

import com.google.gson.JsonElement;

/**
 * Interface that can be serialize to JSON.
 * 
 * @author GuoLin
 *
 */
public interface Jsonable {

    public JsonElement toJson();

}
