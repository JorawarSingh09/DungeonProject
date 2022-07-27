package dungeonmania.interfaces;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.util.Position;

public interface Moveable {
    public void updatePosition(Dungeon dungeon, Player player);

    public boolean isTangible();

    public MovementStrategy getMovementStrategy();

    public void changeMovementStrategy(MovementStrategy movementStrategy);

    public boolean isAllyToPlayer();

    public Position getPosition();

    public void setPosition(Position position);

}
