package com.shadowtag.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.shadowtag.game.tests.PolygonTest;
import com.shadowtag.game.tests.ShaderTest;

public class ShadowTag extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Map map;
	Vector3 mousePos;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(); 
		camera.setToOrtho(false, 480, 320);
		
		map = new Map("map1.txt", camera);
		
		mousePos = new Vector3(); 
		
	}

	/*@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		PolygonTest l = new PolygonTest(1, 2); 
		l.render();
	} */
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		PolygonTest l = new PolygonTest(1, 2); 
		batch.setProjectionMatrix(camera.combined);
		
		//draw sprites
		batch.begin();
		map.renderMapObjects(batch);
		batch.end();
		
		camera.update(); 
	}
}
