package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTileTests {

    @Test
    @DisplayName("Test player does not get stuck on the swamp tile")
    public void swampTilePlayerStuck() {
        DungeonManiaController dmc = TestUtils.createDungeon("swamptile", "bribe_amount_3");
        dmc.tick(Direction.DOWN);
        assertEquals(new Position(-1, 0), TestUtils.getEntities(dmc.getDungeonResponseModel(), "player").get(0).getPosition());
        dmc.tick(Direction.DOWN);
        assertEquals(new Position(-1, 1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "player").get(0).getPosition());
    }    

    @Test
    @DisplayName("Test Spider gets stuck on the swamp tile and can leave, where movement factor is 3")
    public void swampTileSpiderStuck() {
        DungeonManiaController dmc = TestUtils.createDungeon("swamptile", "bribe_amount_3");
        // Tick 1: the tick where the spider moves onto the swamptile
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        // Tick 2: the first tick where the spider is stuck on the swamp tile
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        // Tick 3: the second tick where the spider is stuck on the swamp tile
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        // Tick 4: the third tick where the spider is stuck on the swamp tile
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        // Tick 5: the tick where the spider moves off the swamp tile
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-1, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
    }

    @Test
    @DisplayName("Test Mercenary gets stuck on the swamp tile and can leave")
    public void swampTileMercStuck() {
        DungeonManiaController dmc = TestUtils.createDungeon("swamptile", "bribe_amount_3");
        
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-1, 4), TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-1, 4), TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-2, -1), TestUtils.getEntities(dmc.getDungeonResponseModel(), "spider").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertFalse((new Position(-1, 4)).equals(TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition()));
    }

}
