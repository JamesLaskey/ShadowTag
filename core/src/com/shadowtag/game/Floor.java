package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Floor implements MapObject{
	
	Texture floorText = new Texture("wall.png");
	Rectangle collisionMesh;
	
	int x;
	int y;
	int width;
	int height;
	
	public Floor(int x, int y){
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
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
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
