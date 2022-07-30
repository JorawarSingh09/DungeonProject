package dungeonmania.entities.collectableentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Collectable;
import dungeonmania.entities.collectableentities.interfaces.Regenerative;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class InvisibilityPotion extends Entity implements Collectable, Storeable, Regenerative {

    private int duration;

    public InvisibilityPotion(int id, Position position, int duration) {
        super(id, position, false, false);
        this.duration = duration;
    }

    public void use() {
        duration--;
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);

    }

    public int getItemId() {
        return getEntityId();
    }

    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public String getType() {
        return "invisibility_potion";
    }

    @Override
    public JsonObject getJson(){
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("duration", this.duration);

        return entityJSON;
    }
    
}
