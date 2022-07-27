package dungeonmania.interfaces;

import com.google.gson.JsonObject;

public interface Regenerative {
    public int getRemainingDuration();
    public void decrementDuration();
    public int getItemId();
    public JsonObject getJson();
}
