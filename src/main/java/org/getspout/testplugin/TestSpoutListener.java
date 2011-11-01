package org.getspout.testplugin;

import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.gui.Align;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlider;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TestSpoutListener extends SpoutListener {

	@Override
	public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
		event.getPlayer().sendMessage("Testing commences!");
		GenericPopup popup = new GenericPopup();
		popup.attachWidget(null, new GenericButton("Test").setAlignX(Align.FIRST).setX(70).setY(102).setHeight(35).setWidth(100));
		popup.attachWidget(null, new GenericTextField().setX(70).setY(142).setHeight(35).setWidth(100));
		popup.attachWidget(null, new GenericSlider().setX(70).setY(242).setHeight(35).setWidth(100));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));
		popup.attachWidget(null, ((GenericLabel) new GenericLabel("Some\nLonger Text\nis").setX(0).setY(0).setHeight(427).setWidth(240)));

		popup.attachWidget(null, new GenericLabel("Bottom right of middle").setX(130).setY(230).setHeight(427).setWidth(240));
		((SpoutPlayer) event.getPlayer()).getMainScreen().attachPopupScreen(popup);
	}
}
