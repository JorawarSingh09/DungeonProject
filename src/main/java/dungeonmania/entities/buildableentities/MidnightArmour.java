package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.interfaces.Attacking;
import dungeonmania.entities.collectableentities.interfaces.Defending;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class MidnightArmour extends Entity implements Buildable, Storeable, Attacking, Defending {

    private int attack;
    private int defence;

    public MidnightArmour(int id, int attack, int defence) {
        super(id, new Position(0, 0), false, false);
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public boolean isAdditive() {
        return true;
    }

    @Override
    public int attackBonus() {
        return attack;
    }

    @Override
    public int getItemId() {
        return getEntityId();
    }

    @Override
    public String getType() {
        return "midnight_armour";
    }

    @Override
    public void build(Player player) {

    }

    @Override
    public void consumeItems(Player player) {

    }

    @Override
    public int defenceBonus() {
        return defence;
    }

}
