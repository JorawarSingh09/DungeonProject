package dungeonmania.Entities.movingentities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.Entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.AttackingItem;
import dungeonmania.interfaces.DefendingItem;

public class Player extends Entity {

    private int health;
    private int attack;
    Inventory inventory;
    List<AttackingItem> attackItems = new ArrayList<>();
    List<DefendingItem> defenceItems = new ArrayList<>();
    
    public Player(int id, int xPos, int yPos, boolean interactable, boolean collidable, int player_attack, int player_health) {
        super(id, xPos, yPos, interactable, collidable);
        this.health = player_health;
        this.attack = player_attack;
    }    
    
}
