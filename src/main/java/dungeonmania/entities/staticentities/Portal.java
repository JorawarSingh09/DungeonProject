package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Position;

public class Portal extends Entity implements Static {
    // add a portal pair
    public Portal(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon) {
        // TODO Auto-generated method stub
        
    }  

}
