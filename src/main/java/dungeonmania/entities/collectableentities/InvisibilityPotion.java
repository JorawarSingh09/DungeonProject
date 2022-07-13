package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;

public class InvisibilityPotion extends Entity implements Collectable, Storeable{
    
    private int duration;

    public InvisibilityPotion(int id, int xPos, int yPos, boolean interactable, boolean collidable, int duration) {
        super(id, xPos, yPos, interactable, collidable);
        this.duration = duration;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }

    public int getItemId() {
        return Integer.parseInt(getEntityId());
    }
    
}
