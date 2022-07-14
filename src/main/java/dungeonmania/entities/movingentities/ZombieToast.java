package dungeonmania.entities.movingentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moveable, Health {

    private double attack;
    private double health;

    public ZombieToast(int id, Position position, boolean interactable, boolean collidable, int attack, int health) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.health = health;
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

    @Override
    public void updatePosition(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public Position getNextPosition() {
        // TODO Auto-generated method stub
        return null;
    }

}
