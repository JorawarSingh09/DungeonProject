package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.interfaces.Storeable;

public class InvisibilityPotion extends Entity implements Collectable, Storeable, Regenerative {
    
    private int duration;

    public InvisibilityPotion(int id, int xPos, int yPos, boolean interactable, boolean collidable, int duration) {
        super(id, xPos, yPos, interactable, collidable);
        this.duration = duration;
    }

    @Override
    public void use() {
        duration -= 1;
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }

    public int getItemId() {
        return getEntityId();    }

    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration -= 1;
    }

    public String getType() {
        return "invisibility_potion";
    }

}
