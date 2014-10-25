package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Wall implements MapObject{
	Texture wallText = new Texture("wall_1.png");
	Rectangle collisionMesh;
	BoundingBox boundingBox;
	
	float x;
	float y;
	int width;
	int height;
	
	public Wall(int x, int y){
		this.x = x;
		this.y = y;
		width = wallText.getWidth();
		height = wallText.getHeight();
		boundingBox = new BoundingBox(new Vector3(x,y,0), 
				new Vector3(x+width, y+height, 0));
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

	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}
}
