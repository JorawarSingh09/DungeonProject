package dungeonmania.entities.staticentities;

import java.util.stream.Collectors;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends Entity implements Static {

    public Boulder(int id, Position position) {
        super(id, position, false, true);
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {

        if (checkNextPos(getPosition().translateBy(direction), dungeon, player, direction)) {
            player.setPosition(this.getPosition());
            setPosition(getPosition().translateBy(direction));
        }
    }

    private boolean checkNextPos(Position position, Dungeon dungeon, Player player, Direction direction) {
        for (Static portal : dungeon.getStaticsOnBlock(position)) {
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
