package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Exit extends Entity {
    
    public Exit(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }  
    
}
