package dungeonmania.entities;

import dungeonmania.util.Position;

public class Entity {
    int entityId;
    Position position;
    boolean Collidable;
    boolean Interactable;

    public Entity(int entityId, Position position, boolean interactable, boolean collidable) {
        this.entityId = entityId;
        this.position = position;
        this.Interactable = interactable;
        this.Collidable = collidable;
    }

    public String getType() {
        return "Entity";
    }

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

    // public void moveTo(int xPos, int yPos) {
    //     setXPos(xPos);
    //     setYPos(yPos);
    //     setPosition();
    // }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}
