package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;

public class BattleRoundTests {

    private List<Boolean> assertBattleCalculations(String enemyType, List<BattleResponse> battles,
            String configFilePath, int numAllies) {
        List<Boolean> playerWinner = new ArrayList<>();
        for (BattleResponse battle : battles) {
            List<RoundResponse> rounds = battle.getRounds();
            double enemyHealth = Double
                    .parseDouble(TestUtils.getValueFromConfigFile(enemyType + "_health", configFilePath));
            double playerAttack = Double.parseDouble(TestUtils.getValueFromConfigFile("player_attack", configFilePath));
            double enemyAttack = Double
                    .parseDouble(TestUtils.getValueFromConfigFile(enemyType + "_attack", configFilePath));
            double swordAttack = Double.parseDouble(TestUtils.getValueFromConfigFile("sword_attack", configFilePath));
            double bowAttack = 2;
            double shieldDefence = Double
                    .parseDouble(TestUtils.getValueFromConfigFile("shield_defence", configFilePath));
            double allyAttackBonus = Double
                    .parseDouble(TestUtils.getValueFromConfigFile("ally_attack", configFilePath));
            double allyDefenceBonus = Double
                    .parseDouble(TestUtils.getValueFromConfigFile("ally_defence", configFilePath));

            for (RoundResponse round : rounds) {
                double weaponAttackAddBonus = 0;
                double weaponDefenceAddBonus = 0;
                double weaponAttackMultiBonus = 0;
                for (ItemResponse weapon : round.getWeaponryUsed()) {
                    if (weapon.getType().equals("sword")) {
                        weaponAttackAddBonus += swordAttack;
                    } else if (weapon.getType().equals("shield")) {
                        weaponDefenceAddBonus += shieldDefence;
                    } else if (weapon.getType().equals("bow")) {
                        weaponAttackMultiBonus += bowAttack;
                    }
                }
                if (weaponAttackMultiBonus == 0)
                    weaponAttackMultiBonus = 1;
                double playerAttackBonus = playerAttack + weaponAttackAddBonus + (allyAttackBonus * numAllies);
                playerAttackBonus = playerAttackBonus * weaponAttackMultiBonus;
                double enemyAttackBonus = enemyAttack - (weaponDefenceAddBonus + (allyDefenceBonus * numAllies));
                assertEquals(round.getDeltaCharacterHealth(), -(enemyAttackBonus / 10));
                assertEquals(round.getDeltaEnemyHealth(), -(playerAttackBonus / 5));
                enemyHealth += round.getDeltaEnemyHealth();
            }
            if (enemyHealth <= 0) {
                playerWinner.add(true);
            } else {
                playerWinner.add(false);
            }
        }
        return playerWinner;
    }

    @Test
    @DisplayName("Test a battle with player and mercenery which lasts several rounds")
    public void testMercenaryBattleWithoutWeapons() {
        DungeonManiaController mc = TestUtils.createDungeon("battle_test", "battleTest");
        List<BattleResponse> battles = mc.tick(Direction.LEFT).getBattles();
        assertEquals(0, battles.size());
        DungeonResponse atBattleDungeon = mc.tick(Direction.RIGHT);
        battles = atBattleDungeon.getBattles();
        assertEquals(1, battles.size());
        List<Boolean> winners = new ArrayList<>();
        winners.add(true);
        assertEquals(assertBattleCalculations("mercenary", battles, "battleTest", 0), winners);
    }

    @Test
    @DisplayName("Test a battle with player and mercenery which lasts several rounds with weapons")
    public void testMercenaryBattleWithWeapons() {
        DungeonManiaController mc = TestUtils.createDungeon("battle_test", "battleTest");
        List<BattleResponse> battles = mc.tick(Direction.LEFT).getBattles();
        assertEquals(0, battles.size());
        mc.tick(Direction.DOWN);
        DungeonResponse pickupSword = mc.tick(Direction.DOWN);
        assertEquals(0, battles.size());
        assertEquals(3, pickupSword.getInventory().size());
        mc.tick(Direction.RIGHT);
        DungeonResponse atBattleDungeon = mc.tick(Direction.LEFT);
        battles = atBattleDungeon.getBattles();
        assertEquals(1, battles.size());
        List<Boolean> winners = new ArrayList<>();
        winners.add(true);
        assertEquals(winners, assertBattleCalculations("mercenary", battles, "battleTest", 0));
        mc.tick(Direction.RIGHT);
        pickupSword = mc.tick(Direction.UP);
        assertEquals(2, pickupSword.getInventory().size());
        assertEquals(2, pickupSword.getBattles().size());
        battles = pickupSword.getBattles();
        winners.add(true);
        assertEquals(winners, assertBattleCalculations("mercenary", battles, "battleTest", 0));
    }

    @Test
    @DisplayName("Test a battle with player and mercenery where merc is ally and weapons")
    public void testMercenaryBattleWithAlly() {
        DungeonManiaController mc = TestUtils.createDungeon("battle_test", "battleTest");
        DungeonResponse dungeon = mc.tick(Direction.LEFT);
        assertEquals(0, dungeon.getBattles().size());
        dungeon = mc.tick(Direction.DOWN);
        assertEquals(0, dungeon.getBattles().size());
        assertEquals(3, dungeon.getInventory().size());
        assertDoesNotThrow(() -> mc.interact("2"));
        mc.tick(Direction.RIGHT);
        dungeon = mc.tick(Direction.UP);
        dungeon = mc.tick(Direction.RIGHT);
        assertEquals(1, dungeon.getBattles().size());
        List<BattleResponse> battles = dungeon.getBattles();
        List<Boolean> winners = new ArrayList<>();
        winners.add(true);
        assertEquals(winners, assertBattleCalculations("mercenary", battles, "battleTest", 1));
        assertEquals(1, dungeon.getInventory().size());
    }

    @Test
    @DisplayName("Test a battle with player with many allies")
    public void testMercenaryBattleWithAllies() {
        DungeonManiaController mc = TestUtils.createDungeon("battle_test", "battleTest");
        DungeonResponse dungeon = mc.tick(Direction.LEFT);
        assertEquals(0, dungeon.getBattles().size());
        dungeon = mc.tick(Direction.DOWN);
        assertEquals(0, dungeon.getBattles().size());
        assertEquals(3, dungeon.getInventory().size());
        assertDoesNotThrow(() -> mc.interact("2"));
        mc.tick(Direction.RIGHT);
        dungeon = mc.tick(Direction.UP);
        assertEquals(2, dungeon.getInventory().size());
        assertDoesNotThrow(() -> mc.interact("3"));
        mc.tick(Direction.RIGHT);
        dungeon = mc.tick(Direction.RIGHT);
        assertEquals(1, dungeon.getBattles().size());
        List<BattleResponse> battles = dungeon.getBattles();
        List<Boolean> winners = new ArrayList<>();
        winners.add(true);
        assertEquals(winners, assertBattleCalculations("mercenary", battles, "battleTest", 2));
        assertEquals(0, dungeon.getInventory().size());
    }
}
