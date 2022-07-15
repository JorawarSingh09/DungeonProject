package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvincibleState extends PlayerState {

    public InvincibleState(Player player) {
        super(player);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick(int queueSize) {
        if (queueSize == 0) {
            player.setPlayerState(player.getAliveState());
        }
    }

    public void engageBattle(boolean playerDied) {
        player.setPlayerState(player.getAliveState());
    }

    public void drinkInvis() {
        player.setPlayerState(player.getInvisState());

    }

}
