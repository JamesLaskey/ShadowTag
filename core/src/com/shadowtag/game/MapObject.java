package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;

public interface MapObject {
	public Texture getTexture();
	public float getX();
	public float getY();
	public Rectangle getCollisionMesh();
	public BoundingBox getBoundingBox();
	public boolean isCollidable();
}
