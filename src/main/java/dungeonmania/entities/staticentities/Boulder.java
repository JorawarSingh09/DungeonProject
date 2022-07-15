package dungeonmania.entities.staticentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends Entity implements Static {

    public Boulder(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // Check if the next block has entities on it
        Position playerPos = player.getPosition();
        Position boulderPos = getPosition();

        if (checkNextPos(getPosition().translateBy(direction), dungeon, player, direction)) {
            player.setPosition(this.getPosition());
            setPosition(getPosition().translateBy(direction));
        }
    }

    private boolean checkNextPos(Position position, Dungeon dungeon, Player player, Direction direction) {
        for (Static portal : dungeon.getStaticsOnBlock(position)) { // get portals from thingy
            if (portal instanceof Portal) {
                Position pos = getPosition();
                ((Portal) portal).boulderMoveOnto(this, dungeon, direction);
                if (!pos.equals(getPosition())) {
                    player.setPosition(pos);
                }
                return false;
            }
        }
        return !(dungeon.getStaticsOnBlock(position).stream().filter(entity -> entity.isCollidable())
                .collect(Collectors.toList()).size() > 0);
    }

    @Override
    public String getType() {
        return "boulder";
    }

    public boolean isRepellent() {
        return true;
    }

}
