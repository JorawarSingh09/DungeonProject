package dungeonmania.entities.collectableentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Static;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Bomb extends Entity implements Collectable, Storeable, Static {
    private int bomb_radius;
    private boolean inInventory;
    List<Position> blastArea = new ArrayList<>();

    public Bomb(int id, Position position, boolean interactable, boolean collidable, int bomb_radius) {
        super(id, position, interactable, collidable);
        this.bomb_radius = bomb_radius + 1;
        this.inInventory = false;
        this.blastArea = this.getPosition().getAdjacentPositions();
        this.blastArea.removeAll(this.getPosition().getCardinallyAdjacentPositions());
        this.blastArea = this.blastArea.stream().map(p -> p.scale(bomb_radius)).collect(Collectors.toList());
    }

    public void explode(Dungeon dungeon) {
        // for all entities whose distance from bomb is less than equal
        // to explosion radius, DELETE
        System.out.println("boom");
        for (int id : dungeon.getEntityIds()) {
            if (Position.getDistanceBetweenTwoPositions(this.getPosition(),
                    dungeon.getEntityById(id).getPosition()) <= bomb_radius) {
                if (!(dungeon.getEntityById(id).getType().equals("player"))) {
                    dungeon.removeEntityById(id);
                    if (dungeon.getEntityById(id) instanceof Health && 
                        !((Health) dungeon.getEntityById(id)).isAlly()) {
                        dungeon.addKillCount();
                    }
                }
            }
        }
    }

    public void pickup(Player player, Dungeon dungeon) {
        // if bomb collidable cant pickup
        // if picked up bomb set collidable
        if (!this.isCollidable()) {
            player.addItem(this);
            dungeon.removeEntity(this);
            player.setPosition(this.getPosition());
            
        }

    }

    /**
     * drop da bomb
     */
    public void drop(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub
        // place bomb at player
        //
        setPosition(player.getPosition());
        setCollidable(true);
        dungeon.addEntity(this);
        player.removeItem(this);
        System.out.println("drop");

    }

    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return "bomb";
    }

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {

    }

}
