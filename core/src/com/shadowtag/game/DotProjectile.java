package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DotProjectile implements DynamicMapObject{
	
	private float x;
	private float y;
	private static final float VELOCITY = 5.0f;
	private float direction;
	private Sprite selfSprite;
	
	public DotProjectile(float x, float y){
		this.x = x;
		this.y = y;
		
		selfSprite = new Sprite();
		selfSprite.setOriginCenter();
		selfSprite.setTexture(new Texture("black_dot.png"));
	}

	@Override
	public Texture getTexture() {
		return selfSprite.getTexture();
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public Rectangle getCollisionMesh() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Map map) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
