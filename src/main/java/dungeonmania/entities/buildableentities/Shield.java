package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;

public class Shield extends Entity implements Buildable, Storeable, Defending, Durability{

    private int durability;
    private int defence;
    
    public Shield(int id, int xPos, int yPos, boolean interactable, boolean collidable, int durability, int defence) {
        super(id, xPos, yPos, interactable, collidable);
        this.durability = durability;
        this.defence = defence;
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
        durability -= 1;
        
    }

    @Override
    public void breakEntity() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reduceEnemyAttack() {
        // TODO Auto-generated method stub
        
    }

    public int battleBonus() {
        return defence;
    }

    public int getItemId() {
        return getEntityId();
    }

}
