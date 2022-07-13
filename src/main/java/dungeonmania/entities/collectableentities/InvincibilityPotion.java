package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.interfaces.Storeable;

public class InvincibilityPotion extends Entity implements Storeable, Collectable, Regenerative {
    
    private int duration;

    public InvincibilityPotion(int id, int xPos, int yPos, boolean interactable, boolean collidable, int duration) {
        super(id, xPos, yPos, interactable, collidable);
        this.duration = duration;
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void use() {
        duration -= 1;
    }

    public int getItemId() {
        return getEntityId();
    }

    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration -= 1;
    }
    
}
