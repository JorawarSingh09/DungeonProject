package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Key extends Entity implements Storeable, Collectable {
    int keyPair;

    public Key(int id, Position position, int keyPair) {
        super(id, position, false, false);
        this.keyPair = keyPair;
    }

    @Override
    public void pickup(Player player, Dungeon dungeon) {
        // if(player.has)
        player.addItem(this);
        dungeon.removeEntity(this);

    }

    public int getItemId() {
        return getEntityId();
    }

    public int getKeyPair() {
        return this.keyPair;
    }

    public String getType() {
        return "key";
    }

}
