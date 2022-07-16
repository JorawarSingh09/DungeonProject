package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.movements.FollowPlayerMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Static;
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
    MovementStrategy currMoveStrat;
    MovementStrategy playerInvis = new RandomMovementStrategy(this);
    MovementStrategy standard = new FollowPlayerMovementStrategy(this);

    public Mercenary(int id, Position position, boolean interactable, boolean collidable,
            double ally_attack, double ally_defence, double mercenary_attack,
            double mercenary_health, int bribe_radius, int bribe_amount) {

        super(id, position, interactable, collidable);
        this.ally_attack = ally_attack;
        this.ally_defence = ally_defence;
        this.attack = mercenary_attack;
        this.health = mercenary_health;
        this.bribe_radius = bribe_radius;
        this.bribe_amount = bribe_amount;
        this.isAlly = false;
        currMoveStrat = standard;
    }

    public double getAllyAttackDamage() {
        return ally_attack;
    }

    public double getAllyDefenceBonus() {
        return ally_defence;
    }

    public boolean isAlly() {
        return isAlly;
    }

    public void setAlly() {
        this.isAlly = true;
        this.setInteractable(false);
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
        if (player.getPlayerState().equals(player.getInvisState())) {
            currMoveStrat = playerInvis;
        } else if (player.getPlayerState().equals(player.getInvincState()) && !isAlly && !currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else if (player.getPlayerState().equals(player.getAliveState()) && currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else {
            currMoveStrat = standard;
        }
        //currMoveStrat.updateMovement(dungeon, player); caused merenary to move twice

        boolean foundPortal = false;
        for (Static portal : dungeon.getStaticsOnBlock(currMoveStrat.getNextPosition(dungeon, player))) {
            if (portal instanceof Portal) {
                ((Portal) portal).mercenaryMoveOnto(this, dungeon, Position.getDirection(this.getPosition(),
                    currMoveStrat.getNextPosition(dungeon, player)));

                foundPortal = true;
                break;
            }
        }

        if (!foundPortal)
            currMoveStrat.updateMovement(dungeon, player);
    }

    @Override
    public String getType() {
        return "mercenary";
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
        return isAlly;
    }

}
