package dungeonmania.controllers;

import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.SwampTile;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;

public class MovementController {

    private Player player;
    private Dungeon dungeon;
    private boolean allSwitchestriggered;

    public MovementController(Player player, Dungeon dungeon) {
        this.player = player;
        this.dungeon = dungeon;
        this.allSwitchestriggered = false;
    }

    public void movePlayer(Direction movement) {
        player.setMovement(movement);
        player.updatePosition(dungeon, movement);
        player.tickMindControl();
        checkBattles();
        checkSwitchBehaviour();
    }

    private void checkSwitchBehaviour() {
        List<Entity> floorSwitches = dungeon.getEntitiesOfType("switch");
        List<Entity> boulders = dungeon.getEntitiesOfType("boulder");
        int floorSwitchCount = floorSwitches.size();
        int boulderCount = 0;
        for (Entity floorSwitch : floorSwitches) {
            boolean switchedOn = false;
            for (Entity boulder : boulders) {
                if (floorSwitch.getPosition().equals(boulder.getPosition())) {
                    boulderCount++;
                    switchedOn = true;
                    break;
                }
            }
            ((FloorSwitch) floorSwitch).setTriggered(switchedOn);
            ((FloorSwitch) floorSwitch).setCollidable(switchedOn);
            ((FloorSwitch) floorSwitch).checkBomb(dungeon);
        }
        this.allSwitchestriggered = (boulderCount == floorSwitchCount);
    }

    public boolean allSwitchestriggered() {
        return this.allSwitchestriggered;
    }

    public void updateEntityPositions() {
        for (Moveable enemy : dungeon.getEnemies()) {
            if (!isStuckOnSwampTile(enemy)) enemy.updatePosition(dungeon, player);
        }
        checkBattles();
        checkSwitchBehaviour();
    }

    private void checkBattles() {
        List<Health> enemiesOnNextBlock = dungeon.getEnemiesOnBlock(player.getPosition());
        for (Health enemy : enemiesOnNextBlock) {
            if (!enemy.isAlly())
                dungeon.startBattle(enemy);
        }
    }

    private boolean isStuckOnSwampTile(Moveable enemy) {
        for (SwampTile swampTile : dungeon.getSwampTiles()) {
            if (swampTile.entityStuck(enemy, dungeon)) {
                return true;
            }
        }
        return false;
    }

}