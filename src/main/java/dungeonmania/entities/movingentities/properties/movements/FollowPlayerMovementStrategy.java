package dungeonmania.entities.movingentities.properties.movements;

import dungeonmania.dungeon.Dungeon;
import java.util.stream.Collectors;


import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.interfaces.Moveable;
import dungeonmania.entities.movingentities.properties.movements.dijkstra.Dijkstra;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.util.Position;

public class FollowPlayerMovementStrategy extends MovementStrategy {

    boolean reversed;

    public FollowPlayerMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
        reversed = false;
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        Position followed = player.getPosition();
        Position follower = movingEntity.getPosition();
        boolean isAlly = movingEntity.isAlly();

        if (isAlly && followed.equals(follower)) {
            // ally has moved onto you, they stay where they were.
            return player.getPreviousPosition();
        }
        if (isAlly && followed.equals(findMoveableBlock(dungeon, followed, follower))) {
            // player hasnt moved you do not move
            if (player.getPosition().equals(player.getPreviousPosition())) {
                return follower;
            }
            if (isAlly && followed.equals(follower)) {
                // player has moved, swap
                return player.getPreviousPosition();
            }
        }
        return findMoveableBlock(dungeon, followed, follower);
    }

    private Position findMoveableBlock(Dungeon dungeon, Position followed, Position follower) {
        Dijkstra dijkstra = new Dijkstra(dungeon, follower, followed);
        if (!dijkstra.generateGrid() || reversed) {
            return simpleFollow(dungeon, followed, follower);
        } 
        Position nextMove = dijkstra.getNextPos();
        if (nextMove != null) {
            return nextMove;
        } else {
            return simpleFollow(dungeon, followed, follower);
        }
    }

    private Position simpleFollow(Dungeon dungeon, Position followed, Position follower){
        Position newPos = follower;
        double distance = Double.POSITIVE_INFINITY;
        if (reversed)
            distance = Double.NEGATIVE_INFINITY;

        for (Position pos : follower.getCardinallyAdjacentPositions()) {
            if (getMinMaxDis(pos, followed, distance) && dungeon.getStaticsOnBlock(pos).stream().filter(t -> (t.isCollidable())).collect(Collectors.toList()).size() < 1
                || getMinMaxDis(pos, followed, distance) && 
                    dungeon.getStaticsOnBlock(pos).size() == 1 &&
                        dungeon.getStaticsOnBlock(pos).get(0) instanceof Portal) {
                distance = Position.getDistanceBetweenTwoPositions(pos, followed);
                newPos = pos;
            }
        }
        return newPos;
    }

    private boolean getMinMaxDis(Position pos, Position followed, double currDistance) {
        if (reversed) {
            return Position.getDistanceBetweenTwoPositions(pos, followed) > currDistance;
        }
        return Position.getDistanceBetweenTwoPositions(pos, followed) < currDistance;
    }

    public void reversePath() {
        if (reversed) {
            reversed = false;
        } else {
            reversed = true;
        }
    }

    @Override
    public boolean isReversed() {
        return reversed;
    }

    public void updateMovement(Dungeon dungeon, Player player) {
        movingEntity.setPosition(getNextPosition(dungeon, player));
    }

    @Override 
    public String toString(){
        return "Follow";
    }

}
