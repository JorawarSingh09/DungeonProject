package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Regenerative;

public class Player extends Entity{

    private int health;
    private int attack;
    Inventory inventory;
    List<Mercenary> mercenaries = new ArrayList<>();
    Queue<Regenerative> queueItems = new LinkedList<>();
    PlayerState state;
    Position prevPosition;

    public Player(int id, Position position, boolean interactable, boolean collidable,
            int player_attack, int player_health, int bowDurability, int shieldDurability) {
        super(id, position, interactable, collidable);
        this.prevPosition = position;
        this.health = player_health;
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, getPosition());
        this.state = new AliveState(this);
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

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public void addAlly(Mercenary mercenary) {
        mercenaries.add(mercenary);
        mercenary.setAlly();
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
        this.prevPosition = this.getPosition();
        this.setPosition(getNextPosition(movement));
        // TODO Auto-generated method stub
    }

    public void engageBattle(boolean playerDied) {
        state.engageBattle(playerDied);
    }

    public void tick() {
        if (queueItems.size() > 0) {
            Regenerative item = queueItems.peek();
            item.decrementDuration();
            if (item.getRemainingDuration() == 0) {
                queueItems.remove();
                inventory.removeItemById(item.getItemId());
            }
            ;
            if (queueItems.size() > 0)
                nextItem();
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

    public void addItem(Entity item) {
        inventory.addItem((Storeable) item);
    }

    public void removeItem(Entity entity) {
        inventory.removeItemById(entity.getEntityId());
    }

    public String itemType(int id) {
        return inventory.getItemFromId(id).getType();
    }

    public void putDownBomb(int id) {
        inventory.getItemFromId(id).use();
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

    public boolean attemptBribe(Mercenary mercenary){
        //check money
        //check position
        if(inventory.countItem(Treasure.class) < mercenary.getBribeAmount()) return false;
        if(Position.getDistanceBetweenTwoPositions(this.getPosition(), mercenary.getPosition()) > 
            mercenary.getbribeRadius()) return false;
        addAlly(mercenary);
        return true;
    }

    @Override
    public String getType() {
        return "player";
    }

    
}
