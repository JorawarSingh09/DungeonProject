package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity implements Static {

    private int spawnRate;
    
    public ZombieToastSpawner(int id, Position position, boolean interactable, boolean collidable, int spawnRate) {
        super(id, position, interactable, collidable);
        this.spawnRate = spawnRate;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // TODO Auto-generated method stub
        
    }  
    
}
