package dungeonmania.entities.staticentities;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTile extends Entity implements Static {

    private int movementFactor;
    private Map<Moveable, Integer> tickCount = new HashMap<>();

    public SwampTile(int entityId, Position position, int movementFactor) {
        super(entityId, position, false, false);
        this.movementFactor = movementFactor;
    }

    @Override
    public String getType() {
        return "swamp_tile";
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        player.setPosition(this.getPosition());
    }

    public boolean isRepellent() {
        return false;
    }

    public int getMovementFactor() {
        return movementFactor;
    }

    // Returns whether the entity is still stuck or if it is released.
    public boolean entityStuck(Moveable enemy, Dungeon dungeon) {
        if (enemy.getPosition().equals(this.getPosition())) {
            if (tickCount.containsKey(enemy)) {
                int currTickCount = tickCount.get(enemy);
                tickCount.replace(enemy, currTickCount + 1);
                if (tickCount.get(enemy) < movementFactor) {
                    return true;
                }
                return false;
            } else {
                tickCount.put(enemy, 0);
                return true;
            }
        }
        return false;
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = super.getJson();
        entityJSON.addProperty("movement_factor", movementFactor);
        return entityJSON;
    }
    
}
