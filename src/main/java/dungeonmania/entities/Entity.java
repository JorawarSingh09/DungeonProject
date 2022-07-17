package dungeonmania.entities;

import dungeonmania.util.Position;
import dungeonmania.enums.EntityString;

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

}
