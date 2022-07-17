package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.entities.collectableentities.Bomb;

public class FloorSwitch extends Entity implements Static {

    private boolean triggered;

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

    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        if (!isCollidable()) {
            player.setPosition(this.getPosition());
        }
    }

    public void checkBomb(Dungeon dungeon) {
        if (isTriggered()) {
            for (Position position : this.getPosition().getCardinallyAdjacentPositions()) {
                for (Entity bomb : dungeon.getEntitiesOfType("bomb")) {
                    if (position.equals(bomb.getPosition())) {
                        ((Bomb) bomb).explode(dungeon);
                    }
                }
            }
        }
    }

    @Override
    public String getType() {
        return EntityString.SWITCH.toString();
    }

    public boolean isRepellent() {
        return false;
    }

}
