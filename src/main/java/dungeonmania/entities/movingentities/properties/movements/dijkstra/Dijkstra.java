package dungeonmania.entities.movingentities.properties.movements.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.util.Position;

public class Dijkstra {
    List<Node> grid = new ArrayList<>();
    Queue<Node> positions = new PriorityQueue<>();
    Map<Node, Node> prev = new HashMap<>();
    Map<Node, Boolean> visited = new HashMap<>();
    Node source;
    Node dest;
    Position followed; 
    Position follower;
    Dungeon dungeon;

    public Dijkstra(Dungeon dungeon, Position follower, Position followed) {
        this.dungeon = dungeon;
        this.follower = follower;
        this.followed = followed;
    }

    public boolean generateGrid() {
        grid.clear();
        Position bottomLeft = new Position(follower.getX() - 10, follower.getY() - 10);
        boolean foundPlayer = false;

        // Get all co-ords in the grid
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Position curr = new Position(bottomLeft.getX() + i, bottomLeft.getY() + j);
                if (curr.equals(followed)) {
                    dest = new Node(curr, determineCost(curr, dungeon), dungeon, this);
                    grid.add(dest);
                    foundPlayer = true;
                } else if (curr.equals(follower)) {
                    source = new Node(curr, determineCost(curr, dungeon), dungeon, this);
                    grid.add(source);
                } else if (determineCost(curr, dungeon) != null) {
                    grid.add(new Node(curr, determineCost(curr, dungeon), dungeon, this));
                }
            }
        }
        return foundPlayer;
    }

    private Integer determineCost(Position position, Dungeon dungeon) {
        if (dungeon.getStaticsOnBlock(position).stream().filter(t -> t.isCollidable()).collect(Collectors.toList()).size() > 0) {
            return null;
        } else if (dungeon.getSwampTiles().stream().filter(t -> t.getPosition().equals(position)).collect(Collectors.toList()).size() > 0) {
            return dungeon.getSwampTiles().stream().filter(t -> t.getPosition().equals(position)).collect(Collectors.toList()).get(0).getMovementFactor() + 1;
        }
        return 1;
    }

    private Map<Node, Node> pathOfTraversal() {
        for (Node node : grid) {
            prev.put(node, null);
            visited.put(node, false);
            node.setNeighbours();
        }

        source.setDistance(0);
        positions.add(source);
        while (!positions.isEmpty()) {
            Node currTraversing = positions.poll();
            if (currTraversing.equals(dest)) {
                return prev;
            }
            visited.put(currTraversing, true);
            for (Node neighbour : currTraversing.getNeighbours()) {
                if (!visited.get(neighbour)) {
                    if (neighbour.getDistance() > (neighbour.getCost() + currTraversing.getDistance())) {
                        neighbour.setDistance((neighbour.getCost() + currTraversing.getDistance()));
                        prev.replace(neighbour, currTraversing);
                    }
                    if (positions.contains(neighbour)) {
                        positions.remove(neighbour);
                        positions.add(neighbour);
                    } else {
                        positions.add(neighbour);
                    }
                }
            }
        }
        return prev;
    }

    public Node getNodeForPos(Position pos) {
        if (grid.stream().filter(n -> n.getLocation().equals(pos)).collect(Collectors.toList()).size() > 0) {
            return grid.stream().filter(n -> n.getLocation().equals(pos)).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public Position getNextPos() {
        Map<Node, Node> prevs = pathOfTraversal();
        List<Node> path = new ArrayList<>();
        Node u = dest;
        while(u != null) {
            path.add(u);
            u = prevs.get(u);
        }
        Collections.reverse(path);
        if (path.size() > 1) {
            return path.get(1).getLocation();
        }
        return null;
    }
    
}
