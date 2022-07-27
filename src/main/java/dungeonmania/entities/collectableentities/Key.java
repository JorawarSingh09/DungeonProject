package dungeonmania.entities.collectableentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
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
        return EntityString.KEY.toString();
    }

    @Override
    public JsonObject getJson(){
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("key", this.keyPair);

        return entityJSON;

    }

}
