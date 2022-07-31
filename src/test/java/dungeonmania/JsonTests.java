package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Bomb;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Sword;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.Spider;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.entities.movingentities.properties.movements.PlayerMovementStrategy;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class JsonTests {

    @Test
    @DisplayName("Player get json")
    public void testPlayerToJson() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        dungeon.updateMovement(Direction.DOWN);
        player.getJson();
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", 0);
        entityJSON.addProperty("type", "player");
        entityJSON.addProperty("x", 1);
        entityJSON.addProperty("y", 2);
        entityJSON.addProperty("playerState", player.getPlayerState().toString());
        entityJSON.addProperty("health", 10.0);

        JsonArray allys = new JsonArray();
        entityJSON.add("allys", new JsonArray());

        JsonArray inventorys = new JsonArray();
        entityJSON.add("inventory", new JsonArray());

        JsonArray potQ = new JsonArray();
        entityJSON.add("potionQ", new JsonArray());
        assertEquals(entityJSON, player.getJson());
        assertTrue(player.isAlly());
        assertTrue(player.isTangible());
        assertTrue(player.getMovementStrategy() instanceof PlayerMovementStrategy);
        player.setPlayerStateFromJSON("Invincible");
        assertTrue(player.getPlayerState() instanceof InvincibleState);
        player.setPlayerStateFromJSON("Invisible");
        assertTrue(player.getPlayerState() instanceof InvisibleState);
    }

    @Test
    @DisplayName("Mercenary get json")
    public void testMercToJson() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Mercenary mercenary = new Mercenary(1, new Position(1, 100), 10, 10, 10, 10, 10, 10);
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", 1);
        entityJSON.addProperty("type", "mercenary");
        entityJSON.addProperty("x", 1);
        entityJSON.addProperty("y", 100);
        entityJSON.addProperty("isAlly", false);
        entityJSON.addProperty("mindControl", false);
        entityJSON.addProperty("durability", 0);

        assertEquals(entityJSON, mercenary.getJson());
    }

    @Test
    @DisplayName("spider get json")
    public void testSpiderToJson() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Spider spider = new Spider(1, new Position(1, 100), 10, 10);
        dungeon.addEntity(spider);
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", 1);
        entityJSON.addProperty("type", "spider");
        entityJSON.addProperty("x", 1);
        entityJSON.addProperty("y", 100);
        entityJSON.add("movement", spider.getMovementStrategy().getJson());
        assertEquals(entityJSON, spider.getJson());
    }

    @Test
    @DisplayName("key get json")
    public void testKeytoJson() {
        Dungeon dungeon = new Dungeon("bob", 1);
        Key key = new Key(1, new Position(1, 1), 1);
        dungeon.addEntity(key);
        JsonObject keyJson = key.getJson();

        assertEquals(keyJson.get("type").getAsString(),
                key.getType());
        assertEquals(keyJson.get("x").getAsInt(),
                key.getPosition().getX());
        assertEquals(keyJson.get("y").getAsInt(),
                key.getPosition().getY());
        assertEquals(keyJson.get("key").getAsInt(),
                key.getKeyPair());
    }

    @Test
    @DisplayName("arrow to json")
    public void testArrowtojson() {
        Arrow arrow = new Arrow(1, new Position(0, 0));
        JsonObject arrowJson = arrow.getJson();

        assertEquals(arrow.getPosition().getX(), arrowJson.get("x").getAsInt());
        assertEquals(arrow.getPosition().getY(), arrowJson.get("y").getAsInt());
        assertEquals(arrow.getEntityId(), arrowJson.get("id").getAsInt());
    }

    @Test
    @DisplayName("Bomb to json")
    public void testBombtoJson() {
        Bomb bomb = new Bomb(1, new Position(0, 0), 2);
        JsonObject bombJson = bomb.getJson();

        assertEquals(bomb.getEntityId(), bombJson.get("id").getAsInt());
        assertEquals(bomb.getPosition().getX(), bombJson.get("x").getAsInt());
        assertEquals(bomb.getPosition().getY(), bombJson.get("y").getAsInt());
        assertEquals(bomb.isCollidable(), bombJson.get("collidable").getAsBoolean());
    }

    @Test
    @DisplayName("invincibility potion to json")
    public void testInvincibilityPotionJson() {
        InvincibilityPotion potion = new InvincibilityPotion(1, new Position(0,0), 10);
        JsonObject potionJson = potion.getJson();

        assertEquals(potion.getEntityId(), potionJson.get("id").getAsInt());
        assertEquals(potion.getPosition().getX(), potionJson.get("x").getAsInt());
        assertEquals(potion.getPosition().getY(), potionJson.get("y").getAsInt());
        assertEquals(potion.getRemainingDuration(), potionJson.get("duration").getAsInt());

    }

    @Test
    @DisplayName("Invisibility Potion JSon")
    public void testInvisibilityPotionJson() {
        InvisibilityPotion potion = new InvisibilityPotion(1, new Position(0,0), 10);
        JsonObject potionJson = potion.getJson();

        assertEquals(potion.getEntityId(), potionJson.get("id").getAsInt());
        assertEquals(potion.getPosition().getX(), potionJson.get("x").getAsInt());
        assertEquals(potion.getPosition().getY(), potionJson.get("y").getAsInt());
        assertEquals(potion.getRemainingDuration(), potionJson.get("duration").getAsInt());
    }

    @Test
    @DisplayName("Sword Json")
    public void testSwordJson() {
        Sword sword = new Sword(1, new Position(0,0), 10, 10);
        JsonObject swordJson = sword.getJson();
        assertEquals(sword.getEntityId(), swordJson.get("id").getAsInt());
        assertEquals(sword.getPosition().getX(), swordJson.get("x").getAsInt());
        assertEquals(sword.getPosition().getY(), swordJson.get("y").getAsInt());
        assertEquals(sword.getDurability(), swordJson.get("durability").getAsInt());
    }

}