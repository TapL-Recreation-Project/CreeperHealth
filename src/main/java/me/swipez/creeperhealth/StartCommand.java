package me.swipez.creeperhealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    CreeperHealth plugin;

    public StartCommand(CreeperHealth plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("creeperhealth.toggle")){
                String invalidUsageMsg = ChatColor.RED + "invalid usage! Use /creeperhealth <start/stop>";
                if (args.length == 0) {
                    sender.sendMessage(invalidUsageMsg);
                    return true;
                }
                if (args.length >= 2) {
                    sender.sendMessage(invalidUsageMsg);
                    return true;
                }
                switch (args[0]) {
                    case "start":
                        creeperStart(sender);
                        return true;
                    case "stop":
                        creeperStop(sender);
                        return true;
                    default:
                        sender.sendMessage(invalidUsageMsg);
                }
            }
            else {
                sender.sendMessage(ChatColor.RED+"You do not have the permission to run this command!");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"Only players may run this commmand!");
        }
        return true;
    }



    public void creeperStart(CommandSender sender) {

        if (!plugin.gamestarted) {
            plugin.gamestarted = true;

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2d);
            }
            Bukkit.broadcastMessage(ChatColor.GREEN+"The challenge has started!");
        }
        else {
            sender.sendMessage(ChatColor.GREEN+"The challenge has already started!");
        }
    }

    public void creeperStop(CommandSender sender) {

        if (plugin.gamestarted) {
            plugin.gamestarted = false;

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20d);
                player.setHealth(20);
            }
            Bukkit.broadcastMessage(ChatColor.RED+"The challenge has ended!");
        }
        else {
            sender.sendMessage(ChatColor.RED+"The challenge has not started yet!");
        }
    }
}