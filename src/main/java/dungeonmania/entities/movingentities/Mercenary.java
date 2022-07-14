package dungeonmania.entities.movingentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moveable, Health {

    private int ally_attack;
    private int ally_defence;
    private int attack;
    private int health;
    private int bribe_radius;
    private int bribe_amount;

    public Mercenary(int id, Position position, boolean interactable, boolean collidable, int ally_attack,
            int ally_defence, int mercenary_attack, int mercenary_health, int bribe_radius, int bribe_amount) {
    
        super(id, position, interactable, collidable);
        this.ally_attack = ally_attack;
        this.ally_defence = ally_defence;
        this.attack = mercenary_attack;
        this.health = mercenary_health;
        this.bribe_radius = bribe_radius;
        this.bribe_amount = bribe_amount;
    }
    
    public int getAllyAttackDamage() {
        return ally_attack;
    }

    public int getAllyDefenceBonus() {
        return ally_defence;
    }

    public int getHealth(){
        return health;
    }

    public int getAttackDamage() {
        return attack;
    }

    public int getbribeRadius() {
        return bribe_radius;
    }

    public int getBribeAmount() {
        return bribe_amount;
    }

    public void loseHealth(int deltaHealth) {
        health = health + health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void updatePosition() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Direction movement) {
        // TODO Auto-generated method stub
        return null;
    }

}
