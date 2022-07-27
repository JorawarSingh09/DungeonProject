package dungeonmania.interfaces;

import dungeonmania.dungeon.Dungeon;import dungeonmania.entities.movingentities.Player;

public interface Collectable {

    public void pickup(Player player, Dungeon dungeon);

    public String getType();
     
    public int getEntityId();
}

