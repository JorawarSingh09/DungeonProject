package dungeonmania.controllers;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class MovementController {
    
    Player player;
    Dungeon dungeon;
    
    public MovementController(Player player, Dungeon dungeon) {
        this.player = player;
        this.dungeon = dungeon;
    }

    public void movePlayer(Direction movement) {
        Position newPosition = (new Position(player.getPosition().getX(), player.getPosition().getY())).translateBy(movement);
        List<Static> entitiesOnNextBlock = dungeon.getStaticsOnBlock(newPosition);
        // Check for zombie Spawner
        for (Static entity: entitiesOnNextBlock) {
            entity.playerOnTo(player);
        }
        if (entitiesOnNextBlock.size() < 1) player.updatePosition(movement);
        List<Collectable> collectablesOnNextBlock = dungeon.getCollectablesOnBlock(newPosition);
        for (Collectable entity: collectablesOnNextBlock) {
            entity.pickup();
        }
        List<Health> enemiesOnNextBlock = dungeon.getEnemiesOnBlock(newPosition);
        for (Health enemy: enemiesOnNextBlock) {
            dungeon.startBattle(enemy);
        }
        updateEntityPositions();        
    }

    public void updateEntityPositions() {
        ;
    }
}
