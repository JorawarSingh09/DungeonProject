package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.entities.collectableentities.Bomb;

public class FloorSwitch extends Entity implements Static {

    boolean triggered;

    public FloorSwitch(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
        this.triggered = false;
    }

    public void setTriggered(boolean bool) {
        this.triggered = bool;
    }

    public boolean isTriggered() {
        return triggered;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        if (!isCollidable()) {
            player.updatePosition(dungeon, direction);
        }

    }

    public void checkBomb(Dungeon dungeon) {
        for (Position position : this.getPosition().getCardinallyAdjacentPositions()) {
            for (Static foundItem : dungeon.getStaticsOnBlock(position)) {
                if (foundItem instanceof Bomb) {
                    ((Bomb) foundItem).explode(dungeon);
                }
            }
        }
    }

    @Override
    public String getType() {
        return "switch";
    }

    public boolean isRepellent() {
        return false;
    }

}
