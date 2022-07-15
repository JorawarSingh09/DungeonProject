package dungeonmania.entities.staticentities;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.ZombieToast;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity implements Static {

    private int spawnRate;
    private double attack;
    private double health;

    public ZombieToastSpawner(int id, Position position, boolean interactable,
            boolean collidable, int spawnRate, double attack, double health) {
        super(id, position, interactable, collidable);
        this.spawnRate = spawnRate;
        this.attack = attack;
        this.health = health;
    }

    public ZombieToast spawnZombieToast(int currentMaxId, Dungeon dungeon) {
        List<Position> positions = getPosition().getCardinallyAdjacentPositions();
        Collections.shuffle(positions);
        for (Position position : positions) {
            int entities = dungeon.getEntities().stream().filter(e -> e.getPosition().equals(position))
                    .collect(Collectors.toList()).size();
            if (entities == 0) {
                ZombieToast zombie = new ZombieToast(currentMaxId, position, false,
                        false, attack, health);
                return zombie;
            }
        }
        return null;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getType() {
        return "zombie_toast_spawner";
    }

}
