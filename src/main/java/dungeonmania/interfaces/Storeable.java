package dungeonmania.interfaces;

import com.google.gson.JsonObject;

public interface Storeable {
    public int getItemId();
    public String getType();
    public JsonObject getJson();
}
