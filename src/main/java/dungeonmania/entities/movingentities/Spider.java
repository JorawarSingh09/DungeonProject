package dungeonmania.entities.movingentities;

import java.util.Collections;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.movements.CircularMovement;
import dungeonmania.entities.movingentities.properties.movements.CircularMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moveable, Health {

    private double attack;
    private double health;
    MovementStrategy moveStrat;
    int moveState;
    boolean clockwise;

    CircularMovement<Position> movePath = new CircularMovement<>();

    public Spider(int id, Position position, boolean interactable, boolean collidable,
            double attack, double health) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.health = health;
        moveStrat = new CircularMovementStrategy(this);
        // this.clockwise = true;
        // calculatePath();
        // this.moveState = 0;
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

    // public Position getNextPosition() {
    //     return movePath.get(moveState);
    // }

    public Position getNextPosition(Dungeon dungeon, Player player) {
        return moveStrat.getNextPosition(dungeon, player);
    }


    public void reversePath() {
        moveStrat.reversePath();
    }

    public void loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health < 0)
            health = 0;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    // @Override
    // public void updatePosition(Dungeon dungeon, Player player) {
    //     // if you hit a boulder reverse
    //     setPosition(movePath.get(moveState));
    //     moveState++;
    // }

    public void updatePosition(Dungeon dungeon, Player player) {
        // if you hit a boulder reverse
        moveStrat.updateMovement(dungeon, player);
        //setPosition(moveStrat.getNextPosition(dungeon, player));
        moveState++;
    }

    // private void calculatePath() {
    //     this.movePath.populatePath(this.getPosition().getAdjacentPositions());
    // }

    public boolean isAlly() {
        return false;
    }

    @Override
    public String getType() {
        return "spider";
    }

    @Override
    public boolean isTangible() {
        return false;
    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return moveStrat;
    }

    public void changeMovementStrategy(MovementStrategy movementStrategy) {
        moveStrat = movementStrategy;
        
    }

    public boolean isAllyToPlayer() {
        return false;
    }

}