package me.pseudo;

import org.bukkit.plugin.java.JavaPlugin;

import me.pseudo.listeners.CraftingListener;

public class NoCrafting extends JavaPlugin {

	public void onEnable() {
		
		// Register listeners
		new CraftingListener(this);
		
	}
	
	public void onDisable() {
		
	}
	
}
