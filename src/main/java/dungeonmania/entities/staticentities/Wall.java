package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;

public class Wall extends Entity {
    
    public Wall(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }   
    
}
