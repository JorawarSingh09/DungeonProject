package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvincibleState implements PlayerState {

    Player player; 

    public InvincibleState(Player player) {
        this.player = player;
    }

    public void tick(int queueSize) {
        if (queueSize == 0) {
            player.setPlayerState(new AliveState(player));
        }
    }

    public void engageBattle(boolean playerDied) {
        // The invincibility potion has been depleted
        player.setPlayerState(new AliveState(player));
    }

    public void drinkInvis() {
        player.setPlayerState(new InvincibleState(player));

    }

    @Override
    public void drinkInvinc() {
        // The player is already invisible.
    }

}
