package dungeonmania.entities.movingentities.playerstates;

public interface PlayerState {
    public void engageBattle(boolean playerDied);
    public void tick();
    public void drinkInvis();
    public void drinkInvinc();
}
