package dungeonmania.entities.movingentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moveable, Health {

    private int attack;
    private int health;

    
    public ZombieToast(int id, Position position, boolean interactable, boolean collidable, int attack, int health) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.health = health;
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

    public void loseHealth(int deltaHealth) {
        health = health + health;
    }

    public void setHealth(int health) {
        this.health = health;
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
