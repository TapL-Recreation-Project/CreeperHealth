package me.swipez.creeperhealth;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ExplosionListener implements Listener {

    CreeperHealth plugin;

    public ExplosionListener(CreeperHealth plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperExplode(EntityDamageByEntityEvent e){
        if (plugin.gamestarted){
            if (e.getDamager().getType() == EntityType.CREEPER){
                if (e.getEntity().getType() == EntityType.PLAYER){
                    Player p = (Player) e.getEntity();
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*2);
                    long heartcount = (long) p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()/2;
                    p.sendMessage(ChatColor.WHITE+"["+ChatColor.LIGHT_PURPLE+"!"+ChatColor.WHITE+"]"+" "+ChatColor.GREEN+"You now have "+ChatColor.RED+heartcount+" ♥'s");
                }
            }
        }
    }
}
