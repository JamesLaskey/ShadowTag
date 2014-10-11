package com.shadowtag.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShadowTag extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Troll purple; 
	Map map;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(); 
		camera.setToOrtho(false, 480, 320);
		
		purple = new Troll(); 
		
		map = new Map("map1.txt");
	}

	@Override
	public void render () {
		//Gdx.gl30.glClearColor(0.0f, 0.8f, 0.2f, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		//draw sprites
		batch.begin();
		purple.render(batch); 
		map.renderMapObjects(batch);
		batch.end();
		
		
		//listeners
		/*
		if (Gdx.input.isTouched()) {
			mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mousePos);
			troll.x = mousePos.x - troll.width / 2;
		}
		*/
		float orgX = purple.sprite.getX(); 
		float orgY = purple.sprite.getY(); 
		if (Gdx.input.isKeyPressed(Keys.W)) {
			purple.sprite.translateY(purple.velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			purple.sprite.translateX(-1 * purple.velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			purple.sprite.translateY(-1 * purple.velocity);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			purple.sprite.translateX(purple.velocity);
		}
		if(map.checkCollision(purple.sprite.getBoundingRectangle())) {
			purple.sprite.setX(orgX); 
			purple.sprite.setY(orgY); 
		}
		camera.update(); 
	}
}
