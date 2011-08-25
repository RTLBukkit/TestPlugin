package org.getspout.spouttest;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutWeather;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericItemWidget;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlider;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.keyboard.Keyboard;

public class SpoutTest extends JavaPlugin {

	public HashMap<Player, Integer> movement = new HashMap<Player, Integer>();
	public HashMap<Player, GenericLabel> labels = new HashMap<Player, GenericLabel>();
	public HashMap<Player, GenericTexture> texts = new HashMap<Player, GenericTexture>();
	public Plugin plugin = this;
	public Container aContainer = new GenericContainer();
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		pL pl = new pL();
		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, pl, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, pl, Priority.Normal, this);
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
				GenericLabel label = (GenericLabel) ((GenericLabel) new GenericLabel().setTextColor(new Color(1, 1, 1))).setX(240).setY(0);
				SpoutManager.getPlayer(event.getPlayer()).getMainScreen().attachWidget(plugin, label);
				labels.put(event.getPlayer(), label);
				aContainer.addChild(label);
			}
			labels.get(event.getPlayer()).setText(movement.get(event.getPlayer()).toString());
			if (movement.get(event.getPlayer()) % 10 == 0) {
				aContainer.setX(movement.get(event.getPlayer())).setDirty(true);
			}
		}
		
		@Override
		public void onPlayerChat(PlayerChatEvent event) {
			SpoutManager.getItemManager().setItemName(Material.STONE, event.getMessage());
		}
		
	}
	
	GenericPopup popup;
	
	public class spL extends SpoutListener {
		
		@Override
		public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
			SpoutManager.getBiomeManager().setPlayerBiomeWeather(event.getPlayer(), Biome.DESERT, SpoutWeather.SNOW); // Desert snow ftw!
			
			event.getPlayer().sendMessage("Testing commences!");
			popup = new GenericPopup();
			
			Random r = new Random();
			for (int i = 0; i < 15; i++) {
				int x = -150 + i * 20;
				int y = -40;
				popup.attachWidget(plugin, new GenericGradient().setTopColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1)).setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x).setY(y).setPriority(RenderPriority.Low));
				popup.attachWidget(plugin, new GenericLabel(i+"").setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x + 5).setY(y + 5).setPriority(RenderPriority.Lowest));
			}
			
			popup.attachWidget(plugin, new GenericButton("Test").setY(-75).setX(-70).setHeight(20).setWidth(150).setAnchor(WidgetAnchor.CENTER_CENTER));
			popup.attachWidget(plugin, new GenericTextField().setMaximumCharacters(50).setX(-50).setY(-50).setHeight(20).setWidth(50).setAnchor(WidgetAnchor.CENTER_CENTER));
			popup.attachWidget(plugin, new GenericSlider().setX(-100).setY(20).setHeight(20).setWidth(200).setAnchor(WidgetAnchor.CENTER_CENTER));
			
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.TOP_LEFT).setAnchor(WidgetAnchor.TOP_LEFT)).setX(50).setY(50);
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.TOP_CENTER));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.TOP_RIGHT).setAnchor(WidgetAnchor.TOP_RIGHT));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.CENTER_LEFT).setAnchor(WidgetAnchor.CENTER_LEFT).setX(0).setY(0).setHeight(240).setWidth(427));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.CENTER_CENTER).setAnchor(WidgetAnchor.CENTER_CENTER).setX(0).setY(0).setHeight(240).setWidth(427));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.CENTER_RIGHT).setAnchor(WidgetAnchor.CENTER_RIGHT).setX(0).setY(0).setHeight(240).setWidth(427));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.BOTTOM_LEFT).setAnchor(WidgetAnchor.BOTTOM_LEFT).setX(0).setY(0));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.BOTTOM_CENTER).setAnchor(WidgetAnchor.BOTTOM_CENTER).setX(0).setY(0));
			popup.attachWidget(plugin, new GenericLabel("Some\nLonger Text\nis").setTextColor(new Color(1, 1, 1)).setAlign(WidgetAnchor.BOTTOM_RIGHT).setAnchor(WidgetAnchor.BOTTOM_RIGHT).setX(0).setY(0));
			
			popup.attachWidget(plugin, new GenericItemWidget(new ItemStack(Material.STONE)).setWidth(8).setHeight(8).setDepth(8).setX(50).setY(50).setTooltip("I'm a tool"));
			popup.attachWidget(plugin, new GenericLabel("Bottom right of middle").setTextColor(new Color(1, 1, 1)).setX(230).setY(130));
			
			event.getPlayer().setTexturePack("http://dl.dropbox.com/u/7186172/Foliacraft.zip");
			
			SpoutManager.getPlayer(event.getPlayer()).getMainScreen().attachPopupScreen(popup);
		}
		
	}
	
	public class iL extends InputListener {
		
		@Override
		public void onKeyPressedEvent(KeyPressedEvent event) {
			if (event.getKey() == Keyboard.KEY_L) {
				SpoutManager.getAppearanceManager().setGlobalSkin(event.getPlayer(), "http://s3.amazonaws.com/MinecraftSkins/Top_Cat.png");
			} else if (event.getKey() == Keyboard.KEY_M) {
				SpoutManager.getPlayer(event.getPlayer()).sendNotification(ChatColor.RED + "Party time is now! " + ChatColor.YELLOW + "Oh yeah", "Testing", Material.CAKE);
			} else if (event.getKey() == Keyboard.KEY_C) {
				SpoutManager.getPlayer(event.getPlayer()).resetTexturePack();
			} else if (event.getKey() == Keyboard.KEY_B) {

				popup = new GenericPopup();
				
				Random r = new Random();
				for (int i = 0; i < 15; i++) {
					int x = -150 + i * 20;
					int y = -40;
					popup.attachWidget(plugin, new GenericGradient().setTopColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1)).setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x).setY(y).setPriority(RenderPriority.Low));
					popup.attachWidget(plugin, new GenericLabel(i+"").setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x + 5).setY(y + 5).setPriority(RenderPriority.Lowest));
				}

				event.getPlayer().getMainScreen().attachPopupScreen(popup);
			} else if (event.getKey() == Keyboard.KEY_N) {
				event.getPlayer().getMainScreen().closePopup();
			}
		}
		
	}

	public class sL extends ScreenListener {
		
		@Override
		public void onButtonClick(ButtonClickEvent event) {
			if (event.getButton() instanceof GenericButton && event.getButton().getText().equals("Test")) {
				SpoutManager.getPlayer(event.getPlayer()).getMainScreen().closePopup();
				
				popup = new GenericPopup();
				popup.attachWidget(plugin, new GenericButton("Test").setY(-75).setX(-70).setHeight(20).setWidth(150).setAnchor(WidgetAnchor.CENTER_CENTER).setTooltip("Hiya"));
				
				Random r = new Random();
				for (int i = 0; i < 15; i++) {
					int x = -150 + i * 20;
					int y = -40;
					popup.attachWidget(plugin, new GenericGradient().setTopColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1)).setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x).setY(y).setPriority(RenderPriority.Low));
					popup.attachWidget(plugin, new GenericLabel(i+"").setAnchor(WidgetAnchor.BOTTOM_CENTER).setWidth(20).setHeight(20).setX(x + 5).setY(y + 5).setPriority(RenderPriority.Lowest));
				}
				
				event.getPlayer().getMainScreen().attachPopupScreen(popup);
				popup.setDirty(true);
				SpoutManager.getPlayer(event.getPlayer()).getMainScreen().attachWidget(plugin, ((GenericLabel) new GenericLabel("I'm on the main screen!").setTextColor(new Color(1, 1, 1))).setX(0).setY(0).setHeight(240).setWidth(427));
				SpoutManager.getPlayer(event.getPlayer()).getMainScreen().attachWidget(plugin, (GenericTexture) new GenericTexture("http://i.imgur.com/GFEBP.png").setWidth(64).setHeight(64).setX(100).setY(100));
				event.getPlayer().sendMessage("Button test successful!");
			}
		}
		
	}
	
}