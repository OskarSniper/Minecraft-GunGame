package me.oskarsniper.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.oskarsniper.core.core;

public class myListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		e.setJoinMessage(e.getPlayer().getName() + " has joined \"GunGame\" and is level 0!");
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setHelmet(null);
		e.getPlayer().getInventory().setChestplate(null);
		e.getPlayer().getInventory().setLeggings(null);
		e.getPlayer().getInventory().setBoots(null);
		e.getPlayer().setHealth(20);
		e.getPlayer().setFoodLevel(20);
		core.changeGear(e.getPlayer(), 0);
		core.Players.put(e.getPlayer(), 0);
	}
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(e.getPlayer().getName() + " has left \"GunGame\" and was level " + core.Players.get(e.getPlayer()).intValue());
        core.Players.remove(e.getPlayer());
    }
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		e.setCancelled(true);
	}
	 
	@EventHandler
	public void onDamageEvent(EntityDamageEvent e){
		if(e.getEntity() instanceof Player) {
		   Player p = (Player) e.getEntity();
		   System.out.println(p.getName());
		}
	}
	
	@EventHandler
    public void onPlayerInteract1(PlayerInteractEvent e) {
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
        	core.repairItems(e.getPlayer());
        }
    }
	
	@EventHandler
	public void FoodLevelChange(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
    {
       
        Player p = (Player) e.getPlayer();
        int plvl = core.Players.get(p).intValue();
        p.sendMessage(ChatColor.RED + "Dein Level: " + plvl);
        core.changeGear(p, plvl);
    }
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
    	e.getDrops().clear();
        Player p = e.getEntity();
        Player k = e.getEntity().getKiller();
        
        p.getPlayer().getInventory().clear();
		p.getPlayer().getInventory().setHelmet(null);
		p.getPlayer().getInventory().setChestplate(null);
		p.getPlayer().getInventory().setLeggings(null);
		p.getPlayer().getInventory().setBoots(null);
        
        k.getPlayer().getInventory().clear();
		k.getPlayer().getInventory().setHelmet(null);
		k.getPlayer().getInventory().setChestplate(null);
		k.getPlayer().getInventory().setLeggings(null);
		k.getPlayer().getInventory().setBoots(null);
        
        /* Downrank Player */
        int plvl = core.Players.get(p).intValue();
        int klvl = core.Players.get(k).intValue();
        Bukkit.getServer().broadcastMessage(klvl + " -> " + plvl);
        
        if(plvl == 0) { 
        	core.Players.replace(p, 0);
        	p.sendMessage("Du kannst nicht mehr herabgestuft werden!");
        } else if((plvl == 2) || (plvl == 3) || (plvl == 1) || (plvl == 4)) {
            core.Players.replace(p, plvl-1);
            p.sendMessage("Du wurdest herabgestuft");
        } else if(plvl == 5)
        {
        	core.Players.replace(p, plvl-1);
        	p.sendMessage("Du wurdest herabgestuft");
        }
        
        /* Uprank Killer*/
        if(klvl == 0) { 
        	core.Players.replace(k, 1);
        	core.changeGear(k, 1);
        	k.sendMessage("Du wurdest aufgestuft!");
        } else if((klvl == 2) || (klvl == 3) || (klvl == 1) || (klvl == 4)) {
            core.Players.replace(k, klvl+1);
            core.changeGear(k, klvl+1);
            k.sendMessage("Du wurdest aufgestuft");
        } else if(klvl == 5)
        {
        	core.Players.replace(k, klvl);
        	core.changeGear(k, klvl);
        	k.sendMessage("Du kannst nicht mehr aufgestuft werden");
        }
        
        
        e.setDeathMessage(e.getEntity().getName() + " wurde von " + e.getEntity().getKiller().getName() + " getötet!");
    }
}
