package dungeonmania.entities.movingentities;

import dungeonmania.enums.EntityString;
import dungeonmania.util.Position;

public class Assassin extends Mercenary {
    private double failChance;

    public Assassin(int id, Position position, boolean interactable, boolean collidable,
            double ally_attack, double ally_defence, double assassin_attack,
            double assassin_health, int bribe_radius, int bribe_amount, double fail_chance) {

        super(id, position, interactable, collidable, ally_attack, ally_defence, assassin_attack,
                assassin_health, bribe_radius, bribe_amount);

        failChance = fail_chance;
    }

    public double getFailChance() {
        return failChance;
    }

    public void setFailChance(double failChance) {
        this.failChance = failChance;
    }

    @Override
    public String getType() {
        return EntityString.ASSASSIN.toString();
    }
}