package me.tpxj.test;

import com.google.common.collect.Multimap;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.jar.Attributes;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {

    Bukkit.getPluginManager().registerEvents(new events() , this);

        ItemStack iceSword = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta iceSwordItemMeta = iceSword.getItemMeta();
        iceSwordItemMeta.setCustomModelData(1);
        iceSwordItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "ICE " + ChatColor.DARK_AQUA +"SWORD");
        List<String>Lore = new ArrayList<>();
        Lore.add(ChatColor.GRAY + "this will slowness and make enemy cold asf");
        iceSwordItemMeta.setLore(Lore);
        iceSwordItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        AttributeModifier attmo = new AttributeModifier("Damage" , 19 , AttributeModifier.Operation.ADD_NUMBER);
        iceSwordItemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE , attmo);

        iceSword.setItemMeta(iceSwordItemMeta);
        iceSword.addUnsafeEnchantment(Enchantment.UNBREAKING , 100);

        ShapedRecipe reci = new ShapedRecipe(new NamespacedKey(Test.getMyPlugin() , "IceSword") , iceSword);
        reci.shape("X" , "X" , "A");
        reci.setIngredient('X', Material.PACKED_ICE);
        reci.setIngredient('A', Material.STICK);

        Bukkit.addRecipe(reci);
        Bukkit.broadcastMessage(ChatColor.GREEN + "Recipe Added");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Test getMyPlugin(){
        return getPlugin(Test.class);
    }

}
