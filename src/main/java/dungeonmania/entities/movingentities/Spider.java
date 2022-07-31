package dungeonmania.entities.movingentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.interfaces.Health;
import dungeonmania.entities.movingentities.interfaces.Moveable;
import dungeonmania.entities.movingentities.properties.movements.CircularMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moveable, Health {

    private double attack;
    private double health;
    private MovementStrategy moveStrat;

    public Spider(int id, Position position,
            double attack, double health) {
        super(id, position, false, false);
        this.attack = attack;
        this.health = health;
        moveStrat = new CircularMovementStrategy(this);
    }

    public double getHealth() {
        return health;
    }

    public double getAttackDamage() {
        return attack;
    }

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

    public void updatePosition(Dungeon dungeon, Player player) {
        moveStrat.updateMovement(dungeon, player);
    }

    public boolean isAlly() {
        return false;
    }

    @Override
    public String getType() {
        return "spider";
    }

    public boolean isTangible() {
        return false;
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

    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", this.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.add("movement", this.moveStrat.getJson());
        return entityJSON;
    }

}