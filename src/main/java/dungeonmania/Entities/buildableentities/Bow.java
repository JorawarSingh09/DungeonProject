package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;

public class Bow extends Entity {
    
    private int bow_durability;

    public Bow(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bow_durability) {
        super(id, xPos, yPos, interactable, collidable);
        this.bow_durability = bow_durability;
    }
}
