package dungeonmania.controllers;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class MovementController {

    Player player;
    Dungeon dungeon;

    boolean allSwitchestriggered;

    public MovementController(Player player, Dungeon dungeon) {
        this.player = player;
        this.dungeon = dungeon;
        this.allSwitchestriggered = false;
    }

    public void movePlayer(Direction movement) {
        Position newPosition = (new Position(player.getPosition().getX(), player.getPosition().getY()))
                .translateBy(movement);
        List<Static> entitiesOnNextBlock = dungeon.getStaticsOnBlock(newPosition);
        // Check for zombie Spawner
        for (Static entity : entitiesOnNextBlock) {
            entity.playerOnTo(player, dungeon, movement);
        }
        if (entitiesOnNextBlock.size() < 1)
            player.updatePosition(movement);
        List<Collectable> collectablesOnNextBlock = dungeon.getCollectablesOnBlock(newPosition);
        for (Collectable entity : collectablesOnNextBlock) {
            entity.pickup(player, dungeon);
        }
        // List<Health> enemiesOnNextBlock = dungeon.getEnemiesOnBlock(newPosition);
        // for (Health enemy: enemiesOnNextBlock) {
        // dungeon.startBattle(enemy);
        // }
        // updateEntityPositions();

        // post move checks
        checkSwitchBehaviour();
    }

    private void checkSwitchBehaviour() {
        int floorSwitchCount = 0;
        int boulderCount = 0;

        for (Entity entity : dungeon.getEntitiesOfType("switch")) {
            floorSwitchCount++;
            for (Static foundEntity : dungeon.getStaticsOnBlock(entity.getPosition())) {
                if (foundEntity instanceof Boulder) {
                    boulderCount++;
                    ((FloorSwitch) entity).setTriggered(true);
                    ((FloorSwitch) entity).setCollidable(true);
                    // ((FloorSwitch) entity).checkBomb(dungeon);
                } else {
                    ((FloorSwitch) entity).setTriggered(false);
                    ((FloorSwitch) entity).setCollidable(false);
                }
            }

        }
        this.allSwitchestriggered = (boulderCount == floorSwitchCount);
    }

    public boolean allSwitchestriggered() {
        return this.allSwitchestriggered;
    }

    public void updateEntityPositions() {
        ;
    }
}
