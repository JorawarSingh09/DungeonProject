package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.buildableentities.Sceptre;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.DeadState;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.PlayerMovementStrategy;
import dungeonmania.enums.EntityString;
import dungeonmania.enums.ErrorString;
import dungeonmania.enums.Usable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.entities.collectableentities.Bomb;

public class Player extends Entity implements Moveable {

    private double health;
    private double attack;
    private Inventory inventory;
    private MovementStrategy moveStrat;
    private Direction movement;
    private List<Mercenary> mercenaries = new ArrayList<>();
    private Queue<Regenerative> queueItems = new LinkedList<>();

    private PlayerState state;
    private Position prevPosition;
    private PlayerState aliveState = new AliveState(this);
    private PlayerState deadState = new DeadState(this);
    private PlayerState invincState = new InvincibleState(this);
    private PlayerState invisState = new InvisibleState(this);

    public Player(int id, Position position, boolean interactable, boolean collidable,
            double player_attack, double player_health, int bowDurability, int shieldDurability, int shieldDefence,
            int armourAttack, int armourDefence, int mindControlDuration) {
        super(id, position, interactable, collidable);
        this.prevPosition = position;
        this.health = player_health;
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, shieldDefence, armourAttack, armourDefence,
                mindControlDuration);
        this.state = aliveState;
        this.moveStrat = new PlayerMovementStrategy(this);
    }

    public PlayerState getPlayerState() {
        return state;
    }

    public Direction getMovement() {
        return movement;
    }

    public void setMovement(Direction movement) {
        this.movement = movement;
    }

    public List<Storeable> getInventoryItems() {
        return inventory.getInventoryItems();
    }

    public List<String> getBuildableItems(boolean hasZombie) {
        return inventory.getBuildableItems(hasZombie);
    }

    public void setPlayerState(PlayerState state) {
        this.state = state;
    }

    public double getHealth() {
        return health;
    }

    public double loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health <= 0) {
            health = 0;
            state = getDeadState();
        }
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public void addAlly(Mercenary mercenary, boolean mindControl) {
        mercenaries.add(mercenary);
        mercenary.setAlly();
        mercenary.setInteractable(false);
        if (mindControl) {
            mercenary.setMindControl(true);
            mercenary.setDurability(inventory.getMindControlDuration());
        } else {
            inventory.removeItem(mercenary.getBribeAmount(), Treasure.class);
        }
    }

    public void removeAlly(Mercenary mercenary) {
        mercenaries.remove(mercenary);
        mercenary.removeAlly();
        mercenary.setMindControl(false);
    }

    public List<Mercenary> getAllies() {
        return mercenaries;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Position getNextPosition(Direction movement) {
        return this.getPosition().translateBy(movement);
    }

    public void updatePosition(Dungeon dungeon, Direction movement) {
        moveStrat.updateMovement(dungeon, this);
    }

    public void engageBattle(boolean playerDied) {
        state.engageBattle(playerDied);
    }

    public void tickPotion() {
        if (queueItems.size() > 0) {
            Regenerative item = queueItems.peek();
            item.decrementDuration();
            if (item.getRemainingDuration() <= 0) {
                queueItems.remove();

                if (queueItems.size() > 0) {
                    nextItem();
                } else {
                    state = aliveState;
                }
            }
        } else {
            state.tick(0);
        }
    }

    public void tickMindControl() {
        List<Mercenary> merc = new ArrayList<Mercenary>();
        for (Mercenary mercenary : mercenaries) {
            if (mercenary.isMindControlled()) {
                if (mercenary.getDurability() < 0) {
                    merc.add(mercenary);
                }
                mercenary.reduceDurability();
            }
        }
        for (Mercenary mercenary : merc) {
            removeAlly(mercenary);
        }
    }

    public void drinkInvis(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            Regenerative invisPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invisPotion);
            if (queueItems.size() == 1) {
                state.drinkInvis();
            }
        }
        inventory.removeItemById(itemId);
    }

    public void drinkInvinc(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            Regenerative invincPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invincPotion);
            if (queueItems.size() == 1) {
                state.drinkInvinc();
            }
        }
        inventory.removeItemById(itemId);
    }

    private void nextItem() {
        if (queueItems.peek() instanceof InvisibilityPotion) {
            state.drinkInvis();
        } else if (queueItems.peek() instanceof InvincibilityPotion) {
            state.drinkInvinc();
        }
    }

    public boolean hasItem(int id) {
        return inventory.hasItem(id);
    }

    public Storeable getItemFromId(int id) {
        return inventory.getItemFromId(id);
    }

    public void addItem(Entity item) {
        inventory.addItem((Storeable) item);
    }

    public void removeItem(Entity entity) {
        inventory.removeItemById(entity.getEntityId());
    }

    public String itemType(int id) {
        return inventory.getHistoricalItemType(id);
    }

    public void putDownBomb(Dungeon dungeon, int id) {
        ((Bomb) inventory.getItemFromId(id)).drop(dungeon, this);
    }

    public void build(String item, int nextItemId) {
        inventory.build(item, nextItemId);
    }

    public boolean hasKey(int keyPair) {
        return inventory.hasRightKey(keyPair);
    }

    public boolean hasSunStone() {
        return inventory.hasSunStone();
    }

    public Position getPreviousPosition() {
        return prevPosition;
    }

    public void setPreviousPosition(Position prevPosition) {
        this.prevPosition = prevPosition;
    }

    public String attemptBribe(Mercenary mercenary) {
        if (inventory.countItem(Treasure.class) < mercenary.getBribeAmount() && inventory.countItem(Sceptre.class) == 0)
            return ErrorString.BRIBETREAS.toString();
        if (Position.getDistanceBetweenTwoPositions(this.getPosition(), mercenary.getPosition()) > mercenary
                .getbribeRadius())
            return ErrorString.BRIBERAD.toString();
        if (!mercenary.isInteractable())
            return ErrorString.NOTINTERACT.toString();
        addAlly(mercenary, inventory.countItem(Sceptre.class) >= 1);
        return ErrorString.SUCCESS.toString();
    }

    @Override
    public String getType() {
        return EntityString.PLAYER.toString();
    }

    public PlayerState getAliveState() {
        return aliveState;
    }

    public PlayerState getDeadState() {
        return deadState;
    }

    public PlayerState getInvincState() {
        return invincState;
    }

    public PlayerState getInvisState() {
        return invisState;
    }

    public boolean hasWeapon() {
        return inventory.getAttackingItems().size() > 0;
    }

    public boolean hasKey() {
        return inventory.getKeys().size() > 0;
    }

    public void updatePosition(Dungeon dungeon, Player player) {
        return;
    }

    public boolean isTangible() {
        return true;
    }

    public MovementStrategy getMovementStrategy() {
        return moveStrat;
    }

    public void changeMovementStrategy(MovementStrategy movementStrategy) {
        this.moveStrat = movementStrategy;
    }

    public boolean isAllyToPlayer() {
        return true;
    }

    public void drinkPotion(int id) {
        if (itemType(id).equals(Usable.INVINCE.toString())) {
            drinkInvinc(id);
        } else if (itemType(id).equals(Usable.INVIS.toString())) {
            drinkInvis(id);
        }
    }

}
