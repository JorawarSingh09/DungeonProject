package dungeonmania;

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

    @Test
    @DisplayName("Test hydra with 1.0 absorption chance")
    public void hydraAlwaysAbsorb() {
        // Attacks should never succeed and hydra should always absord.
        Dungeon dng = new Dungeon("nameOfDungeon", 1);
        Player p = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dng.addEntity(p);
        dng.setPlayer(p);

        Hydra h = new Hydra(1, new Position(1, 2), 10, 10, 1.0, 10);
        dng.addEntity(h);

        double ogHealth = h.getHealth();
        h.loseHealth(10);
        assertEquals(ogHealth + h.getIncreaseAmount(), h.getHealth());

        ogHealth = h.getHealth();
        h.loseHealth(10);
        assertEquals(ogHealth + h.getIncreaseAmount(), h.getHealth());

        ogHealth = h.getHealth();
        h.loseHealth(10);
        h.loseHealth(10);
        h.loseHealth(10);
        assertEquals(ogHealth + h.getIncreaseAmount() * 3, h.getHealth());
    }

    @Test
    @DisplayName("Test hydra with 0.0 absorption chance")
    public void hydraNeverAbsorb() {
        // Attacks should always succeed.
        Hydra h = new Hydra(1, new Position(1, 2), 10, 10, 0.00, 10);

        double ogHealth = h.getHealth();
        h.loseHealth(-1);
        assertEquals(ogHealth - 1, h.getHealth());

        ogHealth = h.getHealth();
        h.loseHealth(-5);
        assertEquals(ogHealth - 5, h.getHealth());
    }

    @Test
    @DisplayName("Test hydra with 0.5 absorption chance")
    public void hydraSometimesAbsorb() {

        // since damage == heal-amount, and the absorption rate is 50%,
        // hydra's overall health shouldnt move too much from the og value.
        Hydra h = new Hydra(1, new Position(1, 2), 10, 10000, 0.5, 1);
        double ogHealth = h.getHealth();
        for (int i = 0; i < 10000; i++) {
            h.loseHealth(-1);
        }
        assertTrue(h.getHealth() > (ogHealth - 1000) && h.getHealth() < (ogHealth + 1000));

        // similarly, if we double the damage, and keep the absorption rate, we should
        // expect hydra's health == 1/2 after many many attacks.
        h = new Hydra(1, new Position(1, 2), 10, 10000, 0.5, 1);
        ogHealth = h.getHealth();
        for (int i = 0; i < 10000; i++) {
            h.loseHealth(-2);
        }
        assertTrue(h.getHealth() > (ogHealth / 2) - 1000 && h.getHealth() < (ogHealth / 2) + 1000);
    }
}