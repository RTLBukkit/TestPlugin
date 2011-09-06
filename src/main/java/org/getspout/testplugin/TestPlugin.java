package org.getspout.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
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

public class TestPlugin extends JavaPlugin {

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new spL(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new sL(), Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, new iL(), Priority.Normal, this);
	}
	
	public class spL extends SpoutListener {
		
		@Override
		public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
			event.getPlayer().sendMessage("Testing commences!");
			GenericPopup popup = new GenericPopup();
			popup.attachWidget(new GenericButton("Test").setAlignX(Align.FIRST).setX(70).setY(102).setHeight(35).setWidth(100));
			popup.attachWidget(new GenericTextField().setX(70).setY(142).setHeight(35).setWidth(100));
			popup.attachWidget(new GenericSlider().setX(70).setY(242).setHeight(35).setWidth(100));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.THIRD).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.SECOND)).setAlignX(Align.THIRD).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.SECOND).setX(0).setY(0).setHeight(427).setWidth(240));
			popup.attachWidget(((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setHexColor(0xFFFFFF).setAlignY(Align.THIRD)).setAlignX(Align.THIRD).setX(0).setY(0).setHeight(427).setWidth(240));
			
			popup.attachWidget(new GenericLabel("Bottom right of middle").setHexColor(0xFFFFFF).setX(130).setY(230).setHeight(427).setWidth(240));
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
			}
		}
		
	}

	public class sL extends ScreenListener {
		
		@Override
		public void onButtonClick(ButtonClickEvent event) {
			if (event.getButton() instanceof GenericButton && event.getButton().getText().equals("Test")) {
				((SpoutPlayer) event.getPlayer()).getMainScreen().closePopup();
				event.getScreen().setDirty(true);
				((SpoutPlayer) event.getPlayer()).getMainScreen().attachWidget(((GenericLabel) new GenericLabel("I'm on the main screen!").setHexColor(0xFFFFFF).setAlignY(Align.FIRST)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
				event.getPlayer().sendMessage("Button test successful!");
			}
		}
		
	}
	
}
