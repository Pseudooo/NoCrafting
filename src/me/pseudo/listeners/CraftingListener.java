package me.pseudo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import me.pseudo.NoCrafting;

public class CraftingListener implements Listener {

	// Register event with server
	public CraftingListener(NoCrafting plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraftEvent(PrepareItemCraftEvent e) {
		
		// TODO Handle crafting and potentially block
		
	}
	
}
