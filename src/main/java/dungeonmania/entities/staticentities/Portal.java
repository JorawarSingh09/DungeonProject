package dungeonmania.entities.staticentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Portal extends Entity implements Static {
    private Position pairPosition;
    private String colour;

    public Portal(int id, Position position, String colour) {
        super(id, position, false, false);
        this.colour = colour;
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        onTo(player, dungeon, direction);
    }

    public void mercenaryMoveOnto(Mercenary mercernary, Dungeon dungeon, Direction direction) {
        onTo(mercernary, dungeon, direction);
    }

    public void boulderMoveOnto(Boulder boulder, Dungeon dungeon, Direction direction) {
        onTo(boulder, dungeon, direction);
    }

    public void onTo(Entity entity, Dungeon dungeon, Direction direction) {
        List<Position> possiblePositions = new ArrayList<>();
        possiblePositions.add(pairPosition.translateBy(direction));
        possiblePositions.addAll(pairPosition.getCardinallyAdjacentPositions());
        for (Position position : possiblePositions) {
            int col = dungeon.getStaticsOnBlock(position).stream().filter(e -> e.isCollidable() || e.isRepellent())
                    .collect(Collectors.toList()).size();
            if (col == 0) {
                boolean chainPortal = false;
                for (Static posPortal : dungeon.getStaticsOnBlock(position)) {
                    if (posPortal instanceof Portal) {
                        ((Portal) posPortal).onTo(entity, dungeon, direction);
                        chainPortal = true;
                    }
                }
                
                if (!chainPortal) entity.setPosition(position);
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

    @Override
    public JsonObject getJson(){
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("colour", this.colour);

        return entityJSON;

    }
}
