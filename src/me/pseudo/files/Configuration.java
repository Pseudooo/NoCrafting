package me.pseudo.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configuration {

	private final JavaPlugin plugin;
	private final File configFile;
	
	public Configuration(JavaPlugin plugin) {
		this.plugin = plugin;
		
		// Take the default cfg to the dataFolder
		plugin.saveResource("config.yml", false);
		
		configFile = new File(plugin.getDataFolder(), "config.yml");
		
		reload();
		
	}
	
	/**
	 * Reload the configuration
	 * @return boolean to determine if the reload was a success
	 */
	public boolean reload() {
		try {
			load();
			return true;
		}catch(FileNotFoundException e) {
			disablePlugin("Failed to locate configuration file!");
		}catch(IOException e) {
			disablePlugin("Failed to read from the configuration file!");
		}catch(InvalidConfigurationException e) {
			disablePlugin("Invalid Configuration!");
		}
		return false;
	}
	
	private void disablePlugin(String reason) {
		// Helper function to disable if loading exception occurs
		this.plugin.getLogger().severe(reason);
		this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
	}
	
	private void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		YamlConfiguration cfg = new YamlConfiguration();
		cfg.load(configFile);
		
		BLOCK_ADMINS = cfg.getBoolean("block-admins");
		
		NOTIFY_USER = cfg.getBoolean("notify-user");
						// colorize notifcation msg
		if(NOTIFY_USER) NOTIFY_MSG = ChatColor.translateAlternateColorCodes('&', 
							cfg.getString("notify-message"));
		
		// List will be item string ids, need to conv
		List<String> items = cfg.getStringList("blocked-items");
		BLOCKED_ITEMS = new ArrayList<Material>(items.size());
		
		// iter over str ids
		for(String itemID : items) {
			
			// Lookup failed -> invalid id -> m = null
			Material m = Material.matchMaterial(itemID);
			if(m == null) {
				// Log error - plugin disp not needed
				plugin.getLogger().severe("Invalid Item ID in config.yml!");
				plugin.getLogger().severe(" - " + itemID);
			}else {
				// Add matched item
				BLOCKED_ITEMS.add(m);
			}
			
		}
		
	}
	
	// * * * * * * * * * * * START OF GETTERS
	
	public boolean getAdminBlock() {
		return BLOCK_ADMINS;
	}
	
	public boolean getNotifyUser() {
		return NOTIFY_USER;
	}
	
	public String getNotifyMessage() {
		return NOTIFY_MSG;
	}
	
	public List<Material> getBlockedItems() {
		return this.BLOCKED_ITEMS;
	}
	
	// * * * * * * * * * * * END OF GETTERS
	
	// * * * * * * * * * * * * * * * * * * * * * * * CONFIG ELEMENTS
	private boolean BLOCK_ADMINS, NOTIFY_USER;
	
	private String NOTIFY_MSG = null;
	
	private List<Material> BLOCKED_ITEMS;
	// * * * * * * * * * * * * * * * * * * * * * * * END OF CONFIG ELEMENTS
	
}
