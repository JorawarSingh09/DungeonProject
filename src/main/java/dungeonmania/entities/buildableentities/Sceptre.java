package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.util.Position;

public class Sceptre extends Entity implements Storeable {

    public Sceptre(int entityId) {
        super(entityId, new Position(0, 0), false, false);
    }

    @Override
    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return "sceptre";
    }

}
