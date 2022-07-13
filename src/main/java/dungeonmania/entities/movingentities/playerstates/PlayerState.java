package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public abstract class PlayerState {
    Player player;

    public PlayerState(Player player) {
        this.player = player;
    }
    
    public void engageBattle(boolean playerDied) {
        // Do nothing
    }

    public void tick() {
        // Do nothing
    }

    public void drinkInvis() {
        // Do nothing
    }

    public void drinkInvinc() {
        // Do nothing
    }
}
