package dungeonmania.Entities.collectableentities;

import dungeonmania.Entity;

public class InvisibilityPotion extends Entity {
    
    private int duration;

    public InvisibilityPotion(int id, int xPos, int yPos, boolean interactable, boolean collidable, int duration) {
        super(id, xPos, yPos, interactable, collidable);
        this.duration = duration;
    }

}
