package dungeonmania.entities.movingentities.properties.movements;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Position;

public abstract class MovementStrategy {

    Moveable movingEntity;

    public MovementStrategy(Moveable movingEntity) {
        this.movingEntity = movingEntity;
    }

    public abstract Position getNextPosition(Dungeon dungeon, Player player);

    public void reversePath() {
        // Do nothing
        return;
    }
    
}
