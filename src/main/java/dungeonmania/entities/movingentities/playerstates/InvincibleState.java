package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvincibleState extends PlayerState {

    public InvincibleState(Player player) {
        super(player);
        //TODO Auto-generated constructor stub
    }

    public void engageBattle(boolean playerDied) {
        player.setPlayerState(new AliveState(player));       
    }

    public void drinkInvis() {
        player.setPlayerState(new InvisibleState(player));
        
    }
    
}
