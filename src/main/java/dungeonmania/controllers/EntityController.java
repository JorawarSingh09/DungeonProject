// // package dungeonmania.controllers;

// // import java.util.ArrayList;
// // import java.util.HashMap;
// // import java.util.List;
// // import java.util.Map;

// // import com.google.gson.JsonArray;
// // import com.google.gson.JsonElement;
// // import com.google.gson.JsonObject;

// // import dungeonmania.entities.buildableentities.Bow;
// // import dungeonmania.entities.buildableentities.MidnightArmour;
// // import dungeonmania.entities.buildableentities.Sceptre;
// // import dungeonmania.entities.buildableentities.Shield;
// // import dungeonmania.entities.collectableentities.Arrow;
// // import dungeonmania.entities.collectableentities.Bomb;
// // import dungeonmania.entities.collectableentities.InvincibilityPotion;
// // import dungeonmania.entities.collectableentities.InvisibilityPotion;
// // import dungeonmania.entities.collectableentities.Key;
// // import dungeonmania.entities.collectableentities.Sunstone;
// // import dungeonmania.entities.collectableentities.Sword;
// // import dungeonmania.entities.collectableentities.Treasure;
// // import dungeonmania.entities.collectableentities.Wood;
// // import dungeonmania.entities.movingentities.Assassin;
// // import dungeonmania.entities.movingentities.Hydra;
// // import dungeonmania.entities.movingentities.Mercenary;
// // import dungeonmania.entities.movingentities.Player;
// // import dungeonmania.entities.movingentities.Spider;
// // import dungeonmania.entities.movingentities.ZombieToast;
// // import dungeonmania.entities.staticentities.Boulder;
// // import dungeonmania.entities.staticentities.Door;
// // import dungeonmania.entities.staticentities.Exit;
// // import dungeonmania.entities.staticentities.FloorSwitch;
// // import dungeonmania.entities.staticentities.Portal;
// // import dungeonmania.entities.staticentities.SwampTile;
// // import dungeonmania.entities.staticentities.Wall;
// // import dungeonmania.goals.BoulderGoal;
// // import dungeonmania.goals.CollectTreasureGoal;
// // import dungeonmania.goals.ComplexGoal;
// // import dungeonmania.goals.EnemiesGoal;
// // import dungeonmania.goals.ExitGoal;
// // import dungeonmania.goals.Goal;
// // import dungeonmania.goals.GoalCondition;
// // import dungeonmania.spawners.SpiderSpawn;
// // import dungeonmania.spawners.ZombieToastSpawner;
// // import dungeonmania.dungeon.GameFile;
// // import dungeonmania.dungeon.LoadConfig;
// // import dungeonmania.dungeon.Dungeon;
// // import dungeonmania.dungeon.DungeonFactory;
// // import dungeonmania.util.Position;

// // public class EntityController {
// //     LoadConfig config;

// //     public Dungeon startGame(JsonArray entities, JsonObject goals, JsonObject configs, int dungeonId,
// //             String dungeonName, String configName) {
// //         this.config = new LoadConfig(configs, configName);
// //         Dungeon dungeon = new Dungeon(dungeonName, dungeonId, configName);
// //         // addConfigs(configs); 
// //         dungeon.setGoals(prepareGoals(goals));
// //         // DungeonFactory.makeEntities(entities, dungeon, config); //
// //         return dungeon;
// //     }

// //     public Goal prepareGoals(JsonObject goals) {
// //         if (goals.has("subgoals")) {
// //             GoalCondition condition;
// //             if (goals.get("goal").getAsString().equals("OR")) {
// //                 condition = GoalCondition.OR;
// //             } else {
// //                 condition = GoalCondition.AND;
// //             }
// //             ComplexGoal newGoal = new ComplexGoal(condition);
// //             JsonArray subgoals = goals.get("subgoals").getAsJsonArray();
// //             for (JsonElement subgoal : subgoals) {
// //                 if (subgoal.isJsonObject() && ((JsonObject) subgoal).has("subgoals")) {
// //                     newGoal.addSubgoal(prepareGoals((JsonObject) subgoal));
// //                 } else {
// //                     String subgoalString = ((JsonObject) subgoal).get("goal").getAsString();
// //                     newGoal.addSubgoal(goalType(subgoalString));
// //                 }
// //             }
// //             return newGoal;
// //         } else {
// //             return goalType(goals.get("goal").getAsString());
// //         }
// //     }

