package dungeonmania.entities.movingentities.properties.movements;

import java.util.stream.Collectors;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.interfaces.Moveable;
import dungeonmania.util.Position;

public abstract class MovementStrategy {

    Moveable movingEntity;

    public MovementStrategy(Moveable movingEntity) {
        this.movingEntity = movingEntity;
    }

    public abstract Position getNextPosition(Dungeon dungeon, Player player);

    public abstract void updateMovement(Dungeon dungeon, Player player);

    public void reversePath() {
        // Do nothing
        return;
    }

    public boolean isReversed() {
        return false;
    }

    public boolean nextStepIsMoveable(Dungeon dungeon, Player player) {
        if (movingEntity.isTangible()) {
            return (dungeon.getStaticsOnBlock(getNextPosition(dungeon, player)).stream()
                    .filter(s -> s.isCollidable())
                    .collect(Collectors.toList()).size()) < 1;
        } else {
            return (dungeon.getStaticsOnBlock(getNextPosition(dungeon, player)).stream()
                    .filter(s -> s.isRepellent())
                    .collect(Collectors.toList()).size()) < 1;
        }
    }

    public JsonObject getJson() {
        return null;
    }

}
