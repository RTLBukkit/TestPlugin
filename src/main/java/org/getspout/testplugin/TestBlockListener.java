package org.getspout.testplugin;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.ItemManager;

public class TestBlockListener extends BlockListener {

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		super.onBlockBreak(event);
		ItemManager im = SpoutManager.getItemManager();
		
		SpoutBlock block = im.getSpoutBlock(event.getBlock());
		
		if(block.getType().equals(Material.DIRT)) {
			int id = block.getCustomBlockId();
			ItemStack is = im.getCustomItemStack(TestPlugin.testBlock, 1);
			if(id == TestPlugin.testBlock.getCustomId()) {
				block.getWorld().dropItem(block.getLocation(), is);
			}
		}
	}
}
