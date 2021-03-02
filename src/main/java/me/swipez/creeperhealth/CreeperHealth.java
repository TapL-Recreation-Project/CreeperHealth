package me.swipez.creeperhealth;

import me.swipez.creeperhealth.bstats.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class CreeperHealth extends JavaPlugin {

    public boolean gamestarted = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Metrics(this,10531);
        getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
        getCommand("creeperhealth").setTabCompleter(new CommandComplete());
        getCommand("creeperhealth").setExecutor(new StartCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
