package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Hydra;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.movements.RandomMovementStrategy;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class HydraTests {
    @Test
    @DisplayName("Test the state of a hydra")
    public void hydraJsonTesting() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Hydra hydra = new Hydra(1, new Position(1, 100), 10, 10, 10, 10);
        assertTrue(hydra.isTangible());
        assertTrue(hydra.getMovementStrategy() instanceof RandomMovementStrategy);
        assertFalse(hydra.isAllyToPlayer());
        assertFalse(hydra.isAlly());
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", 1);
        entityJSON.addProperty("type", "hydra");
        entityJSON.addProperty("x", 1);
        entityJSON.addProperty("y", 100);
        assertEquals(entityJSON, hydra.getJson());

    }

    @Test
    @DisplayName("Test the movement of a hydra")
    public void hydraMovementTesting() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Hydra hydra = new Hydra(1, new Position(1, 100), 10, 10, 10, 10);
        hydra.updatePosition(dungeon, player);
        List<Position> xs = new ArrayList<>();
        xs.add(new Position(0, 100));
        xs.add(new Position(2, 100));
        xs.add(new Position(1, 101));
        xs.add(new Position(1, 99));
        assertTrue(xs.contains(hydra.getPosition()));
    }

}