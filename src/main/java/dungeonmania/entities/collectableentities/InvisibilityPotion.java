package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class InvisibilityPotion extends Entity implements Collectable, Storeable, Regenerative {
    
    private int duration;

    public InvisibilityPotion(int id, Position position, boolean interactable, boolean collidable, int duration) {
        super(id, position, interactable, collidable);
        this.duration = duration;
    }

    public void use() {
        duration -= 1;
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);

    }

    public int getItemId() {
        return getEntityId();    }

    public int getRemainingDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration -= 1;
    }

    public String getType() {
        return "invisibility_potion";
    }

}
