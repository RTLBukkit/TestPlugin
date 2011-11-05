package org.getspout.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;

public class TestKeyBinding implements BindingExecutionDelegate {
	
	
	public void keyPressed(KeyBindingEvent event) {
		if (event.getBinding().getId() == "TopCatKey") {
			SpoutManager.getAppearanceManager().setGlobalSkin(event.getPlayer(), "http://s3.amazonaws.com/MinecraftSkins/Top_Cat.png");
		} else if (event.getBinding().getId() == "PartyTimeKey") {
			SpoutManager.getPlayer(event.getPlayer()).sendNotification(ChatColor.RED + "Party time is now! " + ChatColor.YELLOW + "Oh yeah", "Testing", Material.CAKE);
		}
	}

	public void keyReleased(KeyBindingEvent arg0) {		
	}
	
}