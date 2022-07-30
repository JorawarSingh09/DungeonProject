package dungeonmania.entities.collectableentities.interfaces;

public interface Attacking {
    /**
     * if a weapon is additive or multiplicative to base attack
     */
    public boolean isAdditive();

    public int attackBonus();

    public int getItemId();

}
