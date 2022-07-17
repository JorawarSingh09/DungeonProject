package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
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

    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public String getType() {
        return EntityString.INVINCIBILITYPOTION.toString();
    }

}
