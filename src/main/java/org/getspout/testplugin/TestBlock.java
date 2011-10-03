package org.getspout.testplugin;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class TestBlock extends GenericCubeCustomBlock {
	public static int[] faces = new int[]{0,0,0,0,0,0};

	public TestBlock(Plugin plugin) {
		super(plugin, "CustomBlock", new GenericCubeBlockDesign(plugin.getDescription().getName(), "http://dl.dropbox.com/u/40267690/quartz.png", 16, faces), false, 0);
		ItemManager im = SpoutManager.getItemManager();
		im.setCustomItemBlock(getCustomId(), getRawId(), (short) 0);
		im.setItemTexture(Material.FLINT, (short) getCustomId(), plugin, "http://dl.dropbox.com/u/40267690/quartz.png");
	}

}
