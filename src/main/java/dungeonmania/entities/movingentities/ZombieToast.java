package dungeonmania.entities.movingentities;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moveable, Health {

    private double attack;
    private double health;

    public ZombieToast(int id, Position position, boolean interactable, boolean collidable,
            double attack, double health) {
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
        List<Position> positions = getPosition().getCardinallyAdjacentPositions();
        Collections.shuffle(positions);
        for (Position position : positions) {
            int collidable = dungeon.getStaticsOnBlock(position).stream().filter(e -> e.isCollidable())
                    .collect(Collectors.toList()).size();
            if (collidable == 0) {
                setPosition(position);
                return;
            }
        }
    }

    @Override
    public Position getNextPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType() {
        return "zombie_toast";
    }

}
