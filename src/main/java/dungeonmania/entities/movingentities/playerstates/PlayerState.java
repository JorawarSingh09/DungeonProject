package dungeonmania.entities.movingentities.playerstates;

public interface PlayerState {

    public abstract void engageBattle(boolean playerDied);

    public abstract void tick(int remainingDuration);

    public abstract void drinkInvis();

    public abstract void drinkInvinc();
}
