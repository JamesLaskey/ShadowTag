package com.shadowtag.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map {
	
	static final int TEST_SIZE = 10;
	
	private Array<MapObject> mapObjectArray = new Array<MapObject>(TEST_SIZE);
	
	public Map(){
		for(int i = 0; i < TEST_SIZE; i++) {
			mapObjectArray.add(new Wall(i*5, i*5));
		}
	}
	
	public void renderMapObjects(SpriteBatch batch) {
		for(int i = 0; i < TEST_SIZE; i++){
			MapObject obj = mapObjectArray.get(i);
			batch.draw(obj.getTexture(), obj.getX(), obj.getY());
		}
	}
	
	public boolean checkCollision(Rectangle collider){
		boolean ret = false;
		for(int i = 0; i < TEST_SIZE; i++){
			MapObject obj = mapObjectArray.get(i);
			ret = ret || Intersector.overlaps(collider, obj.getCollisionMesh());
		}
		return ret;
	}
}
