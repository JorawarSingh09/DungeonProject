package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Storeable;

public class Player extends Entity {

    private int health;
    private int attack;
    Inventory inventory;
    List<Attacking> attackItems = new ArrayList<>();
    List<Defending> defenceItems = new ArrayList<>();
    
    public Player(int id, int xPos, int yPos, boolean interactable, boolean collidable, int player_attack, int player_health) {
        super(id, xPos, yPos, interactable, collidable);
        this.health = player_health;
        this.attack = player_attack;
    }    
    
    public List<Storeable> getInventoryItems(){
        return inventory.getInventoryItems();
    }

    public List<Storeable> getBuildableItems(){
        return inventory.getBuildableItems();
    }
}
