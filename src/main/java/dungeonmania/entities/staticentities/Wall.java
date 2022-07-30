package dungeonmania.entities.staticentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Wall extends Entity implements Static {

    public Wall(int id, Position position) {
        super(id, position, false, true);
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {

    }

    @Override
    public String getType() {
        return "wall";
    }

    public boolean isRepellent() {
        return false;
    }

}
