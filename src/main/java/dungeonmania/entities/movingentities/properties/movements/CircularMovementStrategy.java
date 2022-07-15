package dungeonmania.entities.movingentities.properties.movements;

import java.util.Collections;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Position;

public class CircularMovementStrategy extends MovementStrategy{

    CircularMovement<Position> movePath = new CircularMovement<>();
    int moveState;
    boolean clockwise;

    public CircularMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
        this.clockwise = true;
        calculatePath();
        this.moveState = 0;
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        return movePath.get(moveState);
    }

    private void calculatePath() {
        this.movePath.populatePath(movingEntity.getPosition().getAdjacentPositions());
    }
    
    public void updateMovement(Dungeon dungeon, Player player) {
        movingEntity.setPosition(getNextPosition(dungeon, player));
        moveState++;
    }
    
    @Override
    public void reversePath() {
        Collections.reverse(movePath);
    }

}
