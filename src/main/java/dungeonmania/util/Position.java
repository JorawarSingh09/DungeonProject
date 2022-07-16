package dungeonmania.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Position {
    private final int x, y, layer;

    public Position(int x, int y, int layer) {
        this.x = x;
        this.y = y;
        this.layer = layer;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.layer = 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y, layer);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;

        // z doesn't matter
        return x == other.x && y == other.y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getLayer() {
        return layer;
    }

    public final Position asLayer(int layer) {
        return new Position(x, y, layer);
    }

    public final Position translateBy(int x, int y) {
        return this.translateBy(new Position(x, y));
    }

    public final Position translateBy(Direction direction) {
        return this.translateBy(direction.getOffset());
    }

    public final Position translateBy(Position position) {
        return new Position(this.x + position.x, this.y + position.y, this.layer + position.layer);
    }

    // (Note: doesn't include z)

    /**
     * Calculates the position vector of b relative to a (ie. the direction from a
     * to b)
     * 
     * @return The relative position vector
     */
    public static final Position calculatePositionBetween(Position a, Position b) {
        return new Position(b.x - a.x, b.y - a.y);
    }

    public static Direction getDirection(Position a, Position b) {
        Position offset = calculatePositionBetween(a, b);
        List<Direction> directions = Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        for (Direction direction : directions) {
            if (direction.getOffset().equals(offset)) {
                return direction;
            }
        }
        System.out.println("direction is null");
        return null;
    }

    public static final double getDistanceBetweenTwoPositions(Position a, Position b) {
        Position vector = calculatePositionBetween(a, b);
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }

    public static final boolean isAdjacent(Position a, Position b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y) == 1;
    }

    // (Note: doesn't include z)
    public final Position scale(int factor) {
        return new Position(x * factor, y * factor, layer);
    }

    @Override
    public final String toString() {
        return "Position [x=" + x + ", y=" + y + ", z=" + layer + "]";
    }

    // Return Adjacent positions in an array list with the following element
    // positions:
    // 7 0 1
    // 6 p 2
    // 5 4 3
    public List<Position> getAdjacentPositions() {
        List<Position> adjacentPositions = new ArrayList<>();
        adjacentPositions.add(new Position(x, y - 1));
        adjacentPositions.add(new Position(x + 1, y - 1));
        adjacentPositions.add(new Position(x + 1, y));
        adjacentPositions.add(new Position(x + 1, y + 1));
        adjacentPositions.add(new Position(x, y + 1));
        adjacentPositions.add(new Position(x - 1, y + 1));
        adjacentPositions.add(new Position(x - 1, y));
        adjacentPositions.add(new Position(x - 1, y - 1));

        return adjacentPositions;
    }

    public List<Position> getCardinallyAdjacentPositions() {
        List<Position> adjacentPositions = new ArrayList<>();
        adjacentPositions.add(new Position(x, y - 1));
        adjacentPositions.add(new Position(x + 1, y));
        adjacentPositions.add(new Position(x, y + 1));
        adjacentPositions.add(new Position(x - 1, y));
        return adjacentPositions;
    }
}
