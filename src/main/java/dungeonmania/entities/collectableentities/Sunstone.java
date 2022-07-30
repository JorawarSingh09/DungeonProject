package dungeonmania.entities.collectableentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Collectable;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class Sunstone extends Entity implements Collectable, Storeable {

    public Sunstone(int entityId, Position position) {
        super(entityId, position, false, false);
    }

    @Override
    public String getType() {
        return "sun_stone";
    }

    public int getItemId() {
        return getEntityId();
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);        
    }
    
}
