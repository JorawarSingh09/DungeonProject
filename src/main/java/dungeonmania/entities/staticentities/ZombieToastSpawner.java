package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity {

    private int spawnRate;
    
    public ZombieToastSpawner(int id, Position position, boolean interactable, boolean collidable, int spawnRate) {
        super(id, position, interactable, collidable);
        this.spawnRate = spawnRate;
    }  
    
}