// //     public Goal goalType(String goal) {
// //         switch (goal) {
// //             case "enemies":
// //                 return new EnemiesGoal(config.enemy_goal);
// //             case "boulders":
// //                 return new BoulderGoal();
// //             case "treasure":
// //                 return new CollectTreasureGoal(config.treasure_goal);
// //             case "exit":
// //                 return new ExitGoal();
// //         }
// //         return null;
// //     }

// <<<<<<< HEAD
// //     // private void addConfigs(JsonObject configs) {
// //     //     this.ally_attack = Integer.parseInt(configs.get("ally_attack").toString());
// //     //     this.ally_defence = Integer.parseInt(configs.get("ally_defence").toString());
// //     //     this.bribe_radius = Integer.parseInt(configs.get("bribe_radius").toString());
// //     //     this.bribe_amount = Integer.parseInt(configs.get("bribe_amount").toString());
// //     //     this.bomb_radius = Integer.parseInt(configs.get("bomb_radius").toString());
// //     //     this.bow_durability = Integer.parseInt(configs.get("bow_durability").toString());
// //     //     this.player_health = Integer.parseInt(configs.get("player_health").toString());
// //     //     this.player_attack = Integer.parseInt(configs.get("player_attack").toString());
// //     //     this.enemy_goal = Integer.parseInt(configs.get("enemy_goal").toString());
// //     //     this.invincibility_potion_duration = Integer.parseInt(configs.get("invincibility_potion_duration").toString());
// //     //     this.invisibility_potion_duration = Integer.parseInt(configs.get("invisibility_potion_duration").toString());
// //     //     this.mercenary_attack = Integer.parseInt(configs.get("mercenary_attack").toString());
// //     //     this.mercenary_health = Integer.parseInt(configs.get("mercenary_health").toString());
// //     //     this.spider_attack = Integer.parseInt(configs.get("spider_attack").toString());
// //     //     this.spider_health = Integer.parseInt(configs.get("spider_health").toString());
// //     //     this.spider_spawn_rate = Integer.parseInt(configs.get("spider_spawn_rate").toString());
// //     //     this.shield_durability = Integer.parseInt(configs.get("shield_durability").toString());
// //     //     this.shield_defence = Integer.parseInt(configs.get("shield_defence").toString());
// //     //     this.sword_attack = Integer.parseInt(configs.get("sword_attack").toString());
// //     //     this.sword_durability = Integer.parseInt(configs.get("sword_durability").toString());
// //     //     this.treasure_goal = Integer.parseInt(configs.get("treasure_goal").toString());
// //     //     this.zombie_attack = Integer.parseInt(configs.get("zombie_attack").toString());
// //     //     this.zombie_health = Integer.parseInt(configs.get("zombie_health").toString());
// //     //     this.zombie_spawn_rate = Integer.parseInt(configs.get("zombie_spawn_rate").toString());
// //     // }
// // }
// =======
//     public Goal goalType(String goal) {
//         switch (goal) {
//             case "enemies":
//                 return new EnemiesGoal(enemy_goal);
//             case "boulders":
//                 return new BoulderGoal();
//             case "treasure":
//                 return new CollectTreasureGoal(treasure_goal);
//             case "exit":
//                 return new ExitGoal();
//         }
//         return null;
//     }

