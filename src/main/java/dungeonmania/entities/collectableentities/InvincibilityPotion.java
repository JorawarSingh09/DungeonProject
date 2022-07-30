package dungeonmania.entities.collectableentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class InvincibilityPotion extends Entity implements Storeable, Collectable, Regenerative {

    private int duration;

    public InvincibilityPotion(int id, Position position, boolean interactable, boolean collidable, int duration) {
        super(id, position, interactable, collidable);
        this.duration = duration;
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int getItemId() {
        return getEntityId();
    }

    public void setRemainingDuration(int Duration){
        this.duration = Duration;
    }
    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public String getType() {
        return "invincibility_potion";
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
