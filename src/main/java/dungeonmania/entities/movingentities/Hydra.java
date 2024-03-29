package dungeonmania.entities.movingentities;

import java.util.Random;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.interfaces.Health;
import dungeonmania.entities.movingentities.interfaces.Moveable;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.properties.movements.FollowPlayerMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.util.Position;

public class Hydra extends Entity implements Health, Moveable {

    private MovementStrategy currMoveStrat;
    private MovementStrategy standard = new RandomMovementStrategy(this);
    private MovementStrategy playerInvinc = new FollowPlayerMovementStrategy(this);
    private double attack; 
    private double health;
    private double increaseRate;
    private double increaseAmount;

    public Hydra(int entityId, Position position, double attack, double health, double rate, double amount) {
        super(entityId, position, false, false);
        currMoveStrat = standard;
        playerInvinc.reversePath();
        this.attack = attack; 
        this.health = health;
        this.increaseRate = rate;
        this.increaseAmount = amount;
    }

    @Override
    public String getType() {
        return "hydra";
    }

    @Override
    public void updatePosition(Dungeon dungeon, Player player) {
        if (player.getPlayerState() instanceof InvincibleState) {
            currMoveStrat = playerInvinc;
        } else {
            currMoveStrat = standard;
        }
        currMoveStrat.updateMovement(dungeon, player);
    }

    public boolean isTangible() {
        return true;
    }

    public MovementStrategy getMovementStrategy() {
        return currMoveStrat;
    }

    public void changeMovementStrategy(MovementStrategy movementStrategy) {
        currMoveStrat = movementStrategy;
        
    }

    public boolean isAllyToPlayer() {
        return false;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;        
    }

    public double getAttackDamage() {
        return attack;
    }

    @Override
    public void loseHealth(double deltaHealth) {
        if (new Random().nextDouble() > increaseRate) {
            health = health + deltaHealth;
            if (health < 0)
                health = 0;
        } else {
            health += increaseAmount;
        }

    }

    public boolean isAlly() {
        return false;
    }

    public double getIncreaseAmount() {
        return increaseAmount;
    }
    
}
