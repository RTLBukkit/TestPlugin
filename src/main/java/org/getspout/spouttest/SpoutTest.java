package org.getspout.spouttest;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.gui.Align;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlider;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutTest extends JavaPlugin {

	public HashMap<Player, Integer> movement = new HashMap<Player, Integer>();
	public HashMap<Player, GenericLabel> labels = new HashMap<Player, GenericLabel>();
	public Plugin plugin = this;
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, new pL(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new spL(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new sL(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new iL(), Priority.Normal, this);
	}
	
	public class pL extends PlayerListener {
		
		@Override
		public void onPlayerMove(PlayerMoveEvent event) {
			if (!movement.containsKey(event.getPlayer())) {
				movement.put(event.getPlayer(), 0);
			} else {
				movement.put(event.getPlayer(), movement.get(event.getPlayer()) + 1);
			}
			if (!labels.containsKey(event.getPlayer())) {
				GenericLabel label = (GenericLabel) ((GenericLabel) new GenericLabel().setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.THIRD).setX(240).setY(0);
				SpoutManager.getPlayer(event.getPlayer()).getMainScreen().attachWidget(plugin, label);
				labels.put(event.getPlayer(), label);
			}
			labels.get(event.getPlayer()).setText(movement.get(event.getPlayer()).toString()).setDirty(true);
		}
		
	}
	
	GenericPopup popup;
	
	public class spL extends SpoutListener {
		
		@Override
		public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
			event.getPlayer().sendMessage("Testing commences!");
			popup = new GenericPopup();
			popup.attachWidget(plugin, new GenericButton("Test").setAlignX(Align.FIRST).setX(70).setY(102).setHeight(35).setWidth(100));
			popup.attachWidget(plugin, new GenericTextField().setX(70).setY(142).setHeight(35).setWidth(100));
			popup.attachWidget(plugin, new GenericSlider().setX(70).setY(242).setHeight(35).setWidth(100));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.THIRD).setX(240).setY(0));
			
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.THIRD).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(plugin, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.THIRD).setX(0).setY(0).setHeight(427).setWidth(240));
			
			popup.attachWidget(plugin, new GenericLabel("Bottom right of middle").setHexColor(0xFFFFFF).setX(130).setY(230).setHeight(427).setWidth(240));
			
			event.getPlayer().setTexturePack("http://dl.dropbox.com/u/7186172/Foliacraft.zip");
			
			((SpoutPlayer) event.getPlayer()).getMainScreen().attachPopupScreen(popup);
		}
		
	}
	
	public class iL extends InputListener {
		
		@Override
		public void onKeyPressedEvent(KeyPressedEvent event) {
			if (event.getKey() == Keyboard.KEY_L) {
				SpoutManager.getAppearanceManager().setGlobalSkin(event.getPlayer(), "http://s3.amazonaws.com/MinecraftSkins/Top_Cat.png");
			} else if (event.getKey() == Keyboard.KEY_M) {
				((SpoutPlayer) event.getPlayer()).sendNotification(ChatColor.RED + "Party time is now! " + ChatColor.YELLOW + "Oh yeah", "Testing", Material.CAKE);
			} else if (event.getKey() == Keyboard.KEY_C) {
				((SpoutPlayer) event.getPlayer()).resetTexturePack();
			} else if (event.getKey() == Keyboard.KEY_A) {
				event.getPlayer().getMainScreen().attachPopupScreen(popup);
				//popup.setDirty(true);
			} else if (event.getKey() == Keyboard.KEY_S) {
				event.getPlayer().getMainScreen().closePopup();
			}
		}
		
	}

	public class sL extends ScreenListener {
		
		@Override
		public void onButtonClick(ButtonClickEvent event) {
			if (event.getButton() instanceof GenericButton && event.getButton().getText().equals("Test")) {
				((SpoutPlayer) event.getPlayer()).getMainScreen().closePopup();
				event.getScreen().setDirty(true);
				((SpoutPlayer) event.getPlayer()).getMainScreen().attachWidget(plugin, ((GenericLabel) new GenericLabel("I'm on the main screen!").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
				event.getPlayer().sendMessage("Button test successful!");
			}
		}
		
	}
	
}