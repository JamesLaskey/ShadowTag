package com.shadowtag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Troll {

	
	Sprite sprite; 
	
	Map map; 
	
	int velocity = 5; 
	Array<Sprite> trail; 
	Vector2 mouse2d; 
	Vector2 pos; 
	
		
	public Troll(Map map) {
		sprite = new Sprite(); 
		sprite.setTexture(new Texture("purple_troll.png"));
		sprite.setBounds(100, 100, 20, 20);
		
		mouse2d = new Vector2(); 
		pos = new Vector2(); 

		trail = new Array<Sprite>(); 
		trail.add(new Sprite(sprite)); 
		
		this.map = map; 
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
	
	public void update(Vector3 mousePos) {
		
		float orgX = sprite.getX(); 
		float orgY = sprite.getY(); 

		float dir = mouse2d.set(mousePos.x, mousePos.y).angle(pos.set(sprite.getX(), sprite.getY())); 
		System.out.println(dir);
		sprite.rotate(dir); 

		if (Gdx.input.isKeyPressed(Keys.W)) {
			sprite.translateY(velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			sprite.translateX(-1 * velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			sprite.translateY(-1 * velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			sprite.translateX(velocity);
		}
		if(map.checkCollision(sprite.getBoundingRectangle())) {
			sprite.setX(orgX); 
			sprite.setY(orgY); 
		}
	}
}
