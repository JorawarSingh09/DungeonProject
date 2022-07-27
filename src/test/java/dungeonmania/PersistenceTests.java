package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;

public class PersistenceTests {

    // @Test
    // @DisplayName("test that player location is saved and loaded")
    // public void basicPersistence() {
    //     DungeonManiaController dmc = new DungeonManiaController();
    //     DungeonResponse savedRes = dmc.newGame("lonePlayer", "bribe_amount_3");
    //     Position originalPlayerPos = getEntities(savedRes, "player").get(0).getPosition();
    //     System.out.println(originalPlayerPos.getX() + " " + originalPlayerPos.getY());

    //     Position newPlayerPosition = getEntities(dmc.tick(Direction.DOWN), "player").get(0).getPosition();
    //     System.out.println(newPlayerPosition.getX() + " " + newPlayerPosition.getY());
    //     assertNotEquals(originalPlayerPos, newPlayerPosition);

    //     dmc.saveGame(savedRes.getDungeonName());
    //     Position posAfterSave = getEntities(dmc.tick(Direction.DOWN), "player").get(0).getPosition();

    //     DungeonResponse loadedDungeon = dmc.loadGame(dmc.saves().get(0));

    //     assertNotEquals(originalPlayerPos, getEntities(savedRes, "player").get(0).getPosition());
    //     assertNotEquals(posAfterSave, getEntities(savedRes, "player").get(0).getPosition());
    //     assertEquals(savedRes.getDungeonId(), loadedDungeon.getDungeonId());
    // }

}
