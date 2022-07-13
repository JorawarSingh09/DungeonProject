package dungeonmania.interfaces;

public interface Regenerative {
    public int getRemainingDuration();
    public void decrementDuration();
    public int getItemId();
}
