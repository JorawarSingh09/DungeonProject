package dungeonmania.entities.movingentities.properties.movements.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.entities.staticentities.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Node implements Comparable<Node> {
    
    private Position location;
    private Integer distance = Integer.MAX_VALUE;
    private Integer cost;
    private List<Node> neighbours = new ArrayList<>();
    private List<Position> neighbourPos = new ArrayList<>();
    private Dungeon dungeon;
    private Dijkstra dijkstra;

    public Node(Position location, Integer cost, Dungeon dungeon, Dijkstra dijkstra) {
        this.location = location;
        this.cost = cost;
        this.dungeon = dungeon;
        this.dijkstra = dijkstra;
        neighbourPos = determineAdjacents();
    }

    private List<Position> determineAdjacents() {
        List<Position> toRemove = new ArrayList<>();
        List<Position> toAdd = new ArrayList<>();
        List<Position> adjacents = new ArrayList<>();
        adjacents.addAll(location.getCardinallyAdjacentPositions());
        for (Position pos : adjacents) {
            for (Static posPortal : dungeon.getStaticsOnBlock(pos)) {
                if (posPortal instanceof Portal) {
                    Direction dir = Position.getDirection(this.location, pos);
                    Position pairPosition = ((Portal) posPortal).getPairPosition();
                    Position position = ((Portal) posPortal).getPairPosition().translateBy(dir);
                    if (findPortalNextPos(position, pairPosition) != null)  {
                        toRemove.add(pos);
                        toAdd.add(findPortalNextPos(position, pairPosition));
                    }
                }
            }
        }
        adjacents.addAll(toAdd);
        adjacents.removeAll(toRemove);

        return adjacents;
    }

    private Position findPortalNextPos(Position pos, Position pairPosition) {
        List<Position> possiblePositions = new ArrayList<>();
        possiblePositions.add(pos);
        possiblePositions.addAll(pairPosition.getCardinallyAdjacentPositions());
        for (Position position : possiblePositions) {
            int col = dungeon.getStaticsOnBlock(position).stream().filter(e -> e.isCollidable() || e.isRepellent())
                    .collect(Collectors.toList()).size();
            if (col == 0) {
                return position;
            }
        }
        return null;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours() {
        neighbours = neighbourPos.stream().map(s -> dijkstra.getNodeForPos(s)).filter(s -> s != null).collect(Collectors.toList());
    }

    public Position getLocation() {
        return location;
    }

    @Override
    public int compareTo(Node o) {
        return o.getDistance() < this.distance ? 1 : -1;
    }

}
