package dungeonmania.interfaces;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;

public interface Static {
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction);
    public boolean isCollidable();
}