//     private void addConfigs(JsonObject configs) {
//         this.ally_attack = Integer.parseInt(configs.get("ally_attack").toString());
//         this.ally_defence = Integer.parseInt(configs.get("ally_defence").toString());
//         this.bribe_radius = Integer.parseInt(configs.get("bribe_radius").toString());
//         this.bribe_amount = Integer.parseInt(configs.get("bribe_amount").toString());
//         this.bomb_radius = Integer.parseInt(configs.get("bomb_radius").toString());
//         this.bow_durability = Integer.parseInt(configs.get("bow_durability").toString());
//         this.player_health = Integer.parseInt(configs.get("player_health").toString());
//         this.player_attack = Integer.parseInt(configs.get("player_attack").toString());
//         this.enemy_goal = Integer.parseInt(configs.get("enemy_goal").toString());
//         this.invincibility_potion_duration = Integer.parseInt(configs.get("invincibility_potion_duration").toString());
//         this.invisibility_potion_duration = Integer.parseInt(configs.get("invisibility_potion_duration").toString());
//         this.mercenary_attack = Integer.parseInt(configs.get("mercenary_attack").toString());
//         this.mercenary_health = Integer.parseInt(configs.get("mercenary_health").toString());
//         this.spider_attack = Integer.parseInt(configs.get("spider_attack").toString());
//         this.spider_health = Integer.parseInt(configs.get("spider_health").toString());
//         this.spider_spawn_rate = Integer.parseInt(configs.get("spider_spawn_rate").toString());
//         this.shield_durability = Integer.parseInt(configs.get("shield_durability").toString());
//         this.shield_defence = Integer.parseInt(configs.get("shield_defence").toString());
//         this.sword_attack = Integer.parseInt(configs.get("sword_attack").toString());
//         this.sword_durability = Integer.parseInt(configs.get("sword_durability").toString());
//         this.treasure_goal = Integer.parseInt(configs.get("treasure_goal").toString());
//         this.zombie_attack = Integer.parseInt(configs.get("zombie_attack").toString());
//         this.zombie_health = Integer.parseInt(configs.get("zombie_health").toString());
//         this.zombie_spawn_rate = Integer.parseInt(configs.get("zombie_spawn_rate").toString());
//         // Milestone 3
//         this.assassin_attack = configGet(configs, "assassin_attack");
//         this.assassin_bribe_amount = configGet(configs, "assassin_bribe_amount");
//         if (configs.has("assassin_bribe_fail_rate")) {
//             this.assassin_bribe_fail_rate = Double.parseDouble(configs.get("assassin_bribe_fail_rate").toString());
//         } else {
//             this.assassin_bribe_fail_rate = 1;
//         }
//         this.assassin_health = configGet(configs, "assassin_health");
//         this.assassin_recon_radius = configGet(configs, "assassin_recon_radius");
//         this.hydra_attack = configGet(configs, "hydra_attack");
//         this.hydra_health = configGet(configs, "hydra_health");
//         if (configs.has("hydra_health_increase_rate")) {
//             this.hydra_health_increase_rate = Double.parseDouble(configs.get("hydra_health_increase_rate").toString());
//         } else {
//             this.hydra_health_increase_rate = 1;
//         }
//         this.hydra_health_increase_amount = configGet(configs, "hydra_health_increase_amount");
//         this.mind_control_duration = configGet(configs, "mind_control_duration");
//         this.midnight_armour_attack = configGet(configs, "midnight_armour_attack");
//         this.midnight_armour_defence = configGet(configs, "midnight_armour_defence");
//     }

//     private int configGet(JsonObject config, String prefix) {
//         if (config.has(prefix)) {
//             return Integer.parseInt(config.get(prefix).toString());
//         } else {
//             return 1;
//         }
//     }

