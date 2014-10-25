package com.shadowtag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Troll implements Agent{

	private Sprite sprite; 
	
	private Map map; 
	
	private int velocity = 5;  
	private Vector2 mouse2d; 
	private Vector2 pos; 
	private Vector3 mousePos;
	private OrthographicCamera camera;
	
	private float width;
	private float height;
	
		
	public Troll(float x, float y, Map map, OrthographicCamera camera) {
		this.camera = camera;
		sprite = new Sprite(new Texture("purple_troll.png")); 

		sprite.setCenter(x, y);
		sprite.setOriginCenter();
		
		width = sprite.getWidth();
		height = sprite.getHeight();
		
		mouse2d = new Vector2(); 
		pos = new Vector2(); 
		mousePos = new Vector3();
		
		this.map = map; 
	}

	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	@Override
	public void update(Map map) {
		
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);
		
		float orgX = sprite.getX(); 
		float orgY = sprite.getY(); 

		Vector3 diff = mousePos.sub(sprite.getX() + (width/2f), 
				sprite.getY() + (height/2f), 0); 
		mouse2d.set(diff.x, diff.y); 
		
		float dir = mouse2d.angle(); 

		sprite.setRotation((float) dir);

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
		
		if(Gdx.input.justTouched()) {
			System.out.println("Fire!");
			map.registerDynamicObject(new DotProjectile(sprite.getX(), sprite.getY(), sprite.getRotation(), map));
		}
	}
}
