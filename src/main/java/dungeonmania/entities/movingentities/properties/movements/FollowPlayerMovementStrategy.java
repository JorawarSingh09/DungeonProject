package dungeonmania.entities.movingentities.properties.movements;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Position;

public class FollowPlayerMovementStrategy extends MovementStrategy {

    public FollowPlayerMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
    }

    public Position nextStep(Dungeon dungeon, boolean isAlly, Player player, Position followed,
            Position follower) {
        // check adjacent position, calculate distance between position,

        // friends,and your next move is on player,
        if (isAlly && followed.equals(findMoveableBlock(dungeon, followed, follower, false))) {
            // player hasnt moved you do not move
            if (player.getPosition().equals(player.getPreviousPosition())) {
                return follower;
            }
        } else if (isAlly && followed.equals(follower)) {
            // player has moved, swap
            return player.getPreviousPosition();
        }
        return findMoveableBlock(dungeon, followed, follower, false);
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        Position followed = player.getPosition();
        Position follower = movingEntity.getPosition();
        boolean isAlly = movingEntity.isAllyToPlayer();
        
        if (isAlly && followed.equals(findMoveableBlock(dungeon, followed, follower, false))) {
            // player hasnt moved you do not move
            if (player.getPosition().equals(player.getPreviousPosition())) {
                return follower;
            }
        } else if (isAlly && followed.equals(follower)) {
            // player has moved, swap
            return player.getPreviousPosition();
        }
        return findMoveableBlock(dungeon, followed, follower, false);
    }

    private Position findMoveableBlock(Dungeon dungeon, Position followed, Position follower, boolean reversed) {
        Position newPos = follower;
        double distance = Double.POSITIVE_INFINITY;
        if (reversed) distance = Double.NEGATIVE_INFINITY;

        for (Position pos : follower.getCardinallyAdjacentPositions()) {
            if (getMinMaxDis(reversed, pos, followed, distance) && dungeon.getStaticsOnBlock(pos).size() < 1) {
                distance = Position.getDistanceBetweenTwoPositions(pos, followed);
                newPos = pos;
            }
        }
        return newPos;
    }

    private boolean getMinMaxDis(boolean min, Position pos, Position followed, double currDistance) {
        if (min) {
            return Position.getDistanceBetweenTwoPositions(pos, followed) < currDistance;
        }
        return Position.getDistanceBetweenTwoPositions(pos, followed) > currDistance;
    }

    public void reversePath(Dungeon dungeon, Player player) {
        // Do nothing
        return;
    }

}
