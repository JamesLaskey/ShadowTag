package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Troll {

	Texture sprite; 
	Rectangle box; 
	
	int velocity = 5; 
	
		
	public Troll() {
		box = new Rectangle(); 
		box.x = 800 / 2 - 64 / 2;
		box.y = 20;
		box.width = 20;
		box.height = 20;
		sprite = new Texture("purple_troll.png"); 
	}
}
