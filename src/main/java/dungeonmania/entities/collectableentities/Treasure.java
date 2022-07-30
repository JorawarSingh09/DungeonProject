package dungeonmania.entities.collectableentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Treasure extends Entity implements Collectable, Storeable {

    public Treasure(int id, Position position) {
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
        return "treasure";
    }

}
