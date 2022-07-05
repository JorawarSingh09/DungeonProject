package dungeonmania;

public class Entity {
    // EntityType type;
    int id;
    int xPos;
    int yPos;
    boolean interactable;
    boolean collidable;

    public Entity(int id, int xPos, int yPos, bool interactable, boolean collidable){
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos; 
        this.interactable = interactable;
        this.collidable = collidable;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
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

    public boolean isInteractable() {
        return this.interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public boolean isCollidable() {
        return this.collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

}
