package com.shadowtag.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface DynamicMapObject extends MapObject{
	public void update(Map map);
	public void render(SpriteBatch batch);
}
