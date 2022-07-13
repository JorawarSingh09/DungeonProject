package dungeonmania.entities;

import dungeonmania.util.Position;

public class Entity {
    int entityId;
    int xPos;
    int yPos;
    Position pos;
    boolean Collidable;
    boolean Interactable;

    public Entity(int entityId, int xPos, int yPos, boolean interactable, boolean collidable) {
        this.entityId = entityId;
        this.xPos = xPos;
        this.yPos = yPos;
        setPosition();
        this.Interactable = interactable;
        this.Collidable = collidable;
    }

    public String getType() {
        return "Entity";
    }

    public String getEntityId() {
        return Integer.toString(this.entityId);
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getXPos() {
        return this.xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
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

    public void moveTo(int xPos, int yPos) {
        setXPos(xPos);
        setYPos(yPos);
        setPosition();
    }

    public void setPosition() {
        this.pos = new Position(this.xPos, this.yPos);
    }

    public Position getPosition() {
        return pos;
    }

}
