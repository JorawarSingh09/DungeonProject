package dungeonmania.Entities.collectableentities;

import dungeonmania.Entity;

public class Bomb extends Entity {

    private int bomb_radius;
    
    public Bomb(int id, int xPos, int yPos, boolean interactable, boolean collidable, int bomb_radius) {
        super(id, xPos, yPos, interactable, collidable);
        this.bomb_radius = bomb_radius;
    }

}
