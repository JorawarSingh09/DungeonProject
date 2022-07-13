package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Door extends Entity implements Static {
    int keyPair;
    boolean doorOpen;

    public Door(int id, Position position, int keyPair) {
        super(id, position, false, true);
        this.keyPair = keyPair;
        this.doorOpen = false;
    }

    public int getKeyPair() {
        return this.keyPair;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // has right key
        // open door move
        // no right key or no key
        // cant move
        if (player.hasKey(keyPair) || doorOpen) {
            // set door open
            player.updatePosition(direction);
            setCollidable(false);
            this.doorOpen = true;
        }

    }

    @Override
    public String getType() {
        return "door";
    }

}
