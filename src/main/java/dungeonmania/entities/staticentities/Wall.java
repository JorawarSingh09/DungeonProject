package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Position;

public class Wall extends Entity implements Static {
    
    public Wall(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    public void playerOnTo(Player player, Dungeon dungeon) {
        // Do nothing
    }   
    
}
