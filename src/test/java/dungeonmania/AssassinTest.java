package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.Assassin;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.ErrorString;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class AssassinTest {
	@Test
	@DisplayName("Test Assassin with infinite bribe success rate.")
	public void alwaysBribableAssassin() {

		Player player = new Player(1, new Position(0, 0), 1,
				1, 1, 1, 1, 1, 1, 1);

		Assassin assn = new Assassin(2, new Position(1, 0), 1, 1, 1, 1,
				99, 1, 0, 1);

		// assassin should not be friendly by default
		assertEquals(false, assn.isAllyToPlayer());

		player.addItem(new Treasure(3, new Position(0, 3)));
		int currCoins = player.getInventory().countItem(Treasure.class);
		// sanity check treasure amount.
		assertEquals(1, currCoins);

		String bribeRes = player.attemptBribe(assn);
		// bribing assassin with 0 fail rate should always work.
		assertEquals(ErrorString.SUCCESS.toString(), bribeRes);
		// and assassin should now be our friend.
		assertEquals(true, assn.isAllyToPlayer());

		// it should have cost us to get him to be our friend tho...
		currCoins = player.getInventory().countItem(Treasure.class);
		assertEquals(0, currCoins);
	}

	@Test
	@DisplayName("Test Assassin with infinite bribe success rate.")
	public void neverBribableAssassin() {
		Player player = new Player(1, new Position(0, 0), 1,
				1, 1, 1, 1, 1, 1, 1);

		Assassin assn = new Assassin(2, new Position(1, 0), 1, 1, 1, 1,
				99, 1, 1, 1);

		// assassin should not be friendly by default
		assertEquals(false, assn.isAllyToPlayer());

		player.addItem(new Treasure(3, new Position(0, 3)));
		player.addItem(new Treasure(4, new Position(0, 4)));
		player.addItem(new Treasure(5, new Position(0, 5)));
		player.addItem(new Treasure(6, new Position(0, 6)));
		int startCoins = player.getInventory().countItem(Treasure.class);

		// sanity check treasure amount.
		assertEquals(4, startCoins);

		String bribeRes = player.attemptBribe(assn);
		// bribing assassin with 1.0 fail rate should never work.
		assertEquals(ErrorString.BRIBECHANCE.toString(), bribeRes);

		// assassin should still not be our friend.
		assertEquals(false, assn.isAllyToPlayer());

		int currCoins = player.getInventory().countItem(Treasure.class);
		// player should have lost 1 coin
		assertEquals(startCoins - assn.getBribeAmount(), currCoins);

		// now bribe 3 times in a row, all should fail, and bankrupt player.
		for (int i = 0; i < 3; i++) {
			bribeRes = player.attemptBribe(assn);
			assertEquals(ErrorString.BRIBECHANCE.toString(), bribeRes);
		}
		currCoins = player.getInventory().countItem(Treasure.class);
		assertEquals(0, currCoins);
		assertEquals(false, assn.isAllyToPlayer());
	}

	@Test
	@DisplayName("Test Assassin with 0.5 bribe success rate.")
	public void sometimesBribableAssassin() {
		// with 1000 iterations, a 0.5 success rate, and 1 bribe per iteration, we
		// should expect ~ 500 successful bribes.
		int bribeSuccessCounter = 0;
		for (int loopCount = 0; loopCount < 1000; loopCount++) {
			Player player = new Player(1, new Position(0, 0), 1,
					1, 1, 1, 1, 1, 1, 1);
			Assassin assn = new Assassin(2, new Position(1, 0), 1, 1, 1, 1,
					99, 1, 0.5, 1);

			player.addItem(new Treasure(3, new Position(0, 3)));
			String res = player.attemptBribe(assn);
			if (res.equals(ErrorString.SUCCESS.toString()))
				bribeSuccessCounter += 1;
		}
		assertTrue(bribeSuccessCounter > 400 && bribeSuccessCounter < 600);

	}

	@Test
	@DisplayName("Assassin goes to portal using the simple player movement")
	public void assassinGoToPortal() {
		DungeonManiaController dmc = TestUtils.createDungeon("adv8", "briber");
		dmc.tick(Direction.DOWN);
		dmc.tick(Direction.DOWN);
		assertEquals(new Position(-50, 2),
				TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
	}

}
