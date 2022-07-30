package dungeonmania.entities.collectableentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Sword extends Entity implements Storeable, Attacking, Durability, Collectable {

    private int attack;
    private int durability;

    public Sword(int id, Position position, int attack, int durability) {
        super(id, position, false, false);
        this.attack = attack;
        this.durability = durability;
    }

    public void reduceDurability() {
        this.durability--;

    }

    public int getDurability() {
        return durability;
    }

    public boolean isAdditive() {
        return true;
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int attackBonus() {
        return attack;
    }

    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return "sword";
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", super.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("durability", this.getDurability());
        return entityJSON;

    }

}
