package me.pseudo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.sun.istack.internal.NotNull;

import me.pseudo.files.Configuration;
import net.md_5.bungee.api.ChatColor;

public class ReloadCommand implements CommandExecutor {
	
	private final Configuration config;
	
	public ReloadCommand(Configuration config) {
		this.config = config;
	}

	@Override
	public boolean onCommand(
			@NotNull CommandSender sender, 
			@NotNull Command cmd,
			@NotNull String label, 
			@NotNull String[] args) {
		
		// Start reloading
		sender.sendMessage(ChatColor.GREEN + "Reloading Config...");
		if(config.reload()) {
			// Success
			sender.sendMessage(ChatColor.GREEN+"Config Reloaded!");
		}else {
			// Errror
			sender.sendMessage(ChatColor.RED+"An error occurred...");
			sender.sendMessage(ChatColor.RED+"Plugin will be disabled!");
		}
		
		return true;
	}


	
}
