package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Static;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Bomb extends Entity implements Collectable, Storeable, Static{
    private int bomb_radius;
    private boolean inInventory;
    
    public Bomb(int id, Position position, boolean interactable, boolean collidable, int bomb_radius) {
        super(id, position, interactable, collidable);
        this.bomb_radius = bomb_radius;
        this.inInventory = false;
    }

    public void explode() {
        //boom
    }

    public void pickup(Player player, Dungeon dungeon) {
        //if bomb collidable cant pickup 
        //if picked up bomb set collidable 
        if(!this.isCollidable()){
            player.addItem(this);
            dungeon.removeEntity(this);
            this.setCollidable(true);
        }

    }

    /**
     * drop da bomb
     */
    public void use() {
        // TODO Auto-generated method stub
        
    }

    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return "bomb";
    }



    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // TODO Auto-generated method stub
        
    }

}
