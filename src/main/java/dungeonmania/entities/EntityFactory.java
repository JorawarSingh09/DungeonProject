package dungeonmania.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.buildableentities.Bow;
import dungeonmania.entities.buildableentities.MidnightArmour;
import dungeonmania.entities.buildableentities.Sceptre;
import dungeonmania.entities.buildableentities.Shield;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Bomb;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Sunstone;
import dungeonmania.entities.collectableentities.Sword;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.Wood;
import dungeonmania.entities.movingentities.Assassin;
import dungeonmania.entities.movingentities.Hydra;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.Spider;
import dungeonmania.entities.movingentities.ZombieToast;
import dungeonmania.entities.spawners.SpiderSpawn;
import dungeonmania.entities.spawners.ZombieToastSpawner;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.entities.staticentities.Door;
import dungeonmania.entities.staticentities.Exit;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.entities.staticentities.SwampTile;
import dungeonmania.entities.staticentities.Wall;

import dungeonmania.util.LoadConfig;
import dungeonmania.util.Position;

public class EntityFactory {

    public static void makeEntities(JsonArray entities, Dungeon dungeon, boolean isSaved, LoadConfig loadedConfig) {
        Map<String, List<Portal>> portals = new HashMap<>();

        for (JsonElement entity : entities) {
            int x = Integer.parseInt(((JsonObject) entity).get("x").toString());
            int y = Integer.parseInt(((JsonObject) entity).get("y").toString());

            String type = ((JsonObject) entity).get("type").getAsString();
            switch (type) {

                case "player":

                    Player player = new Player(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.player_attack, loadedConfig.player_health,
                            loadedConfig.bow_durability, loadedConfig.shield_durability, loadedConfig.shield_defence,
                            loadedConfig.midnight_armour_attack, loadedConfig.midnight_armour_defence,
                            loadedConfig.mind_control_duration);

                    dungeon.addEntity(player);
                    dungeon.setPlayer(player);
                    dungeon.setSpiderSpawner(
                            new SpiderSpawn(loadedConfig.spider_spawn_rate, new Position(x, y),
                                    loadedConfig.spider_attack,
                                    loadedConfig.spider_health));
                    if (isSaved) {
                        JsonArray inventory = ((JsonObject) entity).get("inventory").getAsJsonArray();
                        for (JsonElement item : inventory) {

                            player.addItem(makeItem((JsonObject) item,
                                    dungeon, dungeon.getPlayer()));
                        }
                        player.setHealth(
                                Double.parseDouble(
                                        ((JsonObject) entity).get("health").toString()));
                        player.setPlayerStateFromJSON(
                                ((JsonObject) entity).get("playerState").toString());
                    }
                    break;

                case "wall":
                    dungeon.addEntity(new Wall(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "exit":
                    dungeon.addEntity(new Exit(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "boulder":
                    dungeon.addEntity(new Boulder(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "switch":
                    FloorSwitch fs = (new FloorSwitch(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    if (isSaved) {
                        fs.setTriggered(
                                ((JsonObject) entity).get("triggered")
                                        .getAsBoolean());
                    }
                    dungeon.addEntity(fs);
                    break;
                case "door":
                    Door door = (new Door(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("key").toString())));
                    if (isSaved) {
                        door.setCollidable(
                                ((JsonObject) entity).get("collidable")
                                        .getAsBoolean());
                    }
                    dungeon.addEntity(door);
                    break;
                case "portal":
                    String colour = ((JsonObject) entity).get("colour").getAsString();
                    Portal portal = new Portal(dungeon.getCurrMaxEntityId(), new Position(x, y), colour);
                    dungeon.addEntity(portal);
                    if (portals.containsKey(colour)) {
                        portals.get(colour).add(portal);
                    } else {
                        List<Portal> portalsPair = new ArrayList<>();
                        portalsPair.add(portal);
                        portals.put(colour, portalsPair);
                    }
                    break;
                case "zombie_toast_spawner":
                    dungeon.addEntity(new ZombieToastSpawner(dungeon.getCurrMaxEntityId(), new Position(x, y), true,
                            true, loadedConfig.zombie_spawn_rate, loadedConfig.zombie_attack,
                            loadedConfig.zombie_health));
                    break;
                case "spider":
                    dungeon.addEntity(new Spider(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.spider_attack, loadedConfig.spider_health));
                    break;
                case "zombie_toast":
                    dungeon.addEntity(new ZombieToast(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.zombie_attack, loadedConfig.zombie_health));
                    break;
                case "mercenary":
                    Mercenary merc = (new Mercenary(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.ally_attack,
                            loadedConfig.ally_defence, loadedConfig.mercenary_attack, loadedConfig.mercenary_health,
                            loadedConfig.bribe_radius,
                            loadedConfig.bribe_amount));
                    if (isSaved && ((JsonObject) entity).get("isAlly")
                            .getAsBoolean()) {
                        dungeon.addAlly(merc, ((JsonObject) entity)
                                .get("mindControl")
                                .getAsBoolean());
                        merc.setDurability(Integer.parseInt(
                                ((JsonObject) entity)
                                        .get("durability")
                                        .getAsString()));
                    }
                    dungeon.addEntity(merc);
                    break;

                case "treasure":
                    dungeon.addEntity(new Treasure(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "key":
                    dungeon.addEntity(new Key(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("key").toString())));
                    break;
                case "invincibility_potion":
                    InvincibilityPotion VINpotion = (new InvincibilityPotion(dungeon.getCurrMaxEntityId(),
                            new Position(x, y),
                            loadedConfig.invincibility_potion_duration));
                    if (isSaved) {
                        VINpotion.setRemainingDuration(
                                Integer.parseInt(((JsonObject) entity).get("duration").getAsString()));
                    }
                    dungeon.addEntity(VINpotion);
                    break;
                case "invisibility_potion":
                    InvisibilityPotion VISpotion = (new InvisibilityPotion(dungeon.getCurrMaxEntityId(),
                            new Position(x, y),
                            loadedConfig.invisibility_potion_duration));
                    if (isSaved) {
                        VISpotion.setRemainingDuration(
                                Integer.parseInt(((JsonObject) entity).get("duration").getAsString()));
                    }
                    dungeon.addEntity(VISpotion);
                    break;
                case "wood":
                    dungeon.addEntity(new Wood(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "arrow":
                    dungeon.addEntity(new Arrow(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;
                case "bomb":
                    dungeon.addEntity(
                            new Bomb(dungeon.getCurrMaxEntityId(), new Position(x, y),
                                    loadedConfig.bomb_radius));
                    break;
                case "sword":
                    dungeon.addEntity(new Sword(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.sword_attack, loadedConfig.sword_durability));
                    break;
                case "bow":
                    dungeon.addEntity(new Bow(dungeon.getCurrMaxEntityId(), loadedConfig.bow_durability));
                    break;
                case "shield":
                    dungeon.addEntity(
                            new Shield(dungeon.getCurrMaxEntityId(), loadedConfig.shield_durability,
                                    loadedConfig.shield_defence));
                    break;

                case "swamp_tile":
                    SwampTile swampTile = new SwampTile(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("movement_factor").toString()));
                    dungeon.addEntity(swampTile);
                    dungeon.addSwampTile(swampTile);
                    break;

                case "sun_stone":
                    dungeon.addEntity(new Sunstone(dungeon.getCurrMaxEntityId(), new Position(x, y)));
                    break;

                case "midnight_armour":
                    dungeon.addEntity(
                            new MidnightArmour(dungeon.getCurrMaxEntityId(), loadedConfig.midnight_armour_attack,
                                    loadedConfig.midnight_armour_defence));
                    break;

                case "sceptre":
                    dungeon.addEntity(new Sceptre(dungeon.getCurrMaxEntityId()));
                    break;
                case "assassin":
                    dungeon.addEntity(new Assassin(dungeon.getCurrMaxEntityId(), new Position(x, y),
                            loadedConfig.ally_attack, loadedConfig.ally_defence, loadedConfig.assassin_attack,
                            loadedConfig.assassin_health,
                            loadedConfig.bribe_radius, loadedConfig.assassin_bribe_amount,
                            loadedConfig.assassin_bribe_fail_rate,
                            loadedConfig.assassin_recon_radius));
                    break;
                case "hydra":
                    dungeon.addEntity(
                            new Hydra(dungeon.getCurrMaxEntityId(), new Position(x, y), loadedConfig.hydra_attack,
                                    loadedConfig.hydra_health, loadedConfig.hydra_health_increase_rate,
                                    loadedConfig.hydra_health_increase_amount));
                    break;
            }
        }
        portalFactory(portals);
    }

    private static void portalFactory(Map<String, List<Portal>> portals) {
        for (List<Portal> portalPair : portals.values()) {
            portalPair.get(0).setPair(portalPair.get(1));
        }
    }

    private static Entity makeItem(JsonObject item, Dungeon dungeon, Player player) {
        LoadConfig loadedConfig = dungeon.getConfig();
        String type = item.get("type").getAsString();
        int x = 0;
        int y = 0;
        switch (type) {
            case "treasure":
                return (new Treasure(dungeon.getCurrMaxitemId(), new Position(x, y)));

            case "key":
                return (new Key(dungeon.getCurrMaxitemId(), new Position(x, y),
                        Integer.parseInt(((JsonObject) item).get("key").toString())));

            case "invincibility_potion":
                return (new InvincibilityPotion(dungeon.getCurrMaxitemId(), new Position(x, y),
                        loadedConfig.invincibility_potion_duration));

            case "invisibility_potion":
                return (new InvisibilityPotion(dungeon.getCurrMaxitemId(), new Position(x, y),
                        loadedConfig.invisibility_potion_duration));

            case "wood":
                return (new Wood(dungeon.getCurrMaxitemId(), new Position(x, y)));

            case "arrow":
                return (new Arrow(dungeon.getCurrMaxitemId(), new Position(x, y)));

            case "bomb":
                return (new Bomb(dungeon.getCurrMaxitemId(), new Position(x, y),
                        loadedConfig.bomb_radius));

            case "sword":
                Sword sword = (new Sword(dungeon.getCurrMaxitemId(), new Position(x, y),
                        loadedConfig.sword_attack, loadedConfig.sword_durability));
                sword.setDurability(
                        Integer.parseInt(
                                ((JsonObject) item).get("durability").toString()));
                return sword;

            case "bow":
                Bow bow = (new Bow(dungeon.getCurrMaxitemId(), loadedConfig.bow_durability));
                bow.setDurability(
                        Integer.parseInt(
                                ((JsonObject) item).get("durability").toString()));

            case "shield":
                Shield shield = (new Shield(dungeon.getCurrMaxitemId(), loadedConfig.shield_durability,
                        loadedConfig.shield_defence));
                shield.setDurability(
                        Integer.parseInt(
                                ((JsonObject) item).get("durability").toString()));
                return shield;

            case "sun_stone":
                return (new Sunstone(dungeon.getCurrMaxitemId(), new Position(0, 0)));

        }
        return null;
    }

}
