package dungeonmania.entities.movingentities;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moveable, Health {

    private double attack;
    private double health;
    MovementStrategy moveStrat;

    public ZombieToast(int id, Position position, boolean interactable, boolean collidable,
            double attack, double health) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.health = health;
        moveStrat = new RandomMovementStrategy(this);
    }

    public double getHealth() {
        return health;
    }

    public double getAttackDamage() {
        return attack;
    }

    public void loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health < 0)
            health = 0;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isAlly() {
        return false;
    }

    public void updatePosition(Dungeon dungeon, Player player) {
        moveStrat.updateMovement(dungeon, player);
    }

    @Override
    public String getType() {
        return "zombie_toast";
    }

    public boolean isTangible() {
        return true;
    }


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
