package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Wall implements MapObject{
	Texture wallText = new Texture("wall.png");
	Rectangle collisionMesh;
	
	int x;
	int y;
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
}
