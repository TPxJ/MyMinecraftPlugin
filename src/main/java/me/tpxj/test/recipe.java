package me.tpxj.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class recipe extends JavaPlugin {

    @Override
    public void onEnable(){
        ItemStack iceSword = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta iceSwordItemMeta = iceSword.getItemMeta();
        iceSwordItemMeta.setCustomModelData(1);
        iceSwordItemMeta.setItemName("&b&lIce &f&lSword");

        ShapedRecipe reci = new ShapedRecipe(new NamespacedKey(Test.getMyPlugin() , "IceSword") , iceSword);
        reci.shape("X" , "X" , "A");
        reci.setIngredient('X', Material.PACKED_ICE);
        reci.setIngredient('X', Material.STICK);

        Bukkit.addRecipe(reci);
        Bukkit.broadcastMessage(ChatColor.GREEN + "Recipe Added");
    }
}
