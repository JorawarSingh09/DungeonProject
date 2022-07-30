package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class Sceptre extends Entity implements Buildable, Storeable {

    public Sceptre(int entityId) {
        super(entityId, new Position(0, 0), false, false);
    }

    @Override
    public int getItemId() {
        return getEntityId();
    }

    @Override
    public void build(Player player) {

    }

    @Override
    public void consumeItems(Player player) {

    }

    @Override
    public String getType() {
        return "sceptre";
    }

}
