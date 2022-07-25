package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Door extends Entity implements Static {
    private int keyPair;
    private boolean doorOpen;

    public Door(int id, Position position, int keyPair) {
        super(id, position, false, true);
        this.keyPair = keyPair;
        this.doorOpen = false;
    }

    public int getKeyPair() {
        return this.keyPair;
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        if (player.hasSunStone() || player.hasKey(keyPair) || doorOpen) {
            player.setPosition(this.getPosition());
            setCollidable(false);
            this.doorOpen = true;
        }
    }

    @Override
    public String getType() {
        return EntityString.DOOR.toString();
    }

    public boolean isRepellent() {
        return false;
    }

}
