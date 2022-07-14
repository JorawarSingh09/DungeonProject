package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvisibleState extends PlayerState {

    public InvisibleState(Player player) {
        super(player);
    }

    @Override
    public void drinkInvinc() {
        player.setPlayerState(player.getInvincState());
        
    }
    
}
