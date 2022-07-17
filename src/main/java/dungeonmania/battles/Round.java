package dungeonmania.battles;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Defending;

public class Round {

    private double playerAttackDamage;
    private double enemyAttackDamage;
    private List<Attacking> attackWeaponryUsed;
    private List<Defending> defenceWeaponryUsed;
    private List<Mercenary> allies;

    public Round(double playerAttackDamage, double enemyAttackDamage, List<Attacking> attackWeaponryUsed,
            List<Defending> defenceWeaponryUsed, List<Mercenary> allies) {
        this.playerAttackDamage = playerAttackDamage;
        this.enemyAttackDamage = enemyAttackDamage;
        this.attackWeaponryUsed = new ArrayList<>();
        attackWeaponryUsed.addAll(attackWeaponryUsed);
        this.defenceWeaponryUsed = new ArrayList<>();
        this.defenceWeaponryUsed.addAll(defenceWeaponryUsed);
        this.allies = allies;
    }

    public double playerHealthChange() {
        return (((-calculateEnemyAttackDamage()) + calculatePlayerDefenceBonus()) / 10);
    }

    public double enemyHealthChange() {
        return ((-calculatePlayerAttackDamage()) / 5);
    }

    public List<Integer> weaponryUsed() {
        List<Integer> weapons = new ArrayList<>();
        for (Attacking attackItem : attackWeaponryUsed) {
            weapons.add(attackItem.getItemId());
        }
        for (Defending defendItem : defenceWeaponryUsed) {
            weapons.add(defendItem.getItemId());
        }
        return weapons;
    }

    private double calculatePlayerAttackDamage() {
        int additiveBonus = 0;
        int multiplicativeBonus = 0;
        for (Attacking item : attackWeaponryUsed) {
            if (item.isAdditive()) {
                additiveBonus += item.battleBonus();
            } else {
                multiplicativeBonus += item.battleBonus();
            }
        }
        if (multiplicativeBonus == 0)
            multiplicativeBonus = 1;
        return ((playerAttackDamage + additiveBonus + allyAttackBonus()) * multiplicativeBonus);
    }

    private double calculateEnemyAttackDamage() {
        return enemyAttackDamage;
    }

    private double calculatePlayerDefenceBonus() {
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
