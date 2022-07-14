package dungeonmania.entities.movingentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.FollowPlayerMovement;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moveable, Health {

    private boolean isAlly;
    private double ally_attack;
    private double ally_defence;
    private double attack;
    private double health;
    private int bribe_radius;
    private int bribe_amount;
    Position prevPosition;

    public Mercenary(int id, Position position, boolean interactable, boolean collidable, int ally_attack,
            int ally_defence, int mercenary_attack, int mercenary_health, int bribe_radius, int bribe_amount) {

        super(id, position, interactable, collidable);
        this.ally_attack = ally_attack;
        this.ally_defence = ally_defence;
        this.attack = mercenary_attack;
        this.health = mercenary_health;
        this.bribe_radius = bribe_radius;
        this.bribe_amount = bribe_amount;
        this.isAlly = false;
    }

    public double getAllyAttackDamage() {
        return ally_attack;
    }

    public double getAllyDefenceBonus() {
        return ally_defence;
    }

    public boolean getIsAlly() {
        return isAlly;
    }

    public void setAlly() {
        this.isAlly = true;
    }

    public double getHealth() {
        return health;
    }

    public double getAttackDamage() {
        return attack;
    }

    public int getbribeRadius() {
        return bribe_radius;
    }

    public int getBribeAmount() {
        return bribe_amount;
    }

    public void loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health <= 0)
            health = 0;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void updatePosition(Dungeon dungeon, Player player) {
        setPosition(FollowPlayerMovement.nextStep(dungeon, isAlly, player,
                player.getPosition(), this.getPosition()));
    }

    @Override
    public Position getNextPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType() {
        return "mercenary";
    }

}
