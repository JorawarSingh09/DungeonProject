package dungeonmania.entities.staticentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Portal extends Entity implements Static {
    // add a portal pair
    Position pairPosition;

    public Portal(int id, Position position) {
        super(id, position, false, false);
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        onTo(player, dungeon, direction);
    }

    public void mercenaryMoveOnto(Mercenary mercernary, Dungeon dungeon, Direction direction) {
        onTo(mercernary, dungeon, direction);
    }

    public void boulderMoveOnto(Boulder boulder, Dungeon dungeon, Direction direction) {
        onTo(boulder, dungeon, direction);
    }

    private void onTo(Entity entity, Dungeon dungeon, Direction direction) {
        List<Position> possiblePositions = new ArrayList<>();
        possiblePositions.add(pairPosition.translateBy(direction));
        possiblePositions.addAll(pairPosition.getCardinallyAdjacentPositions());
        for (Position position : possiblePositions) {
            int col = dungeon.getStaticsOnBlock(position).stream().filter(e -> e.isCollidable() || e.isRepellent())
                    .collect(Collectors.toList()).size();
            if (col == 0) {
                entity.setPosition(position);
                break;
            }
        }
    }

    public boolean isRepellent() {
        return false;
    }

    public void setPairPosition(Position position) {
        this.pairPosition = position;
    }

    public void setPair(Portal portal) {
        this.setPairPosition(portal.getPosition());
        portal.setPairPosition(this.getPosition());
    }

    @Override
    public String getType() {
        return "portal";
    }

    public Position getPairPosition() {
        return pairPosition;
    }
}
