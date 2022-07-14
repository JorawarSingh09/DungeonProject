package dungeonmania.interfaces;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface Moveable {
    
    public void updatePosition(Dungeon dungeon, Player player);
    public Position getNextPosition();
}
