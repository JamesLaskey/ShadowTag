package com.shadowtag.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class ShadowTag extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Troll purple; 

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(); 
		camera.setToOrtho(false, 480, 320);
		
		purple = new Troll(); 
	}

	@Override
	public void render () {
		//Gdx.gl30.glClearColor(0.0f, 0.8f, 0.2f, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		//draw sprites
		batch.begin();
		batch.draw(purple.sprite, purple.box.x, purple.box.y); 
		batch.end();
		
		
		//listeners
		/*
		if (Gdx.input.isTouched()) {
			mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mousePos);
			troll.x = mousePos.x - troll.width / 2;
		}
		*/
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			purple.box.y += purple.velocity; 
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			purple.box.x -= purple.velocity; 
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			purple.box.y -= purple.velocity; 
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			purple.box.x += purple.velocity; 
		}
		camera.update(); 
	}
}
