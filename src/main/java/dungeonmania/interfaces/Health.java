package dungeonmania.interfaces;

public interface Health {
    /*Same as Enemy */
    public int getHealth();
    public void setHealth(int health);
    public int getAttackDamage();
    public void loseHealth(int deltaHealth);
    public int getEntityId();
    public String getType();
}
