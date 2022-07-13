package dungeonmania.controllers;

import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Direction;

public class MovementController {
    
    Player player;
    
    public MovementController(Player player) {
    }

    public void movePlayer(Direction movement) {
        player.updatePosition(movement);
    }

    public void updateEntityPositions(Direction movement) {
        ;
    }
}
