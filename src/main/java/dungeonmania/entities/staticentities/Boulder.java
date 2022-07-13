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

    public void playerOnTo(Player player, Dungeon dungeon) {
        // Check if the next block has entities on it
        Position playerPos = player.getPosition();
        Position boulderPos = getPosition();
        if (playerPos.translateBy(Direction.UP) == boulderPos) {
            if (checkNextPos(getPosition().translateBy(Direction.UP), dungeon)) setPosition(getPosition().translateBy(Direction.UP));
        } else if (playerPos.translateBy(Direction.DOWN) == boulderPos) {
            if (checkNextPos(getPosition().translateBy(Direction.DOWN), dungeon)) setPosition(getPosition().translateBy(Direction.DOWN));
        } else if (playerPos.translateBy(Direction.LEFT) == boulderPos) {
            if (checkNextPos(getPosition().translateBy(Direction.LEFT), dungeon)) setPosition(getPosition().translateBy(Direction.LEFT));
        } else if (playerPos.translateBy(Direction.RIGHT) == boulderPos) {
            if (checkNextPos(getPosition().translateBy(Direction.RIGHT), dungeon)) setPosition(getPosition().translateBy(Direction.RIGHT));
        }

    }    

    private boolean checkNextPos(Position position, Dungeon dungeon) {
        return !(dungeon.getStaticsOnBlock(position).stream().filter(entity -> entity.isCollidable()).collect(Collectors.toList()). size() > 0);
    }
    
}
