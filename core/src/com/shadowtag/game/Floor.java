package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Floor implements MapObject{
	
	Texture floorText = new Texture("floor_1.png");
	Rectangle collisionMesh;
	
	float x;
	float y;
	int width;
	int height;
	
	public Floor(float x, float y){
		this.x = x;
		this.y = y;
		width = floorText.getWidth();
		height = floorText.getHeight();
		
		collisionMesh = new Rectangle(x,y,width,height);
	}

	@Override
	public Texture getTexture() {
		return floorText;
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
		return collisionMesh;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

}
