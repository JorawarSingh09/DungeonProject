package dungeonmania.Entities.collectableentities;

import dungeonmania.Entity;

public class InvincibilityPotion extends Entity {
    
    private int duration;

    public InvincibilityPotion(int id, int xPos, int yPos, boolean interactable, boolean collidable, int duration) {
        super(id, xPos, yPos, interactable, collidable);
        this.duration = duration;
    }
    
}
