package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Portal extends Entity implements Static {
    // add a portal pair
    String colour;
    Position pairPosition;

    public Portal(int id, Position position, boolean interactable, boolean collidable, String colour) {
        super(id, position, interactable, collidable);
        this.colour = colour;
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // TODO Auto-generated method stub
        //when a player
        
    }  
    
    public boolean isRepellent() {
        return false;
    }

}
