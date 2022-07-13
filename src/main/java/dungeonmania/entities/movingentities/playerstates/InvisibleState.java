package dungeonmania.entities.movingentities.playerstates;

import dungeonmania.entities.movingentities.Player;

public class InvisibleState extends PlayerState {

    public InvisibleState(Player player) {
        super(player);
    }
    
    @Override
    public void engageBattle(boolean playerDied) {
        // Do nothing as cannot engage
    }

    @Override
    public boolean tick(int remainingDuration) {
        if (remainingDuration == 0);
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
