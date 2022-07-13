package dungeonmania.interfaces;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;

public interface Static {
    public void playerOnTo(Player player, Dungeon dungeon);
    public boolean isCollidable();
}
