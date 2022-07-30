package dungeonmania.entities;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public abstract class Entity {
    private int entityId;
    private Position position;
    private boolean Collidable;
    private boolean Interactable;

    public Entity(int entityId, Position position, boolean interactable, boolean collidable) {
        this.entityId = entityId;
        this.position = position;
        this.Interactable = interactable;
        this.Collidable = collidable;
    }

    public abstract String getType();

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public boolean isCollidable() {
        return this.Collidable;
    }

    public void setCollidable(boolean isCollidable) {
        this.Collidable = isCollidable;
    }

    public boolean isInteractable() {
        return this.Interactable;
    }

    public void setInteractable(boolean isInteractable) {
        this.Interactable = isInteractable;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", this.entityId);
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        return entityJSON;
    }

    public EntityResponse createEntityResponse() {
        return new EntityResponse(Integer.toString(getEntityId()), getType(), getPosition(), isInteractable());
    }
}
