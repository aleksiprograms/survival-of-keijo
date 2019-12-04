package com.aleksiprograms.survivalofkeijo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aleksiprograms.survivalofkeijo.TheGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.x = 50;
		config.y = 50;
		config.samples = 8;
		config.title = "Survival of Keijo";
		new LwjglApplication(new TheGame(), config);
	}
}