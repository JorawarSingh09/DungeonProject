package dungeonmania.entities.movingentities.properties.movements;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Position;

public class FollowPlayerMovementStrategy extends MovementStrategy {

    boolean reversed;

    public FollowPlayerMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
        reversed = false;
    }

    public Position nextStep(Dungeon dungeon, boolean isAlly, Player player, Position followed,
            Position follower) {
        // check adjacent position, calculate distance between position,

        // friends,and your next move is on player,
        if (isAlly && followed.equals(findMoveableBlock(dungeon, followed, follower))) {
            // player hasnt moved you do not move
            if (player.getPosition().equals(player.getPreviousPosition())) {
                return follower;
            }
        } else if (isAlly && followed.equals(follower)) {
            // player has moved, swap
            return player.getPreviousPosition();
        }
        return findMoveableBlock(dungeon, followed, follower);
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        Position followed = player.getPosition();
        Position follower = movingEntity.getPosition();
        boolean isAlly = movingEntity.isAllyToPlayer();
        
        if (isAlly && followed.equals(findMoveableBlock(dungeon, followed, follower))) {
            // player hasnt moved you do not move
            if (player.getPosition().equals(player.getPreviousPosition())) {
                return follower;
            }
        } else if (isAlly && followed.equals(follower)) {
            // player has moved, swap
            return player.getPreviousPosition();
        }
        return findMoveableBlock(dungeon, followed, follower);
    }

    private Position findMoveableBlock(Dungeon dungeon, Position followed, Position follower) {
        Position newPos = follower;
        double distance = Double.POSITIVE_INFINITY;
        if (reversed) distance = Double.NEGATIVE_INFINITY;

        for (Position pos : follower.getCardinallyAdjacentPositions()) {
            if (getMinMaxDis(pos, followed, distance) && dungeon.getStaticsOnBlock(pos).size() < 1) {
                distance = Position.getDistanceBetweenTwoPositions(pos, followed);
                newPos = pos;
            }
        }
        return newPos;
    }

    private boolean getMinMaxDis(Position pos, Position followed, double currDistance) {
        if (reversed) {
            return Position.getDistanceBetweenTwoPositions(pos, followed) < currDistance;
        }
        return Position.getDistanceBetweenTwoPositions(pos, followed) > currDistance;
    }

    public void reversePath() {
        if (reversed) {
            reversed = false;
        } else {
            reversed = true;
        }
    }

    public void updateMovement(Dungeon dungeon, Player player) {
        if (nextStepIsMoveable(dungeon, player)) movingEntity.setPosition(getNextPosition(dungeon, player));
    }

}
