package me.oskarsniper.core;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.oskarsniper.core.cmd;
import me.oskarsniper.listener.myListener;

public class core extends JavaPlugin {
	
	public static HashMap<Player, Integer> Players = new HashMap<Player, Integer>();
	
	@Override
	public void onEnable()
	{
		System.out.println("[GunGame] loaded.");
		this.getCommand("gungame").setExecutor(new cmd());
		Bukkit.getServer().getPluginManager().registerEvents(new myListener(), this);
	}
	
    public static void repairItems(Player p){
        ItemStack is = p.getItemInHand();
       
        ItemStack newis = new ItemStack(is.getType(), is.getAmount());
        newis.addEnchantments(is.getEnchantments());
        ItemMeta meta = is.getItemMeta();
        if (is.hasItemMeta()){
            if (is.getItemMeta().hasDisplayName()) meta.setDisplayName(is.getItemMeta().getDisplayName());
            if (is.getItemMeta().hasLore())  meta.setLore(is.getItemMeta().getLore());
        }
        newis.setItemMeta(meta);
        p.setItemInHand(is);
    }
    
    public static void changeGear(Player p, int id)
    {
    	switch(id)
    	{
			case 0:
				p.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
			break;
    		case 1:
    			p.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
    			p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
    			p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    			p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
    			p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
    		break;
    		case 2:
    			p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
    			p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
    			p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    			p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
    			p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    		break;
    		case 3:
    			p.getInventory().setItem(0, new ItemStack(Material.GOLD_SWORD));
    			p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
    			p.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
    			p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
    			p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
    		break;
    		case 4:
    			p.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
    			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
    			p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
    			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
    			p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
    		break;
    		case 5:
    			ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
    			ItemMeta swordMeta = sword.getItemMeta();
    			//swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 9999, true);
    			swordMeta.addEnchant(Enchantment.KNOCKBACK, 9999, true);
    			//swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 9999, true);
    			sword.setItemMeta(swordMeta);
    			p.getInventory().setItem(0, sword);
    			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
    			p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
    			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
    			p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
    		break;
    	}
    }
}
