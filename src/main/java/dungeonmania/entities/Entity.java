package dungeonmania.entities;

import dungeonmania.util.Position;

public class Entity {
    String type;
    int entityId;
    int xPos;
    int yPos;
    Position pos;
    boolean isCollidable;
    boolean isInteractable;

    public Entity(int entityId, int xPos, int yPos, boolean interactable, boolean collidable){
        this.entityId = entityId;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isInteractable = interactable;
        this.isCollidable = collidable;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEntityId() {
        return this.entityId;
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

    public boolean isIsCollidable() {
        return this.isCollidable;
    }

    public void setIsCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    public boolean isIsInteractable() {
        return this.isInteractable;
    }

    public void setIsInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public void moveTo(Position pos){
        // stuff goes here
    }
}
