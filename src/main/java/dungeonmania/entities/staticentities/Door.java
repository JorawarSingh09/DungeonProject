package dungeonmania.entities.staticentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Door extends Entity implements Static {
    private int keyPair;
    private boolean doorOpen;

    public Door(int id, Position position, int keyPair) {
        super(id, position, false, true);
        this.keyPair = keyPair;
        this.doorOpen = false;
    }

    public int getKeyPair() {
        return this.keyPair;
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        if (player.hasSunStone() || player.hasKey(keyPair) || doorOpen) {
            player.setPosition(this.getPosition());
            setCollidable(false);
            this.doorOpen = true;
        }
    }

    @Override
    public void setCollidable(boolean isCollidable) {
        super.setCollidable(isCollidable);
        if (isCollidable) {
            this.doorOpen = true;
        } else {
            this.doorOpen = false;
        }
    }

    @Override
    public String getType() {
        return "door";
    }

    public boolean isRepellent() {
        return false;
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("key", this.keyPair);
        entityJSON.addProperty("collidable", super.isCollidable());

        return entityJSON;

    }
}
