package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;

public class ZombieToastSpawner extends Entity {

    private int spawnRate;
    
    public ZombieToastSpawner(int id, int xPos, int yPos, boolean interactable, boolean collidable, int spawnRate) {
        super(id, xPos, yPos, interactable, collidable);
        this.spawnRate = spawnRate;
    }  
    
}
