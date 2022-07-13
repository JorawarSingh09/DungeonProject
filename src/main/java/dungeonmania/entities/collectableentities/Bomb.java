package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;

public class Bomb extends Entity implements Collectable, Storeable{
    private int bomb_radius;
    private boolean inInventory;
    
    public Bomb(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bomb_radius) {
        super(id, xPos, yPos, interactable, collidable);
        this.bomb_radius = bomb_radius;
        this.inInventory = false;
    }

    public void explode() {
        //check if explodable, and if so explode 
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }

    /**
     * drop da bomb
     */
    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return "bomb";
    }

}
