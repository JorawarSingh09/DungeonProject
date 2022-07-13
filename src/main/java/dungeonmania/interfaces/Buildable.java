package dungeonmania.interfaces;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;

public interface Buildable {
    public void build(Player player);
    
    public void consumeItems(Player player);
}
