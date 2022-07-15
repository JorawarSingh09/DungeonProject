package dungeonmania.entities.staticentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Portal extends Entity implements Static {
    // add a portal pair
    Position pairPosition;

    public Portal(int id, Position position) {
        super(id, position, false, true);
    }

    @Override
    public void playerOnTo(Player player, Dungeon dungeon, Direction direction) {
        // TODO Auto-generated method stub
        //when a player
        
    }  
    
    public boolean isRepellent() {
        return false;
    }

    public setPairPosition(Position position){
        this.setPair(portal);
    }

    public void setPair(Portal portal){
        this.pairPosition = position;
    }
}
