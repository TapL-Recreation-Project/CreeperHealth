package me.swipez.creeperhealth;

import org.bukkit.plugin.java.JavaPlugin;

public final class CreeperHealth extends JavaPlugin {

    public boolean gamestarted = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
        getCommand("creeperhealth").setTabCompleter(new CommandComplete());
        getCommand("creeperhealth").setExecutor(new StartCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
