package dungeonmania.spawners;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.ZombieToast;
import dungeonmania.interfaces.Spawn;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity implements Static, Spawn<ZombieToast> {

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

    public ZombieToast spawnEntity(int currentMaxId, Dungeon dungeon) {
        List<Position> positions = getPosition().getCardinallyAdjacentPositions();
        Collections.shuffle(positions);
        for (Position position : positions) {
            int entities = dungeon.getEntities().stream().filter(e -> e.getPosition().equals(position))
                    .collect(Collectors.toList()).size();
            if (entities == 0) {
                ZombieToast zombie = new ZombieToast(currentMaxId, position, attack, health);
                return zombie;
            }
        }
        return null;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {

    }

    @Override
    public String getType() {
        return "zombie_toast_spawner";
    }

    public boolean isRepellent() {
        return false;
    }

}
