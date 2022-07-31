package dungeonmania.entities.collectableentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Collectable;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class Key extends Entity implements Storeable, Collectable {
    private int keyPair;

    public Key(int id, Position position, int keyPair) {
        super(id, position, false, false);
        this.keyPair = keyPair;
    }

    @Override
    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int getItemId() {
        return getEntityId();
    }

    public int getKeyPair() {
        return this.keyPair;
    }

    public String getType() {
        return "key";
    }

    @Override
    public JsonObject getJson(){
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", this.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("key", this.keyPair);

        return entityJSON;

    }

}
