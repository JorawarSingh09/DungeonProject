package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Storeable;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Regenerative;
import dungeonmania.interfaces.Storeable;

public class Player extends Entity implements Moveable {

    private int health;
    private int attack;
    Inventory inventory;
    List<Mercenary> mercenaries = new ArrayList<>();
    Queue<Regenerative> queueItems = new LinkedList<>();
    PlayerState state;
    
    public Player(int id, int xPos, int yPos, boolean interactable, boolean collidable, 
                    int player_attack, int player_health, int bowDurability, int shieldDurability) {
        super(id, xPos, yPos, interactable, collidable);
        this.health = player_health;                                                                                                                         
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, getPosition());
        this.state = new AliveState(this);
    }    

    public PlayerState getPlayerState() {
        return state;
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
    }

    public List<Mercenary> getAllies() {
        return mercenaries;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updatePosition() {
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
            };
            if (queueItems.size() > 0) nextItem();
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
}
