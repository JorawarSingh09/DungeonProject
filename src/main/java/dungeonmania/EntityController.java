package dungeonmania;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dungeonmania.entities.Entity;
import dungeonmania.entities.buildableentities.Bow;
import dungeonmania.entities.buildableentities.Shield;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Bomb;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Sword;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.Wood;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.Spider;
import dungeonmania.entities.movingentities.ZombieToast;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.entities.staticentities.Door;
import dungeonmania.entities.staticentities.Exit;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.entities.staticentities.Wall;
import dungeonmania.entities.staticentities.ZombieToastSpawner;

public class EntityController {

    private int ally_attack;
    private int ally_defence;
    private int bribe_radius;
    private int bribe_amount;
    private int bomb_radius;
    private int bow_durability;
    private int player_health;
    private int player_attack;
    private int enemy_goal;
    private int invincibility_potion_duration;
    private int invisibility_potion_duration;
    private int mercenary_attack;
    private int mercenary_health;
    private int spider_attack;
    private int spider_health;
    private int spider_spawn_rate;
    private int shield_durability;
    private int shield_defence;
    private int sword_attack;
    private int sword_durability;
    private int treasure_goal;
    private int zombie_attack;
    private int zombie_health;
    private int zombie_spawn_rate;

    public void startGame(JsonArray entities, JsonObject goals, JsonObject configs) {
        Dungeon dungeon = new Dungeon();
        prepareGoals(goals);
        addConfigs(configs);
        makeEntities(entities, dungeon);
    }

    public void prepareGoals(JsonObject goals) {

    }

    public void addConfigs(JsonObject configs) {
        this.ally_attack = Integer.parseInt(configs.get("ally_attack").toString());
        this.ally_defence = Integer.parseInt(configs.get("ally_defence").toString());
        this.bribe_radius = Integer.parseInt(configs.get("bribe_radius").toString());
        this.bribe_amount = Integer.parseInt(configs.get("bribe_amount").toString());
        this.bomb_radius = Integer.parseInt(configs.get("bomb_radius").toString());
        this.bow_durability = Integer.parseInt(configs.get("bow_durability").toString());
        this.player_health = Integer.parseInt(configs.get("player_health").toString());
        this.player_attack = Integer.parseInt(configs.get("player_attack").toString());
        this.enemy_goal = Integer.parseInt(configs.get("enemy_goal").toString());
        this.invincibility_potion_duration = Integer.parseInt(configs.get("invincibility_potion_duration").toString());
        this.invisibility_potion_duration = Integer.parseInt(configs.get("invisibility_potion_duration").toString());
        this.mercenary_attack = Integer.parseInt(configs.get("mercenary_attack").toString());
        this.mercenary_health = Integer.parseInt(configs.get("mercenary_health").toString());
        this.spider_attack = Integer.parseInt(configs.get("spider_attack").toString());
        this.spider_health = Integer.parseInt(configs.get("spider_health").toString());
        this.spider_spawn_rate = Integer.parseInt(configs.get("spider_spawn_rate").toString());
        this.shield_durability = Integer.parseInt(configs.get("shield_durability").toString());
        this.shield_defence = Integer.parseInt(configs.get("shield_defence").toString());
        this.sword_attack = Integer.parseInt(configs.get("sword_attack").toString());
        this.sword_durability = Integer.parseInt(configs.get("sword_durability").toString());
        this.treasure_goal = Integer.parseInt(configs.get("treasure_goal").toString());
        this.zombie_attack = Integer.parseInt(configs.get("zombie_attack").toString());
        this.zombie_health = Integer.parseInt(configs.get("zombie_health").toString());
        this.zombie_spawn_rate = Integer.parseInt(configs.get("zombie_spawn_rate").toString());
    }

    public void makeEntities(JsonArray entities, Dungeon dungeon) {
        for (JsonElement entity : entities) {
            int x = Integer.parseInt(((JsonObject) entity).get("x").toString());
            int y = Integer.parseInt(((JsonObject) entity).get("y").toString());
            String type = ((JsonObject) entity).get("type").getAsString();
            switch (type) {
                case "player":
                    dungeon.addEntity(new Player(dungeon.getCurrMaxEntityId(), x, y, false, false, this.player_attack, this.player_health));
                    break;
                case "wall":
                    dungeon.addEntity(new Wall(dungeon.getCurrMaxEntityId(), x, y, false, true));
                    break;
                case "exit":
                    dungeon.addEntity(new Exit(dungeon.getCurrMaxEntityId(), x, y, false, true));
                    break;
                case "boulder":
                    dungeon.addEntity(new Boulder(dungeon.getCurrMaxEntityId(), x, y, false, true));    
                    break;
                case "switch":
                    dungeon.addEntity(new FloorSwitch(dungeon.getCurrMaxEntityId(), x, y, false, true));
                    break;
                case "door":
                    dungeon.addEntity(new Door(dungeon.getCurrMaxEntityId(), x, y, false, true));
                    break;
                case "portal":
                    dungeon.addEntity(new Portal(dungeon.getCurrMaxEntityId(), x, y, false, true));
                    break;
                case "zombie_toast_spawner":
                    dungeon.addEntity(new ZombieToastSpawner(dungeon.getCurrMaxEntityId(), x, y, true, true, this.zombie_spawn_rate));
                    break;
                case "spider":
                    dungeon.addEntity(new Spider(dungeon.getCurrMaxEntityId(), x, y, false, false, this.spider_attack, this.spider_health));
                    break;
                case "zombie_toast":
                    dungeon.addEntity(new ZombieToast(dungeon.getCurrMaxEntityId(), x, y, false, false, this.zombie_attack, this.zombie_health));
                    break;
                case "mercenary":
                    dungeon.addEntity(new Mercenary(dungeon.getCurrMaxEntityId(), x, y, false, false, this.ally_attack,
                                                    this.ally_defence, this.mercenary_attack, this.mercenary_health, this.bribe_radius, this.bribe_amount));
                    break;
                case "treasure":
                    dungeon.addEntity(new Treasure(dungeon.getCurrMaxEntityId(), x, y, false, false));
                    break;
                case "key":
                    dungeon.addEntity(new Key(dungeon.getCurrMaxEntityId(), x, y, false, false));
                    break;
                case "invincibility_potion":
                    dungeon.addEntity(new InvincibilityPotion(dungeon.getCurrMaxEntityId(), x, y, false, false, this.invincibility_potion_duration));
                    break;
                case "invisibility_potion":
                    dungeon.addEntity(new InvisibilityPotion(dungeon.getCurrMaxEntityId(), x, y, false, false, this.invisibility_potion_duration));
                    break;
                case "wood":
                    dungeon.addEntity(new Wood(dungeon.getCurrMaxEntityId(), x, y, false, false));
                    break;
                case "arrow":
                    dungeon.addEntity(new Arrow(dungeon.getCurrMaxEntityId(), x, y, false, false));
                    break;
                case "bomb":
                    dungeon.addEntity(new Bomb(dungeon.getCurrMaxEntityId(), x, y, false, false, this.bomb_radius));
                    break;
                case "sword":
                    dungeon.addEntity(new Sword(dungeon.getCurrMaxEntityId(), x, y, false, false, this.sword_attack, this.sword_durability));
                    break;
                case "bow":
                    dungeon.addEntity(new Bow(dungeon.getCurrMaxEntityId(), x, y, false, false, this.bow_durability));
                    break;
                case "shield":
                    dungeon.addEntity(new Shield(dungeon.getCurrMaxEntityId(), x, y, false, false, this.shield_durability, this.shield_defence));
                    break;


            } 
        }
    }
}