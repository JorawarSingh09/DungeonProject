package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class AliveState extends PlayerState {

    public AliveState(Player player) {
        super(player);
        //TODO Auto-generated constructor stub
    }

    public void engageBattle(boolean playerDied) {
        if (playerDied) {
            player.setPlayerState(player.getDeadState());
        }
    }

    @Override
    public void drinkInvis() {
        player.setPlayerState(player.getInvisState());
    }

    @Override
    public void drinkInvinc() {
        player.setPlayerState(player.getInvincState());
    }
    
}
