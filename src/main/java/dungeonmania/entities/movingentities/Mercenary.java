package dungeonmania.entities.movingentities;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Durability;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.entities.movingentities.playerstates.interfaces.Health;
import dungeonmania.entities.movingentities.playerstates.interfaces.Moveable;
import dungeonmania.entities.movingentities.properties.movements.FollowPlayerMovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.entities.staticentities.Static;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moveable, Health, Durability {

    protected boolean isAlly;
    private double ally_attack;
    private double ally_defence;
    private double attack;
    private double health;
    private int bribe_radius;
    private int bribe_amount;
    protected MovementStrategy currMoveStrat;
    protected MovementStrategy playerInvis = new RandomMovementStrategy(this);
    protected MovementStrategy standard = new FollowPlayerMovementStrategy(this);
    private int durability;
    private boolean mindControlled = false;

    public Mercenary(int id, Position position,
            double ally_attack, double ally_defence, double mercenary_attack,
            double mercenary_health, int bribe_radius, int bribe_amount) {

        super(id, position, true, false);
        this.ally_attack = ally_attack;
        this.ally_defence = ally_defence;
        this.attack = mercenary_attack;
        this.health = mercenary_health;
        this.bribe_radius = bribe_radius;
        this.bribe_amount = bribe_amount;
        this.isAlly = false;
        currMoveStrat = standard;
        this.durability = 0; 
    }

    public MovementStrategy getPlayerInvis() {
        return playerInvis;
    }

    public MovementStrategy getStandard() {
        return standard;
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

    public void removeAlly() {
        this.isAlly = false;
        this.setInteractable(true);
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

    public void reversePath() {
        currMoveStrat.reversePath();
    }

    public void updatePosition(Dungeon dungeon, Player player) {
        if (player.getPlayerState() instanceof InvisibleState) {
            currMoveStrat = playerInvis;
        } else if (player.getPlayerState() instanceof InvincibleState && !isAlly && !currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else if (player.getPlayerState() instanceof AliveState && currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else {
            currMoveStrat = standard;
        }

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

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", super.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("isAlly", this.isAlly());
        entityJSON.addProperty("mindControl", this.mindControlled);
        entityJSON.addProperty("durability", this.durability);

        return entityJSON;

    }

    public void reduceDurability() {
        this.durability -= 1;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public int getItemId() {
        return getEntityId();
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isMindControlled() {
        return this.mindControlled;
    }

    public void setMindControl(boolean bool) {
        this.mindControlled = bool;
    }
}
