package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Moveable;

public class Player extends Entity implements Moveable {

    private int health;
    private int attack;
    Inventory inventory;
    List<Mercenary> mercenaries = new ArrayList<>();
    PlayerState state;
    
    public Player(int id, int xPos, int yPos, boolean interactable, boolean collidable, 
                    int player_attack, int player_health, int bowDurability, int shieldDurability) {
        super(id, xPos, yPos, interactable, collidable);
        this.health = player_health;
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, getPosition());
        this.state = new AliveState();
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

    public void engageBattle() {
        ;
    }

    public void tick() {
        ;
    }

    public void drinkInvis() {
        ;
    }

    public void drinkInvinc() {
        ;
    }
}
