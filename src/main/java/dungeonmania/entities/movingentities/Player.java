package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Storeable;

public class Player extends Entity implements Moveable {

    private int health;
    private int attack;
    Inventory inventory;
    List<Mercenary> mercenaries = new ArrayList<>();
    Queue<Storeable> queueItems = new LinkedList<>();
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
        state.engageBattle(playerDied);;
    }

    public void tick() {
        state.tick();;
    }

    public void drinkInvis(int itemId) {
        state.drinkInvis();
    }

    public void drinkInvinc(int itemId) {
        Storeable invincPotion = inventory.getItemFromId(itemId);
        queueItems.add(invincPotion);
        state.drinkInvinc();
    }

}
