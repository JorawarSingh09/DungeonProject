package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Inventory;

public class Wood extends Entity implements Collectable, Inventory{
    
    public Wood(int id, int xPos, int yPos, boolean interactable, boolean collidable) {
        super(id, xPos, yPos, interactable, collidable);
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }
    
    
}
