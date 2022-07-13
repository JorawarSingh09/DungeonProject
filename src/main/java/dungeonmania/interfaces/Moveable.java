package dungeonmania.interfaces;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface Moveable {
    
    public void updatePosition();
    public Position getNextPosition(Direction movement);
}
