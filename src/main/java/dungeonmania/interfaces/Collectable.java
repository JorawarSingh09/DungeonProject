package dungeonmania.interfaces;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;

public interface Collectable {

    public void pickup(Player player, Dungeon dungeon);
     
}

