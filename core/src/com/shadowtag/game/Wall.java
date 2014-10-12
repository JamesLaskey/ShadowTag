package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Wall implements MapObject{
	Texture wallText = new Texture("wall.png");
	Rectangle collisionMesh;
	
	float x;
	float y;
	int width;
	int height;
	
	public Wall(int x, int y){
		this.x = x;
		this.y = y;
		width = wallText.getWidth();
		height = wallText.getHeight();
		
		collisionMesh = new Rectangle(x,y,width,height);
	}

	@Override
	public Texture getTexture() {
		return wallText;
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
		return true;
	}
}
