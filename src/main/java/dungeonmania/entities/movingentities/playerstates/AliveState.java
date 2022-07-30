package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class AliveState implements PlayerState {

    Player player;

    public AliveState(Player player) {
        this.player = player;
    }

    public void engageBattle(boolean playerDied) {
        // Player no longer has a state as they've died in battle
        if (playerDied) {
            player.setPlayerState(null);
        }
    }

    public void drinkInvis() {
        player.setPlayerState(new InvisibleState(player));
    }

    public void drinkInvinc() {
        player.setPlayerState(new InvincibleState(player));
    }

    public void tick(int remainingDuration) {
        // No potion activated, stays alive        
    }

}
