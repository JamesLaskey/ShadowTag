package com.shadowtag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

public class Map {
	private int mapSizeXBlocks;
	private int mapSizeYBlocks;
	private static final int BLOCK_SIZE = 20;
	
	private Array<DynamicMapObject> dynamicObjectArray;
	private Array<Array<MapObject>> mapObjectArray;
	private Array<Agent> agentArray;
	
	private OrthographicCamera camera;
	
	public Map(String mapFilePath, OrthographicCamera camera){
		this.camera = camera;
		dynamicObjectArray = new Array<DynamicMapObject>(100);
		agentArray = new Array<Agent>(4);
		
		mapObjectArray = buildMap(mapFilePath);
	}
	
	private Array<Array<MapObject>> buildMap(String filePath) {
		FileHandle mapFile = Gdx.files.internal(filePath);
		String mapData = mapFile.readString();
		String[] mapYRows = mapData.split("\n");
		
		mapSizeYBlocks = mapYRows.length;
		System.out.println(mapSizeYBlocks);
		mapSizeXBlocks = mapYRows[0].length();
		System.out.println(mapSizeXBlocks);
		
		mapObjectArray = new Array<Array<MapObject>>(mapSizeYBlocks);
		for(int i = 0; i < mapSizeYBlocks; i++){
			char[] mapXRow = mapYRows[i].toCharArray();
			mapObjectArray.add(new Array<MapObject>(mapSizeXBlocks));
			Array<MapObject> objArr = mapObjectArray.get(i);
			for(int j = 0; j < mapSizeXBlocks; j++){
				switch (mapXRow[j]) {
					case 'W': //wall
						objArr.add(new Wall(j*BLOCK_SIZE, i*BLOCK_SIZE));
						break;
					case 'E': //floor
						objArr.add(new Floor(j*BLOCK_SIZE, i*BLOCK_SIZE));
						break;
					case '#': //spawn point 
						objArr.add(new Floor(j*BLOCK_SIZE, i*BLOCK_SIZE));
						agentArray.add(new Troll(j*BLOCK_SIZE, i*BLOCK_SIZE, this, camera));
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
		
		for(Agent agent : agentArray) {
			agent.update(this);
			agent.render(batch);
		}
		
		for(DynamicMapObject obj : dynamicObjectArray) {
			obj.update(this);
			obj.render(batch);
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
	
	//perhaps take in Vector3 to prevent creating new one each time?
	/*
	 * checks a ray against all the map objects and produces an intersection point if it is within dist
	 * 
	 */
	public Vector3 raycast(Ray ray, float dist) {
		Vector3 intersection = new Vector3();
		float closestDist = dist;
		Vector3 origin = ray.origin;
		Vector3 closestIntersect = null;
		
		for(int j = 0; j < mapSizeYBlocks; j++) {
			for(int i = 0; i < mapSizeXBlocks; i++) {
				MapObject obj = mapObjectArray.get(j).get(i);
				if(obj.isCollidable()){
					Intersector.intersectRayBounds(ray, obj.getBoundingBox(), intersection);
					if(intersection.dst(origin) < closestDist) {
						closestIntersect = intersection.cpy();
					}
				}
			}
		}
		return closestIntersect;
	}
	
	public void registerDynamicObject(DynamicMapObject obj){
		dynamicObjectArray.add(obj);
	}
}
