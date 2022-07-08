package dungeonmania.Entities.collectableentities;

import dungeonmania.Entity;

public class Sword extends Entity {
    
    private int attack;
    private int durability; 

    public Sword(int id, int xPos, int yPos, boolean interactable, boolean collidable, int attack, int durability) {
        super(id, xPos, yPos, interactable, collidable);
        this.attack = attack;
        this.durability = durability;
    }
    
    
}
