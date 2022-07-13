package dungeonmania.controllers;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.interfaces.Static;

/**
 * controller for events that need to be updatedeach tick, entity location, and
 * such
 */
public class tickController {

    public void enemyCell() {

    }

    public boolean checkSwitchBehaviour(Dungeon dungeon) {
        boolean allTriggered = true;
        for (Entity entity : dungeon.getEntitiesOfType("switch")) {
            for (Static foundEntity : dungeon.getStaticsOnBlock(entity.getPosition())) {
                if (foundEntity instanceof Boulder) {
                    ((FloorSwitch) entity).setTriggered(true);
                    System.out.println("hi");
                    ((FloorSwitch) entity).checkBomb(dungeon);
                } else {
                    allTriggered = false;
                }
            }
            // check if there is a boulder ontop of switch
            // check if tehre is a bomb ontop of switch

            // why dont we jsut check after moving boulder
        }

        return allTriggered;
    }
}
