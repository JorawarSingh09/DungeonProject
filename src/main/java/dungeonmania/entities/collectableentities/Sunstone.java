package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Sunstone extends Entity implements Collectable, Storeable {

    public Sunstone(int entityId, Position position, boolean interactable, boolean collidable) {
        super(entityId, position, interactable, collidable);
    }

    @Override
    public String getType() {
        return EntityString.SUNSTONE.toString();
    }

    public int getItemId() {
        return getEntityId();
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);        
    }
    
}
