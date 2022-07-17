package dungeonmania.entities.collectableentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
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
        this.bomb_radius = bomb_radius + 1;
        this.blastArea = this.getPosition().getAdjacentPositions();
        this.blastArea.removeAll(this.getPosition().getCardinallyAdjacentPositions());
        this.blastArea = this.blastArea.stream().map(p -> p.scale(bomb_radius)).collect(Collectors.toList());
    }

    public void explode(Dungeon dungeon) {
        for (int id : dungeon.getEntityIds()) {
            if (Position.getDistanceBetweenTwoPositions(this.getPosition(),
                    dungeon.getEntityById(id).getPosition()) <= bomb_radius) {
                if (!(dungeon.getEntityById(id).getType().equals(EntityString.PLAYER.toString()))) {
                    if (dungeon.getEntityById(id) instanceof Health &&
                            !((Health) dungeon.getEntityById(id)).isAlly()) {
                        dungeon.addKillCount();
                    }
                    dungeon.removeEntityById(id);
                }
            }
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
