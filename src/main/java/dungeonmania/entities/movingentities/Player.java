package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.DeadState;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import javassist.compiler.MemberResolver;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.entities.collectableentities.Bomb;

public class Player extends Entity {

    private double health;
    private double attack;
    Inventory inventory;
    List<Mercenary> mercenaries = new ArrayList<>();
    Queue<Regenerative> queueItems = new LinkedList<>();

    PlayerState state;
    Position prevPosition;
    PlayerState aliveState = new AliveState(this);
    PlayerState deadState = new DeadState(this);
    PlayerState invincState = new InvincibleState(this);
    PlayerState invisState = new InvisibleState(this);

    public Player(int id, Position position, boolean interactable, boolean collidable,
            double player_attack, double player_health, int bowDurability, int shieldDurability) {
        super(id, position, interactable, collidable);
        this.prevPosition = position;
        this.health = player_health;
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, getPosition());
        this.state = aliveState;
    }

    public PlayerState getPlayerState() {
        return state;
    }

    public List<Storeable> getInventoryItems() {
        return inventory.getInventoryItems();
    }

    public List<String> getBuildableItems() {
        return inventory.getBuildableItems();
    }

    public void setPlayerState(PlayerState state) {
        this.state = state;
    }

    public double getHealth() {
        return health;
    }

    public double loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health < 0)
            health = 0;

        return health;
    }

    public double getAttack() {
        return attack;
    }

    public void addAlly(Mercenary mercenary) {
        mercenaries.add(mercenary);
        mercenary.setAlly();
        mercenary.setInteractable(false);
        inventory.removeItem(mercenary.getBribeAmount(), Treasure.class);

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

    public void updatePosition(Direction movement) {
        setPreviousPosition(this.getPosition());
        this.setPosition(getNextPosition(movement));
    }

    public void engageBattle(boolean playerDied) {
        state.engageBattle(playerDied);
    }

    public void tickPotion() {
        if (queueItems.size() > 0) {
            Regenerative item = queueItems.peek();
            item.decrementDuration();
            if (item.getRemainingDuration() == 0) {
                queueItems.remove();
                inventory.removeItemById(item.getItemId());
            }
            if (queueItems.size() > 0) {
                nextItem();
            } else {
                state = aliveState;
            }
        } else {
            state.tick(0);
        }
    }

    public void drinkInvis(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            Regenerative invincPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invincPotion);
            if (queueItems.size() == 1) {
                state.drinkInvis();
            }
        }
    }

    public void drinkInvinc(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            Regenerative invincPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invincPotion);
            if (queueItems.size() == 1) {
                state.drinkInvinc();
            }
        }
    }

    private void nextItem() {
        if (queueItems.peek() instanceof InvisibilityPotion) {
            drinkInvis(queueItems.peek().getItemId());
        } else if (queueItems.peek() instanceof InvincibilityPotion) {
            drinkInvinc(queueItems.peek().getItemId());
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
        return inventory.itemHistory.get(id);
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

    public Position getPreviousPosition() {
        return prevPosition;
    }

    public void setPreviousPosition(Position prevPosition) {
        this.prevPosition = prevPosition;
    }

    public boolean attemptBribe(Mercenary mercenary) {
        // check money
        // check position
        if (inventory.countItem(Treasure.class) < mercenary.getBribeAmount())
            return false;
        if (Position.getDistanceBetweenTwoPositions(this.getPosition(), mercenary.getPosition()) > mercenary
                .getbribeRadius())
            return false;
        addAlly(mercenary);
        return true;
    }

    @Override
    public String getType() {
        return "player";
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

}
