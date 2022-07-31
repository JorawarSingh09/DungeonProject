package dungeonmania.entities.buildableentities;

import com.google.gson.JsonObject;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Attacking;
import dungeonmania.entities.collectableentities.interfaces.Durability;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.util.Position;

public class Bow extends Entity implements Storeable, Durability, Attacking {

    private int battleBonus = 2;
    private int durability;

    public Bow(int id, int bow_durability) {
        super(id, new Position(0, 0), false, false);
        this.durability = bow_durability;
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

    public boolean isAdditive() {
        return false;
    }

    public int getItemId() {
        return getEntityId();
    }

    public int attackBonus() {
        return battleBonus;
    }

    public String getType() {
        return "bow";
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
