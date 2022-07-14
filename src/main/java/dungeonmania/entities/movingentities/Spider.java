package dungeonmania.entities.movingentities;

import java.util.Collections;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.CircularMovement;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moveable, Health {

    private double attack;
    private double health;
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
    public double getHealth() {
        // TODO Auto-generated method stub
        return health;
    }

    @Override
    public double getAttackDamage() {
        // TODO Auto-generated method stub
        return attack;
    }

    public Position getNextPosition() {
        return movePath.get(moveState);
    }

    public void reversePath() {
        Collections.reverse(movePath);
    }

    public void loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health < 0)
            health = 0;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void updatePosition(Dungeon dungeon, Player player) {
        // if you hit a boulder reverse
        setPosition(movePath.get(moveState));
        moveState++;
    }

    private void calculatePath() {
        this.movePath.populatePath(this.getPosition().getAdjacentPositions());
    }

    public boolean isAlly() {
        return false;
    }

    @Override
    public String getType() {
        return "spider";
    }

}