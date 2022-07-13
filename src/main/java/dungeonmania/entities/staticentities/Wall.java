package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class Wall extends Entity {

    public Wall(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    @Override
    public String getType() {
        return "wall";
    }
}
