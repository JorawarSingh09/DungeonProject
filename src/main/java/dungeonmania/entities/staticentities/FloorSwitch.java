package dungeonmania.entities.staticentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.entities.collectableentities.Bomb;

public class FloorSwitch extends Entity implements Static {

    private boolean triggered;

    public FloorSwitch(int id, Position position) {
        super(id, position, false, false);
        this.triggered = false;
    }

    public void setTriggered(boolean bool) {
        this.setCollidable(bool);
        this.triggered = bool;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        if (!isCollidable()) {
            player.setPosition(this.getPosition());
        }
    }

    public void checkBomb(Dungeon dungeon) {
        if (isTriggered()) {
            for (Position position : this.getPosition().getCardinallyAdjacentPositions()) {
                for (Entity bomb : dungeon.getEntitiesOfType("bomb")) {
                    if (position.equals(bomb.getPosition())) {
                        ((Bomb) bomb).explode(dungeon);
                    }
                }
            }
        }
    }

    @Override
    public String getType() {
        return "switch";
    }

    public boolean isRepellent() {
        return false;
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", this.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("triggered", this.isTriggered());
        return entityJSON;
    }
}
