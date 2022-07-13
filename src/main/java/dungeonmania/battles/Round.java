package dungeonmania.battles;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Defending;

public class Round {

    private int playerAttackDamage;
    private int enemyAttackDamage;
    private List<Attacking> attackWeaponryUsed;
    private List<Defending> defenceWeaponryUsed;
    private List<Mercenary> allies;

    public Round(int playerAttackDamage, int enemyAttackDamage, List<Attacking> attackWeaponryUsed,
            List<Defending> defenceWeaponryUsed, List<Mercenary> allies) {
        this.playerAttackDamage = playerAttackDamage;
        this.enemyAttackDamage = enemyAttackDamage;
        this.attackWeaponryUsed = attackWeaponryUsed;
        this.defenceWeaponryUsed = defenceWeaponryUsed;
        this.allies = allies;
    }

    public int playerHealthChange() {
        return (((-calculateEnemyAttackDamage()) + calculatePlayerDefenceBonus())/10);
    }

    public int enemyHealthChange() {
        return ((-calculatePlayerAttackDamage())/5);
    }

    private int calculatePlayerAttackDamage() {
        int additiveBonus = 0;
        int multiplicativeBonus = 0;
        for (Attacking item : attackWeaponryUsed) {
            if (item.isAdditive()) {
                additiveBonus += item.battleBonus();
            } else {
                multiplicativeBonus += item.battleBonus();
            }
        }
        return ((playerAttackDamage + additiveBonus + allyAttackBonus()) * multiplicativeBonus);
    }

    private int calculateEnemyAttackDamage() {
        return enemyAttackDamage;
    }

    private int calculatePlayerDefenceBonus() {
        int defenceBonus = 0;
        for (Defending item : defenceWeaponryUsed) {
            defenceBonus += item.battleBonus();
        }
        return (defenceBonus + allyDefenceBonus());
    }

    private int allyAttackBonus() {
        int attackBonus = 0;
        for (Mercenary ally : allies) {
            attackBonus += ally.getAllyAttackDamage();
        }
        return attackBonus;
    }

    private int allyDefenceBonus() {
        int defenceBonus = 0;
        for (Mercenary ally : allies) {
            defenceBonus += ally.getAllyDefenceBonus();
        }
        return defenceBonus;
    }

}