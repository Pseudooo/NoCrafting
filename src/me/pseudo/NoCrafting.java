package me.pseudo;

import org.bukkit.plugin.java.JavaPlugin;

import me.pseudo.commands.ReloadCommand;
import me.pseudo.files.Configuration;
import me.pseudo.listeners.CraftingListener;

public class NoCrafting extends JavaPlugin {
	
	public void onEnable() {
	
		final Configuration config = new Configuration(this);
		
		// Register listeners
		new CraftingListener(this, config);
		
		this.getCommand("nocraftingreload").setExecutor(new ReloadCommand(config));
		
	}
	
}
