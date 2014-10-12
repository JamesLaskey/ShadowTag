package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface MapObject {
	public Texture getTexture();
	public float getX();
	public float getY();
	public Rectangle getCollisionMesh();
	public boolean isCollidable();
}
