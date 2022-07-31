package dungeonmania.entities.movingentities.interfaces;

public interface Health {
    /* Same as Enemy */
    public double getHealth();

    public void setHealth(double health);

    public double getAttackDamage();

    public void loseHealth(double deltaHealth);

    public int getEntityId();

    public String getType();

    public boolean isAlly();

}
