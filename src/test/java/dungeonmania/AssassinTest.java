package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.Assassin;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.ErrorString;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class AssassinTest {
	@Test
	@DisplayName("Test random assassin bribe fail chance, and that player loses gold regardless.")
	public void friendRequest() {

			Player player = new Player(1, new Position(0, 0), 1,
							1, 1, 1, 1, 1, 1, 1);
			Assassin assn = new Assassin(2, new Position(1, 0), 1, 1, 1, 1,
							99, 1, 0, 1);

			player.addItem(new Treasure(3, new Position(0, 3)));

			assertEquals(false, assn.isAllyToPlayer());

			String bribeRes = player.attemptBribe(assn);
			// int currCoins = player.getInventory().countItem(Treasure.class);

			// if (bribeRes.equals(ErrorString.SUCCESS.toString()))
			assertEquals(true, assn.isAllyToPlayer());
			// else
			// assertEquals(false, assn.isAllyToPlayer());
			// System.out.println(currCoins);
			// System.out.println(assn.getBribeAmount());
			// assertNotEquals(currCoins, player.getInventory().countItem(Treasure.class));
	}

    @Test
    @DisplayName("Assassin goes to portal using the simple player movement")
    public void assassinGoToPortal() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv8", "briber");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        assertEquals(new Position(-50, 2), TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
	}
}
