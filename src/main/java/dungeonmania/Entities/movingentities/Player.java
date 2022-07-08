package dungeonmania.Entities.movingentities;

import dungeonmania.Entity;

public class Player extends Entity {

    private int health;
    private int attack;
    
    public Player(int id, int xPos, int yPos, boolean interactable, boolean collidable, int player_attack, int player_health) {
        super(id, xPos, yPos, interactable, collidable);
        this.health = player_health;
        this.attack = player_attack;
    }    
    
}
