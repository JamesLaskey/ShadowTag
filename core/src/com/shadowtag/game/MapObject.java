package com.shadowtag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface MapObject {
	public Texture getTexture();
	public int getX();
	public int getY();
	public Rectangle getCollisionMesh();
}
