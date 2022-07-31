package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvisibleState implements PlayerState {
    Player player;

    public InvisibleState(Player player) {
        this.player = player;
    }

    public void tick(int queueSize) {
        if (queueSize == 0) {
            player.setPlayerState(new AliveState(player));
        }
    }

    public void drinkInvinc() {
        player.setPlayerState(new InvincibleState(player));

    }

    public void engageBattle(boolean playerDied) {
        // The player cannot engage in battle when invisible
        return;
    }

    public void drinkInvis() {
        // The player is already invisible
        return;
    }

    @Override
    public String toString() {
        return "Invisible";
    }

}