//     private void makeEntities(JsonArray entities, Dungeon dungeon) {
//         Map<String, List<Portal>> portals = new HashMap<>();
//         // create portal, add it to hashmap
//         // send hashMap to portal factory at the end
//         for (JsonElement entity : entities) {
//             int x = Integer.parseInt(((JsonObject) entity).get("x").toString());
//             int y = Integer.parseInt(((JsonObject) entity).get("y").toString());
//             String type = ((JsonObject) entity).get("type").getAsString();
//             switch (type) {
//                 case "player":
//                     Player player = new Player(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false,
//                             this.player_attack, this.player_health,
//                             bow_durability, shield_durability, shield_defence, midnight_armour_attack,
//                             midnight_armour_defence, mind_control_duration);
//                     dungeon.addEntity(player);
//                     dungeon.setPlayer(player);
//                     dungeon.setSpiderSpawner(
//                             new SpiderSpawn(spider_spawn_rate, new Position(x, y), spider_attack, spider_health));
//                     break;
//                 case "wall":
//                     dungeon.addEntity(new Wall(dungeon.getCurrMaxEntityId(), new Position(x, y), false, true));
//                     break;
//                 case "exit":
//                     dungeon.addEntity(new Exit(dungeon.getCurrMaxEntityId(), new Position(x, y), false, true));
//                     break;
//                 case "boulder":
//                     dungeon.addEntity(new Boulder(dungeon.getCurrMaxEntityId(), new Position(x, y), false, true));
//                     break;
//                 case "switch":
//                     dungeon.addEntity(new FloorSwitch(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false));
//                     break;
//                 case "door":
//                     dungeon.addEntity(new Door(dungeon.getCurrMaxEntityId(), new Position(x, y),
//                             Integer.parseInt(((JsonObject) entity).get("key").toString())));
//                     break;
//                 case "portal":
//                     String colour = ((JsonObject) entity).get("colour").toString();
//                     Portal portal = new Portal(dungeon.getCurrMaxEntityId(), new Position(x, y));
//                     dungeon.addEntity(portal);
//                     if (portals.containsKey(colour)) {
//                         portals.get(colour).add(portal);
//                     } else {
//                         List<Portal> portalsPair = new ArrayList<>();
//                         portalsPair.add(portal);
//                         portals.put(colour, portalsPair);
//                     }
//                     break;
//                 case "zombie_toast_spawner":
//                     dungeon.addEntity(new ZombieToastSpawner(dungeon.getCurrMaxEntityId(), new Position(x, y), true,
//                             true, this.zombie_spawn_rate, zombie_attack, zombie_health));
//                     break;
//                 case "spider":
//                     dungeon.addEntity(new Spider(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false,
//                             this.spider_attack, this.spider_health));
//                     break;
//                 case "zombie_toast":
//                     dungeon.addEntity(new ZombieToast(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false,
//                             this.zombie_attack, this.zombie_health));
//                     break;
//                 case "mercenary":
//                     dungeon.addEntity(new Mercenary(dungeon.getCurrMaxEntityId(), new Position(x, y), true, false,
//                             this.ally_attack,
//                             this.ally_defence, this.mercenary_attack, this.mercenary_health, this.bribe_radius,
//                             this.bribe_amount));
//                     break;
//                 case "treasure":
//                     dungeon.addEntity(new Treasure(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false));
//                     break;
//                 case "key":
//                     dungeon.addEntity(new Key(dungeon.getCurrMaxEntityId(), new Position(x, y),
//                             Integer.parseInt(((JsonObject) entity).get("key").toString())));
//                     break;
//                 case "invincibility_potion":
//                     dungeon.addEntity(new InvincibilityPotion(dungeon.getCurrMaxEntityId(), new Position(x, y), false,
//                             false, this.invincibility_potion_duration));
//                     break;
//                 case "invisibility_potion":
//                     dungeon.addEntity(new InvisibilityPotion(dungeon.getCurrMaxEntityId(), new Position(x, y), false,
//                             false, this.invisibility_potion_duration));
//                     break;
//                 case "wood":
//                     dungeon.addEntity(new Wood(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false));
//                     break;
//                 case "arrow":
//                     dungeon.addEntity(new Arrow(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false));
//                     break;
//                 case "bomb":
//                     dungeon.addEntity(
//                             new Bomb(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false, this.bomb_radius));
//                     break;
//                 case "sword":
//                     dungeon.addEntity(new Sword(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false,
//                             this.sword_attack, this.sword_durability));
//                     break;
//                 case "bow":
//                     dungeon.addEntity(new Bow(dungeon.getCurrMaxEntityId(), this.bow_durability));
//                     break;
//                 case "shield":
//                     dungeon.addEntity(new Shield(dungeon.getCurrMaxEntityId(), false, false, this.shield_durability,
//                             this.shield_defence));
//                     break;
//                 case "sun_stone":
//                     dungeon.addEntity(new Sunstone(dungeon.getCurrMaxEntityId(), new Position(x, y), false, false));
//                     break;
//                 case "midnight_armour":
//                     dungeon.addEntity(new MidnightArmour(dungeon.getCurrMaxEntityId(), this.midnight_armour_attack,
//                             this.midnight_armour_defence));
//                     break;
//                 case "swamp_tile":
//                     SwampTile swampTile = new SwampTile(dungeon.getCurrMaxEntityId(), new Position(x, y),
//                             Integer.parseInt(((JsonObject) entity).get("movement_factor").toString()));
//                     dungeon.addEntity(swampTile);
//                     dungeon.addSwampTile(swampTile);
//                     break;
//                 case "sceptre":
//                     dungeon.addEntity(new Sceptre(dungeon.getCurrMaxEntityId()));
//                     break;
//                 case "assassin":
//                     dungeon.addEntity(new Assassin(dungeon.getCurrMaxEntityId(), new Position(x, y), true, false,
//                             this.ally_attack, this.ally_defence, this.assassin_attack, this.assassin_health,
//                             this.bribe_radius, this.assassin_bribe_amount, this.assassin_bribe_fail_rate, this.assassin_recon_radius));
//                     break;
//                 case "hydra":
//                     dungeon.addEntity(new Hydra(dungeon.getCurrMaxEntityId(), new Position(x, y), hydra_attack, hydra_health, hydra_health_increase_rate, hydra_health_increase_amount));
//                     break;
//             }
//         }
//         portalFactory(portals);
//     }

//     private void portalFactory(Map<String, List<Portal>> portals) {
//         for (List<Portal> portalPair : portals.values()) {
//             portalPair.get(0).setPair(portalPair.get(1));
//         }
//     }
// }
// >>>>>>> da10351d6256011cc4e94f4618a1703511aec7e8
