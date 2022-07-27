package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.getGoals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class GoalTests {
    @Test
    @DisplayName("Test a map with 4 'or' complex goals")
    public void mixAll() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_complexGoalsTest_mixAll", "c_complexGoalsTest_andAll");
        assertTrue(getGoals(res).contains(":exit"));
        assertTrue(getGoals(res).contains(":treasure"));
        assertTrue(getGoals(res).contains(":boulders"));
        assertTrue(getGoals(res).contains(":enemies"));

        // kill spider
        res = dmc.tick(Direction.RIGHT);
        assertTrue(getGoals(res).contains(":exit"));
        assertTrue(getGoals(res).contains(":treasure"));
        assertTrue(getGoals(res).contains(":boulders"));
        assertFalse(getGoals(res).contains(":enemies"));

        // move down
        res = dmc.tick(Direction.DOWN);
        assertTrue(getGoals(res).contains(":exit"));
        assertTrue(getGoals(res).contains(":treasure"));
        assertTrue(getGoals(res).contains(":boulders"));
        assertFalse(getGoals(res).contains(":enemies"));

        // pickup treasure
        res = dmc.tick(Direction.RIGHT);
        assertTrue(getGoals(res).contains(":exit"));
        assertFalse(getGoals(res).contains(":treasure"));
        assertTrue(getGoals(res).contains(":boulders"));
        assertFalse(getGoals(res).contains(":enemies"));

        // move to exit
        res = dmc.tick(Direction.DOWN);
        assertEquals("", getGoals(res));
    }
}
