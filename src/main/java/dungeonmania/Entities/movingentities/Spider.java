package dungeonmania.entities.movingentities;

import dungeonmania.entities.Entity;

public class Spider extends Entity {

    private int attack;
    private int health;

    public Spider(int id, int xPos, int yPos, boolean interactable, boolean collidable, int attack, int health) {
        super(id, xPos, yPos, interactable, collidable);
        this.attack = attack;
        this.health = health;
    }
    
}