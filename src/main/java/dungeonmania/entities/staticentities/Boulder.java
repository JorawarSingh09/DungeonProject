package dungeonmania.entities.staticentities;

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

        if(checkNextPos(getPosition().translateBy(direction), dungeon)){
            player.updatePosition(direction);
            setPosition(getPosition().translateBy(direction));
        }
    }

    private boolean checkNextPos(Position position, Dungeon dungeon) {
        return !(dungeon.getStaticsOnBlock(position).stream().filter(entity -> entity.isCollidable())
                .collect(Collectors.toList()).size() > 0);
    }

    @Override
    public String getType() {
        return "boulder";
    }
}
