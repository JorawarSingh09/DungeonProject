package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;

public class Wall extends Entity {
    
    public Wall(int id, int xPos, int yPos, boolean interactable, boolean collidable) {
        super(id, xPos, yPos, interactable, collidable);
    }  
    
}
