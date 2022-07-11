package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Inventory;

public class Bow extends Entity implements Buildable, Inventory, Durability, Attacking{
    
    private int bow_durability;

    public Bow(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bow_durability) {
        super(id, xPos, yPos, interactable, collidable);
        this.bow_durability = bow_durability;
    }

    @Override
    public Entity build() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
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
}