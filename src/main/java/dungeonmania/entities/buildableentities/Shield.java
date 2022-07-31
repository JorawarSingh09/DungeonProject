package dungeonmania.entities.buildableentities;

import com.google.gson.JsonObject;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Defending;
import dungeonmania.entities.collectableentities.interfaces.Durability;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.util.Position;

public class Shield extends Entity implements Storeable, Defending, Durability {

    private int durability;
    private int defence;

    public Shield(int id, int durability, int defence) {
        super(id, new Position(0, 0), false, false);
        this.durability = durability;
        this.defence = defence;
    }

    public void reduceDurability() {
        this.durability -= 1;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int defenceBonus() {
        return defence;
    }

    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return "shield";
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
