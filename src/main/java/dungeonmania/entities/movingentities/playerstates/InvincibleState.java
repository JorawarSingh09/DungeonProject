package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvincibleState extends PlayerState {

    public InvincibleState(Player player) {
        super(player);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void engageBattle(boolean playerDied) {
        player.setPlayerState(new AliveState(player));       
    }

    @Override
    public boolean tick(int remainingDuration) {
        // TODO Auto-generated method stub
        return false;        
    }

    @Override
    public void drinkInvis() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drinkInvinc() {
        // TODO Auto-generated method stub
        
    }
    
}
