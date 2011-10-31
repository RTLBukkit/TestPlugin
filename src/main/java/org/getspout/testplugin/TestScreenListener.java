package org.getspout.testplugin;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Align;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TestScreenListener extends ScreenListener {
	private int tries = 0;
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		if (event.getButton() instanceof GenericButton && event.getButton().getText().equals("Test")) {
			if (tries > 3) {}
				((SpoutPlayer) event.getPlayer()).getMainScreen().closePopup();
				event.getScreen().setDirty(true);
				((SpoutPlayer) event.getPlayer()).getMainScreen().attachWidget(null,((GenericLabel) new GenericLabel("I'm on the main screen!").setAlignY(Align.FIRST)).setAlignX(Align.FIRST).setX(0).setY(0).setHeight(427).setWidth(240));
			}
			else {
				tries++;
				event.getButton().setText("Haha, Try Again!").setDirty(true);
			}
			event.getPlayer().sendMessage("Button test successful!");
		}
	}
	
}