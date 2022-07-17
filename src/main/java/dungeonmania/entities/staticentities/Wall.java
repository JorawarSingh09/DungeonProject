package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Wall extends Entity implements Static {

    public Wall(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {

    }

    @Override
    public String getType() {
        return EntityString.WALL.toString();
    }

    public boolean isRepellent() {
        return false;
    }

}
