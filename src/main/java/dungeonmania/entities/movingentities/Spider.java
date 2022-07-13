package dungeonmania.entities.movingentities;

import java.util.Collections;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.CircularMovement;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moveable, Health {

    private int attack;
    private int health;
    int moveState;
    boolean clockwise;

    CircularMovement<Position> movePath = new CircularMovement<>();

    public Spider(int id, Position position, boolean interactable, boolean collidable, int attack, int health) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.health = health;
        this.clockwise = true;
        calculatePath();
        // reversePath();
        this.moveState = 0;
    }

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return health;
    }

    @Override
    public int getAttackDamage() {
        // TODO Auto-generated method stub
        return attack;
    }

    public Position getNextPosition(Direction movement) {
        return movePath.get(moveState);
    }

    public void reversePath() {
        Collections.reverse(movePath);
    }

    @Override
    public void loseHealth() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePosition() {
        // if you hit a boulder reverse
        setPosition(movePath.get(moveState));
        moveState++;
    }

    private void calculatePath() {
        this.movePath.populatePath(this.getPosition().getAdjacentPositions());

    }

    @Override
    public String getType() {
        return "spider";
    }
}