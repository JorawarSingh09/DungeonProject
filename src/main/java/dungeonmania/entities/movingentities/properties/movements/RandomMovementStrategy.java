package dungeonmania.entities.movingentities.properties.movements;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.playerstates.interfaces.Moveable;
import dungeonmania.util.Position;

public class RandomMovementStrategy extends MovementStrategy {

    public RandomMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        List<Position> positions = movingEntity.getPosition().getCardinallyAdjacentPositions();
        Collections.shuffle(positions);
        for (Position position : positions) {
            int collidable = dungeon.getStaticsOnBlock(position).stream().filter(e -> e.isCollidable())
                    .collect(Collectors.toList()).size();
            if (collidable == 0) {
                return position;
            }
        }
        return movingEntity.getPosition();
    }

    public void updateMovement(Dungeon dungeon, Player player) {
        if (nextStepIsMoveable(dungeon, player))
            movingEntity.setPosition(getNextPosition(dungeon, player));
    }

    @Override 
    public String toString(){
        return "Random";
    }
}
