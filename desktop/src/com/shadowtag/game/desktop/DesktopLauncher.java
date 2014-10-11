package com.shadowtag.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shadowtag.game.ShadowTag;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Shadow Tag";
		config.useGL30 = true;
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new ShadowTag(), config);
	}
}
