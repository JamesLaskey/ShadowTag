package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Troll {

	
	Sprite sprite; 
	
	int velocity = 5; 
	Array<Sprite> trail; 
		
	public Troll() {
		sprite = new Sprite(); 
		sprite.setTexture(new Texture("purple_troll.png"));
		sprite.setBounds(100, 100, 20, 20);

		trail = new Array<Sprite>(); 
		trail.add(new Sprite(sprite)); 
	}

	public void render(SpriteBatch batch) {
		//sprite.draw(batch);
		//batch.draw(new Texture("purple_troll.png"), sprite.getX(), sprite.getY()); 
		batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY()); 
		/*
		for (Sprite v : trail) {
			v.draw(batch);
		}
        trail.add(new Sprite(sprite)); 
        if (trail.size > 100) {
        	trail.removeIndex(0); 
        }
        */

	}
}
