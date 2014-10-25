package com.shadowtag.game;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;

public class DotProjectile implements DynamicMapObject{
	
	private float x;
	private float y;
	private float velocity = 5.0f;
	private float direction;
	
	private Sprite sprite;
	private Map map;
	
	private Vector3 origin;
	private static final int NUM_RAYS = 4;
	private static final float RAY_LENGTH = 40f;
	private float[] bounds = new float[NUM_RAYS*2];
	private short[] lightVertexTriangles = new short[] {
		0,1,2,
		0,2,3
	};
	private PolygonRegion lightRegion = null;
	
	public DotProjectile(float x, float y, float direction, Map map){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.origin = new Vector3(x,y,0);
		
		sprite = new Sprite(new Texture("black_dot.png"));
		sprite.setPosition(x, y);
		
		
		this.map = map;
	}

	@Override
	public Texture getTexture() {
		return sprite.getTexture();
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
		return sprite.getBoundingRectangle();
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public void update(Map map) {
		float diffx = velocity * MathUtils.cosDeg(direction);
		float diffy = velocity * MathUtils.sinDeg(direction);
		
		if(velocity == 0 || map.checkCollision(getCollisionMesh())) {
			velocity = 0;
			castRays();
		}else {
			x += diffx;
			y += diffy;
			sprite.setPosition(x, y);
			origin.set(diffx, diffy, 0);
		}
	}

	private void castRays() {
		//if bottleneck, move these vectors to be member fields
		//direction should be length of RAY_LENGTH
		Vector3 direction = new Vector3(RAY_LENGTH, 0 ,0);
		Ray ray = new Ray(origin, direction);
		float rotationStep = 360 / NUM_RAYS;
		
		for(int i = 0; i < NUM_RAYS; i++) {
			System.out.println(direction.x + " " + direction.y);
			Vector3 endpoint = map.raycast(ray, RAY_LENGTH);
			System.out.println(endpoint.x + " " + endpoint.y);
			if(endpoint == null) {
				endpoint.set(origin.x + direction.x, origin.y + direction.y, 0);
			}
			bounds[2*i] = endpoint.x;
			bounds[2*i + 1] = endpoint.y;
			
			direction.set((float) (RAY_LENGTH * Math.cos(Math.toRadians(rotationStep*(i+1)))),
					(float) (RAY_LENGTH * Math.sin(Math.toRadians(rotationStep*(i+1)))),
					0);
			ray.set(origin, direction);
		}
		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(0xDEADBEFF); // DE is red, AD is green and BE is blue.
		pix.fill();
		Texture textureSolid = new Texture(pix);
		lightRegion = new PolygonRegion(new TextureRegion(textureSolid),
				bounds,
				lightVertexTriangles);
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		if(velocity == 0){
			PolygonSpriteBatch polyBatch = new PolygonSpriteBatch();
			PolygonSprite poly = new PolygonSprite(lightRegion);
			poly.setOrigin(x, y);
			polyBatch.begin();
			poly.draw(polyBatch);
			polyBatch.end();
		}
		
	}


	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
