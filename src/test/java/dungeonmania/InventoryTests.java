package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class InventoryTests {
    @Test
    @DisplayName("Check item from id player pickup an item")
    // should this not be an integration test
    public void pickupItemPlayerId() {
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        Treasure treasure = new Treasure(1, new Position(2, 0));
        player.addItem(treasure);
        assertTrue(player.getItemFromId(1) instanceof Treasure);
        player.removeItem(treasure);
        assertTrue(player.getInventoryItems().size() == 0);
    }
}