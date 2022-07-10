package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Inventory;

public class Sword extends Entity implements Inventory, Attacking, Durability, Collectable{
    
    private int attack;
    private int durability; 

    public Sword(int id, int xPos, int yPos, boolean interactable, boolean collidable, int attack, int durability) {
        super(id, xPos, yPos, interactable, collidable);
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    public void reducedurability() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void breakEntity() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Boolean isAdditive() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }
    
    
}
