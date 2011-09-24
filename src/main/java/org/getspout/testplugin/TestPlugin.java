package org.getspout.testplugin;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;



public class TestPlugin extends JavaPlugin {

	public void onDisable() {
		
		Bukkit.getLogger().log(Level.INFO, "[Spout Test Plugin] Disabled!");
	}

	public void onEnable() {
		
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new TestSpoutListener(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new TestInputListener(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new TestScreenListener(), Priority.Normal, this);
		
		Bukkit.getLogger().log(Level.INFO, "[Spout Test Plugin] Enabled!");
	}
	
}
