package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class DotProjectile implements DynamicMapObject{
	
	private float x;
	private float y;
	private float velocity = 5.0f;
	private float direction;
	private Sprite sprite;
	
	public DotProjectile(float x, float y, float direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		sprite = new Sprite(new Texture("black_dot.png"));
		sprite.setPosition(x, y);
	}

	@Override
	public Texture getTexture() {
		return sprite.getTexture();
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
		return sprite.getBoundingRectangle();
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public void update(Map map) {
		float diffx = velocity * MathUtils.cosDeg(direction);
		float diffy = velocity * MathUtils.sinDeg(direction);
		
		if(map.checkCollision(getCollisionMesh())) {
			velocity = 0;
		}else {
			x += diffx;
			y += diffy;
			sprite.setPosition(x, y);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}

}
