package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Arrow extends Entity implements Collectable, Storeable {
    
    public Arrow(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    @Override
    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int getItemId() {
        return getEntityId();
    }
    
    public String getType() {
        return "arrow";
    }

}
