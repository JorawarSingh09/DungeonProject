package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Storeable;
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
        return EntityString.MIDNIGHTARMOUR.toString();
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
