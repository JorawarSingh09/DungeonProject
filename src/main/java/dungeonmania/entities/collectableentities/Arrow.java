package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;

public class Arrow extends Entity implements Collectable, Storeable{
    
    public Arrow(int id, int xPos, int yPos, boolean interactable, boolean collidable) {
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

    public int getItemId() {
        return Integer.parseInt(getEntityId());
    }
    
}
