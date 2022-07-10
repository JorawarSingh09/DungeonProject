package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;

public class Shield extends Entity {

    private int durability;
    private int defence;
    
    public Shield(int id, int xPos, int yPos, boolean interactable, boolean collidable, int durability, int defence) {
        super(id, xPos, yPos, interactable, collidable);
        this.durability = durability;
        this.defence = defence;
    }
    
}
