package dungeonmania.entities.staticentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Position;

public class Door extends Entity implements Static {
    
    public Door(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon) {
        // TODO Auto-generated method stub
        
    }  
    
}
