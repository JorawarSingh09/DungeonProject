package dungeonmania.entities.collectableentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Collectable;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class Wood extends Entity implements Collectable, Storeable {

    public Wood(int id, Position position) {
        super(id, position, false, false);
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return "wood";
    }

}
