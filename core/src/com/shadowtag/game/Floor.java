package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Floor implements MapObject{
	
	Texture floorText = new Texture("floor_1.png");
	BoundingBox boundingBox;
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
		
		boundingBox = new BoundingBox(new Vector3(x,y,0), 
				new Vector3(x+width, y+height, 0));
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

	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

}
