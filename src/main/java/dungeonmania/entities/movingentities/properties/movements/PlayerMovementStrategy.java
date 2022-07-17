package dungeonmania.entities.movingentities.properties.movements;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Position;

public class PlayerMovementStrategy extends MovementStrategy {

    public PlayerMovementStrategy(Moveable movingEntity) {
        super(movingEntity);
    }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        Position newPosition = (new Position(player.getPosition().getX(), player.getPosition().getY()))
                .translateBy(player.getMovement());

        return newPosition;
    }

    public void updateMovement(Dungeon dungeon, Player player) {
        Position newPosition = getNextPosition(dungeon, player);
        List<Static> entitiesOnNextBlock = dungeon.getStaticsOnBlock(newPosition);
        // Check for zombie Spawner
        player.setPreviousPosition(player.getPosition());

        for (Static entity : entitiesOnNextBlock) {
            entity.playerOnTo(player, dungeon, player.getMovement());
        }

        if (entitiesOnNextBlock.size() < 1) {
            player.setPosition(newPosition);
        }

        List<Collectable> collectablesOnNextBlock = dungeon.getCollectablesOnBlock(newPosition);
        for (Collectable entity : collectablesOnNextBlock) {
            entity.pickup(player, dungeon);
        }
    }

}
