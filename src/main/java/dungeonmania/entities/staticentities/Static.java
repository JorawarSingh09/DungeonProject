package dungeonmania.entities.staticentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;

public interface Static {

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction);
    
    public boolean isCollidable();

    public boolean isRepellent();

    public int getEntityId();

}
