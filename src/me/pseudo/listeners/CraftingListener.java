package me.pseudo.listeners;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import me.pseudo.NoCrafting;
import me.pseudo.files.Configuration;

public class CraftingListener implements Listener {
	
	private final Configuration config;

	// Register event with server
	public CraftingListener(NoCrafting plugin, Configuration config) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.config = config;
	}
	
	@EventHandler
	public void onCraftEvent(PrepareItemCraftEvent e) {
		
		// No-action for non-recipes
		if(e.getRecipe() == null)
			return;
		
		// If the user is an admin and the admin-block is disabled no-action
		HumanEntity he = e.getView().getPlayer();
		if(he.hasPermission("nocrafting.admin") && !config.getAdminBlock())
			return;
		
		// Check the outcome of the recipe is to be blocked
		boolean block = false; 
		Material cur = e.getRecipe().getResult().getType();
		
		for(Material m : config.getBlockedItems()) {
			
			if(m == cur) { 
				// Found material
				block = true;
				break;
			}
			
		}
		
		// If it's not to be blocked no-action
		if(!block) return;
		
		// Disable recipe by removing outcome
		e.getInventory().setResult(new ItemStack(Material.AIR));
		
		// Notify user if required
		if(!config.getNotifyUser())
			return;
		
		he.sendMessage(config.getNotifyMessage());
		
	}
	
}
