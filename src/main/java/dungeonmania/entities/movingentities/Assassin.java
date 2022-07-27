package dungeonmania.entities.movingentities;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Static;
import dungeonmania.util.Position;

public class Assassin extends Mercenary {
    private double failChance;
    private int reconRadius;

    public Assassin(int id, Position position, boolean interactable, boolean collidable,
            double ally_attack, double ally_defence, double assassin_attack,
            double assassin_health, int bribe_radius, int bribe_amount, double fail_chance, int reconRadius) {

        super(id, position, interactable, collidable, ally_attack, ally_defence, assassin_attack,
                assassin_health, bribe_radius, bribe_amount);

        this.failChance = fail_chance;
        this.reconRadius = reconRadius;
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

    @Override
    public void updatePosition(Dungeon dungeon, Player player) {
        if (player.getPlayerState().equals(player.getInvisState())) {
            if (Position.getDistanceBetweenTwoPositions(player.getPosition(), this.getPosition()) > reconRadius) {
                currMoveStrat = playerInvis;
            } else {
                currMoveStrat = standard;
            }

        } else if (player.getPlayerState().equals(player.getInvincState()) && !isAlly && !currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else if (player.getPlayerState().equals(player.getAliveState()) && currMoveStrat.isReversed()) {
            currMoveStrat = standard;
            currMoveStrat.reversePath();
        } else {
            currMoveStrat = standard;
        }

        boolean foundPortal = false;
        for (Static portal : dungeon.getStaticsOnBlock(currMoveStrat.getNextPosition(dungeon, player))) {
            if (portal instanceof Portal) {
                ((Portal) portal).mercenaryMoveOnto(this, dungeon, Position.getDirection(this.getPosition(),
                        currMoveStrat.getNextPosition(dungeon, player)));

                foundPortal = true;
                break;
            }
        }

        if (!foundPortal)
            currMoveStrat.updateMovement(dungeon, player);
    }
}