package me.oskarsniper.core;

import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class cmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			if(args[0].equalsIgnoreCase("score"))
			{
				CraftPlayer p = (CraftPlayer) sender;
				p.sendMessage("#--------------------- GunGame ----------------------#");
				p.sendMessage("");
				for(Entry<Player, Integer> entry : core.Players.entrySet())
				{
					p.sendMessage("UUID -> " + entry.getKey().getUniqueId() + " # Level -> " + entry.getValue());
				}
					
			} else { System.out.println("Error"); }
		}
		return false;
	}
	
}
