package com.shadowtag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map {
	private int mapSizeXBlocks;
	private int mapSizeYBlocks;
	private static final int BLOCK_SIZE = 20;
	private Array<Array<MapObject>> mapObjectArray;
	
	public Map(String mapFilePath){
		mapObjectArray = buildMap(mapFilePath);
		for(int j = 0; j < mapSizeYBlocks; j++) {
			for(int i = 0; i < mapSizeXBlocks; i++) {
				mapObjectArray.get(j).add(new Wall(i,j));;
			}
		}
	}
	
	private Array<Array<MapObject>> buildMap(String filePath) {
		FileHandle mapFile = Gdx.files.internal(filePath);
		String mapData = mapFile.readString();
		String[] mapYRows = mapData.split("\n");
		
		mapSizeYBlocks = mapYRows.length;
		mapSizeXBlocks = mapYRows[0].length();
		
		mapObjectArray = new Array<Array<MapObject>>(mapSizeYBlocks);
		for(int i = 0; i < mapSizeYBlocks; i++){
			String[] mapXRow = mapYRows[i].split("");
			mapObjectArray.add(new Array<MapObject>(mapSizeXBlocks));
			Array<MapObject> objArr = mapObjectArray.get(i);
			
			for(int j = 0; j < mapSizeXBlocks; j++){
				switch (mapXRow[j]) {
					case "W": objArr.add(new Wall(j*BLOCK_SIZE, i*BLOCK_SIZE));
					break;
					case "E": objArr.add(new Floor(j*BLOCK_SIZE, i*BLOCK_SIZE));
					break;
				}
			}
		}
		return mapObjectArray;
	}
	
	public void renderMapObjects(SpriteBatch batch) {
		for(int j = 0; j < mapSizeYBlocks; j++) {
			for(int i = 0; i < mapSizeXBlocks; i++) {
				MapObject obj = mapObjectArray.get(j).get(i);
				batch.draw(obj.getTexture(), obj.getX(), obj.getY());
			}
		}
	}
	
	public boolean checkCollision(Rectangle collider){
		boolean ret = false;
		for(int j = 0; j < mapSizeYBlocks; j++) {
			for(int i = 0; i < mapSizeXBlocks; i++) {
				MapObject obj = mapObjectArray.get(j).get(i);
				if(obj.isCollidable()){
					ret = ret || Intersector.overlaps(collider, obj.getCollisionMesh());
				}
			}
		}
		return ret;
	}
}
