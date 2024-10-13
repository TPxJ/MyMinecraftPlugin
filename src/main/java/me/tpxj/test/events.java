package me.tpxj.test;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class events implements Listener {
    Double sFSCD = 200.00;
    Double sDashCD = 1000.00;
    Map<UUID,Long>FSCD = new HashMap<UUID,Long>();
    Map<UUID,Long>DashCD = new HashMap<UUID,Long>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        Bukkit.broadcastMessage("welcome" + e.getPlayer().toString() + "To ma server");
        FSCD.put(player.getUniqueId(), System.currentTimeMillis() + 3 * 1000);

    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        FireballStick(e);
        DashSkill(e);
        return;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager().hasMetadata("fb")){
            e.setDamage(10.00);
        }
    }

    private void FireballStick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getItem().getType() == Material.BLAZE_ROD)) {
            if (!FSCD.containsKey(player.getUniqueId())) {


                FSCD.put(player.getUniqueId(), System.currentTimeMillis());
                Fireball f = player.launchProjectile(Fireball.class);
                f.setIsIncendiary(false);
                f.setVelocity(player.getLocation().getDirection().multiply(2));
                f.setMetadata("fb", new FixedMetadataValue(Test.getMyPlugin(), true));
                return;

            } else {

                Long timeelapse = (System.currentTimeMillis() - FSCD.get(player.getUniqueId()));

                if (timeelapse >= sFSCD) {
                    FSCD.put(player.getUniqueId(), System.currentTimeMillis());
                    Fireball f = player.launchProjectile(Fireball.class);
                    f.setIsIncendiary(false);
                    f.setVelocity(player.getLocation().getDirection().multiply(2));
                    f.setMetadata("fb", new FixedMetadataValue(Test.getMyPlugin(), true));
                    return;
                } else {
                    Double delay = sFSCD - timeelapse;
                    return;
//                player.spigot().sendMessage(ChatMessageType.ACTION_BAR , TextComponent.fromLegacyText(delay.toString()));
                }

            }
        }
        return;
    }

    private void DashSkill(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getItem().getType() == Material.FEATHER)){
            if(!DashCD.containsKey(player.getUniqueId())){
                DashCD.put(player.getUniqueId(), System.currentTimeMillis());
                player.setVelocity(player.getLocation().getDirection().multiply(1));
                return;
            }
            else{

                Long timeelapse = (System.currentTimeMillis() - DashCD.get(player.getUniqueId()));

                if (timeelapse >= sDashCD){
                    DashCD.put(player.getUniqueId(), System.currentTimeMillis());
                    player.setVelocity(player.getLocation().getDirection().multiply(1));
                    return;
                }

                else {
                    Double delay = sDashCD-timeelapse;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR , TextComponent.fromLegacyText(delay.toString()));
                    return;
                }

            }
        }

        return;
    }

}
