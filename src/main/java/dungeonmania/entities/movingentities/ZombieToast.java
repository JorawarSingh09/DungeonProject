package dungeonmania.entities.movingentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.interfaces.Health;
import dungeonmania.entities.movingentities.playerstates.interfaces.Moveable;
import dungeonmania.entities.movingentities.properties.movements.FollowPlayerMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moveable, Health {

    private double attack;
    private double health;
    private MovementStrategy currMoveStrat;
    private MovementStrategy standard = new RandomMovementStrategy(this);
    private MovementStrategy playerInvinc = new FollowPlayerMovementStrategy(this);

    public ZombieToast(int id, Position position,
            double attack, double health) {
        super(id, position, false, false);
        this.attack = attack;
        this.health = health;
        currMoveStrat = standard;
        playerInvinc.reversePath();
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
        if (player.getPlayerState() instanceof InvincibleState) {
            currMoveStrat = playerInvinc;
        } else {
            currMoveStrat = standard;
        }
        currMoveStrat.updateMovement(dungeon, player);
    }

    @Override
    public String getType() {
        return "zombie_toast";
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

}
