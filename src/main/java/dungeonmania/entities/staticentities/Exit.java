package dungeonmania.entities.staticentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Exit extends Entity implements Static {

    public Exit(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        player.setPosition(this.getPosition());
    }

    @Override
    public String getType() {
        return "exit";
    }

    public boolean isRepellent() {
        return false;
    }

}
