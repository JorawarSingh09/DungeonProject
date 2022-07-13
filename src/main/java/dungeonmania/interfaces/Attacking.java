package dungeonmania.interfaces;

public interface Attacking {
    /**
     * if a weapon is additive or multiplicative to base attack
     */
    public boolean isAdditive();
    public int battleBonus();
    
}
