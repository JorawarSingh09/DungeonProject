package dungeonmania.entities.buildableentities;

import dungeonmania.entities.movingentities.Player;

public interface Buildable {
    public void build(Player player);

    public void consumeItems(Player player);

}
