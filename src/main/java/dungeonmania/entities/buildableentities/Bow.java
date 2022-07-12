package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;

public class Bow extends Entity implements Buildable, Storeable, Durability, Attacking {
    
    private int bow_durability;
    private int battleBonus = 2;

    public Bow(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bow_durability) {
        super(id, xPos, yPos, interactable, collidable);
        this.bow_durability = bow_durability;
    }

    public Entity build() {
        // TODO Auto-generated method stub
        return null;
    }

    public void use() {
        // TODO Auto-generated method stub
        
    }

    public void reducedurability() {
        // TODO Auto-generated method stub
        
    }

    public void breakEntity() {
        // TODO Auto-generated method stub
        
    }

    public boolean isAdditive() {
        // The bow is multiplicative
        return false;
    }

    public int battleBonus() {
        return battleBonus;
    }
}
