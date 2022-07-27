package dungeonmania.entities.collectableentities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Static;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Bomb extends Entity implements Collectable, Storeable, Static {
    private int bomb_radius;
    private List<Position> blastArea = new ArrayList<>();

    public Bomb(int id, Position position, boolean interactable, boolean collidable, int bomb_radius) {
        super(id, position, interactable, collidable);
        this.bomb_radius = bomb_radius;
        Position bombOrigin = new Position(this.getPosition().getX() - this.bomb_radius, this.getPosition().getY() - this.bomb_radius);
        for (int i = 0; i < ((2 * this.bomb_radius) + 2); i++) {
            for (int j = 0; j < ((2 * this.bomb_radius) + 2); j++) {
                Position curr = new Position(bombOrigin.getX() + i, bombOrigin.getY() + j);
                blastArea.add(curr);
            }
        }
    }

    public void explode(Dungeon dungeon) {
        List<Integer> idsToRemove = new ArrayList<>();
        for (Position pos : blastArea) {
            for (Static stat : dungeon.getStaticsOnBlock(pos)) {
                idsToRemove.add(stat.getEntityId());
            }
            for (Collectable collect : dungeon.getCollectablesOnBlock(pos)) {
                idsToRemove.add(collect.getEntityId());
            }
            for (Health enemy : dungeon.getEnemiesOnBlock(pos)) {
                idsToRemove.add(enemy.getEntityId());
                if (!enemy.isAlly()) {
                    dungeon.addKillCount();
                }
            }
        }
        for (Integer id : idsToRemove) {
            dungeon.removeEntityById(id);
        }
    }

    public void pickup(Player player, Dungeon dungeon) {
        if (!this.isCollidable()) {
            player.addItem(this);
            dungeon.removeEntity(this);
            player.setPosition(this.getPosition());
        }

    }

    public void drop(Dungeon dungeon, Player player) {
        setPosition(player.getPosition());
        setCollidable(true);
        dungeon.addEntity(this);
        player.removeItem(this);
    }

    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return EntityString.BOMB.toString();
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        return;
    }

    public boolean isRepellent() {
        return false;
    }

}
