package dungeonmania.interfaces;

import com.google.gson.JsonObject;

import dungeonmania.response.models.ItemResponse;

public interface Storeable {
    public int getItemId();

    public String getType();

    public JsonObject getJson();

    public default ItemResponse createItemResponse() {
        return new ItemResponse(Integer.toString(getItemId()), getType());
    };
}
