package dungeonmania.dungeon;

import com.google.gson.JsonObject;

public class LoadConfig {

    public int ally_attack;
    public int ally_defence;
    public int assassin_attack;
    public int assassin_bribe_amount;
    public double assassin_bribe_fail_rate;
    public int assassin_health;
    public int assassin_recon_radius;
    public int bomb_radius;
    public int bow_durability;
    public int bribe_amount;
    public int bribe_radius;
    public int enemy_goal;
    public int hydra_attack;
    public int hydra_health;
    public int hydra_health_increase_amount;
    public double hydra_health_increase_rate;
    public int hydra_spawn_rate;
    public int invincibility_potion_duration;
    public int invisibility_potion_duration;
    public int mercenary_attack;
    public int mercenary_health;
    public int midnight_armour_attack;
    public int midnight_armour_defence;
    public int mind_control_duration;
    public int player_attack;
    public int player_health;
    public int shield_defence;
    public int shield_durability;
    public int spider_attack;
    public int spider_health;
    public int spider_spawn_rate;
    public int sword_attack;
    public int sword_durability;
    public int treasure_goal;
    public int zombie_attack;
    public int zombie_health;
    public int zombie_spawn_rate;

    private String configName;

    public LoadConfig(JsonObject configs, String configName) {
        this.configName = configName;
        // Old config files
        this.ally_attack = (int) Double.parseDouble(configs.get("ally_attack").toString());
        this.ally_defence = (int) Double.parseDouble(configs.get("ally_defence").toString());
        this.bribe_radius = (int) Double.parseDouble(configs.get("bribe_radius").toString());
        this.bribe_amount = (int) Double.parseDouble(configs.get("bribe_amount").toString());
        this.bow_durability = (int) Double.parseDouble(configs.get("bow_durability").toString());
        this.player_health = (int) Double.parseDouble(configs.get("player_health").toString());
        this.player_attack = (int) Double.parseDouble(configs.get("player_attack").toString());
        this.enemy_goal = (int) Double.parseDouble(configs.get("enemy_goal").toString());
        this.invincibility_potion_duration = (int) Double
                .parseDouble(configs.get("invincibility_potion_duration").toString());
        this.invisibility_potion_duration = (int) Double
                .parseDouble(configs.get("invisibility_potion_duration").toString());
        this.mercenary_attack = (int) Double.parseDouble(configs.get("mercenary_attack").toString());
        this.mercenary_health = (int) Double.parseDouble(configs.get("mercenary_health").toString());
        this.spider_attack = (int) Double.parseDouble(configs.get("spider_attack").toString());
        this.spider_health = (int) Double.parseDouble(configs.get("spider_health").toString());
        this.spider_spawn_rate = (int) Double.parseDouble(configs.get("spider_spawn_rate").toString());
        this.shield_durability = (int) Double.parseDouble(configs.get("shield_durability").toString());
        this.shield_defence = (int) Double.parseDouble(configs.get("shield_defence").toString());
        this.sword_attack = (int) Double.parseDouble(configs.get("sword_attack").toString());
        this.sword_durability = (int) Double.parseDouble(configs.get("sword_durability").toString());
        this.treasure_goal = (int) Double.parseDouble(configs.get("treasure_goal").toString());
        this.zombie_attack = (int) Double.parseDouble(configs.get("zombie_attack").toString());
        this.zombie_health = (int) Double.parseDouble(configs.get("zombie_health").toString());
        this.zombie_spawn_rate = (int) Double.parseDouble(configs.get("zombie_spawn_rate").toString());
        this.bomb_radius = (int) Double.parseDouble(configs.get("bomb_radius").toString());

        try {
            this.assassin_attack = (int) Double.parseDouble(configs.get("assassin_attack").toString());
            this.assassin_bribe_amount = (int) Double.parseDouble(configs.get("assassin_bribe_amount").toString());
            this.assassin_bribe_fail_rate = Double.parseDouble(configs.get("assassin_bribe_fail_rate").toString());
            this.assassin_health = (int) Double.parseDouble(configs.get("assassin_health").toString());
            this.assassin_recon_radius = (int) Double.parseDouble(configs.get("assassin_recon_radius").toString());

            this.hydra_attack = (int) Double.parseDouble(configs.get("hydra_attack").toString());
            this.hydra_health = (int) Double.parseDouble(configs.get("hydra_health").toString());
            this.hydra_health_increase_amount = (int) Double
                    .parseDouble(configs.get("hydra_health_increase_amount").toString());
            this.hydra_health_increase_rate = Double.parseDouble(configs.get("hydra_health_increase_rate").toString());
            this.midnight_armour_attack = (int) Double.parseDouble(configs.get("midnight_armour_attack").toString());
            this.midnight_armour_defence = (int) Double.parseDouble(configs.get("midnight_armour_defence").toString());
            this.mind_control_duration = (int) Double.parseDouble(configs.get("mind_control_duration").toString());

        } catch (NullPointerException e) {
            this.assassin_attack = 1;
            this.assassin_bribe_amount = 1;
            this.assassin_bribe_fail_rate = 1;
            this.assassin_health = 1;
            this.assassin_recon_radius = 1;
            this.hydra_attack = 1;
            this.hydra_health = 1;
            this.hydra_health_increase_amount = 1;
            this.hydra_health_increase_rate = 1;
            this.hydra_spawn_rate = 1;
            this.midnight_armour_attack = 1;
            this.midnight_armour_defence = 1;
            this.mind_control_duration = 1;
        }

    }

}
