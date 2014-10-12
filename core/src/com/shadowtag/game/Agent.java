package com.shadowtag.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Agent {
	public void update(Map map);
	public void render(SpriteBatch batch);
}
