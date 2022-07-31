package dungeonmania.entities.movingentities.properties.movements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.playerstates.interfaces.Moveable;
import dungeonmania.util.Position;

public class CircularMovementStrategy extends MovementStrategy {

    CircularMovement<Position> movePath = new CircularMovement<>();
    int moveState;
    boolean clockwise;

    public CircularMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
        this.clockwise = true;
        calculatePath();
        this.moveState = 0;
    }

    public CircularMovementStrategy(Moveable movingEntity, boolean clockwise,
            int movestate, CircularMovement<Position> movePath) {
        super(movingEntity);
        this.clockwise = clockwise;
        this.movePath = movePath;
        this.moveState = movestate;
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        return movePath.get(moveState);
    }

    private void calculatePath() {
        this.movePath.populatePath(movingEntity.getPosition().getAdjacentPositions());
    }

    public void updateMovement(Dungeon dungeon, Player player) {
        if (!nextStepIsMoveable(dungeon, player)) {
            reversePath();
            if (clockwise) {
                moveState += 2;
            } else {
                moveState = ((moveState - 2) + movePath.size());
            }
        }
        if (!nextStepIsMoveable(dungeon, player)) {
            return;
        }
        movingEntity.setPosition(getNextPosition(dungeon, player));
        if (clockwise) {
            moveState++;
        } else {
            moveState = ((moveState - 1) + movePath.size());
        }

    }

    @Override
    public void reversePath() {
        if (clockwise) {
            clockwise = false;
        } else {
            clockwise = true;
        }
    }

    @Override
    public boolean isReversed() {
        return clockwise;
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("clockwise", clockwise);
        entityJSON.addProperty("moveState", moveState);
        JsonArray savedMovePath = new JsonArray();
        movePath.forEach(e -> savedMovePath.add(e.getJson()));
        entityJSON.add("movePath", savedMovePath);

        return entityJSON;
    }

    @Override
    public String toString() {
        return "Circular";
    }

}
