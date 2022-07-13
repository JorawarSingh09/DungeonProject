package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Door extends Entity {
    
    public Door(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }  
    
}
