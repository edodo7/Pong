package com.gdx.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.pong.main.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 700;
		config.width = 1200;
		config.x = 0;
		config.y = 0;
		config.resizable = false;
		new LwjglApplication(new Pong(), config);
	}
}
