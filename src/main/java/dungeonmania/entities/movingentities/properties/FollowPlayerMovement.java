package dungeonmania.entities.movingentities.properties;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class FollowPlayerMovement {

    public static Position nextStep(Dungeon dungeon, boolean isAlly, Player player, Position followed,
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

    private static Position findMoveableBlock(Dungeon dungeon, Position followed, Position follower) {
        Position minPos = follower;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Position pos : follower.getCardinallyAdjacentPositions()) {
            if (Position.getDistanceBetweenTwoPositions(pos, followed) < minDistance
                    && dungeon.getStaticsOnBlock(pos).size() < 1) {
                minDistance = Position.getDistanceBetweenTwoPositions(pos, followed);
                minPos = pos;
            }
        }

        return minPos;
    }
}
