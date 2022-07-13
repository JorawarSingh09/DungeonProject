package dungeonmania.entities.buildableentities;

import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;

public class Bow extends Entity implements Buildable, Storeable, Durability, Attacking{
    
    private int bow_durability;

    // Bow will never have a pos, set to INF? can only ever appear in inventory
    // bow(String id, int bow_durability) default values for the rest
    //

    // public Bow(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bow_durability) {
    //     super(id, xPos, yPos, interactable, collidable);
    //     this.bow_durability = bow_durability;
    // }

    public Bow(int id, int bow_durability){
        super(id, 0, 0, false, false);
        this.bow_durability = bow_durability;
    }
    
    /**
     * add bow object to player inventory, remove items from player inventory used to make bow
     * @param player
     * @return
     */   
    @Override
    public void build(Player player) {
        player.addInventoryItem(this);
        consumeItems(player);
    }

    /**
     * remove items to make a bow, 3 arrows one wood
     */
    @Override
    public void consumeItems(Player player) {
        List<Storeable> inventory = player.getInventoryItems();
        boolean wood = false;
        int arrows = 3;
        for(Storeable item : player.getInventoryItems()){
            Entity newItem = (Entity) item;
            if(newItem.getType().equals("Wood") && !wood){
                player.removeInventoryItem(newItem);
            }

            if(newItem.getType().equals("Arrow") && arrows > 0){
                player.removeInventoryItem(newItem);
            }
        }
        
    }

    @Override
    public void use() {
        //dont really have a use for bow        
    }

    /**
     * reduce durability after each round 
     */
    @Override
    public void reducedurability() {
        // TODO Auto-generated method stub
        //
        
    }

    @Override
    public void breakEntity(Player player) {
        player.removeInventoryItem(this);
        
    }

    @Override
    public Boolean isAdditive() {
        // TODO Auto-generated method stub
        return false;
    }
}
