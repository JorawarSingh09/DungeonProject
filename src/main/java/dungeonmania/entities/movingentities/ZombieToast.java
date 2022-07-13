package dungeonmania.entities.movingentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;

public class ZombieToast extends Entity implements Moveable, Health {

    private int attack;
    private int health;
    
    public ZombieToast(int id, int xPos, int yPos, boolean interactable, boolean collidable, int attack, int health) {
        super(id, xPos, yPos, interactable, collidable);
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

    @Override
    public void loseHealth() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePosition() {
        // TODO Auto-generated method stub
        
    }    
    
}
