package com.shadowtag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Lamp implements DynamicMapObject{

	private Mesh spread; 
	
	int x; 
	int y; 
	

	Lamp(int x, int y) {
		spread = new Mesh(false, 100, 100, new VertexAttribute(Usage.Position, 3, "a_position")); 
		int numAttributes = 3;
		float radius = 100; 
		float[] vertices = new float[spread.getMaxVertices()*numAttributes]; 
		for (int i = 0; i < vertices.length; i++) {
			float d = 360/spread.getMaxVertices() * i; 
			vertices[i] = MathUtils.cosDeg(d) * 100 + x;  // x
			vertices[i+1] = MathUtils.cosDeg(d) * 100 + y;  // y
			vertices[i+2] = 0;  // z 
		}
		spread.setVertices(vertices); 
		short[] indices = new short[spread.getMaxIndices()]; 
		for (short i = 0; i < indices.length; i++)
			indices[i] = i; 
		spread.setIndices(indices); 
	}
	
	@Override
	public Texture getTexture() {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Map map) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		String vertexShader = "attribute vec4 a_position;    \n" + 
				"attribute vec4 a_color;\n" +
				"attribute vec2 a_texCoord0;\n" + 
				"uniform mat4 u_worldView;\n" + 
				"varying vec4 v_color;" + 
				"varying vec2 v_texCoords;" + 
				"void main()                  \n" + 
				"{                            \n" + 
				"   v_color = vec4(1, 1, 1, 1); \n" + 
				"   v_texCoords = a_texCoord0; \n" + 
				"   gl_Position =  u_worldView * a_position;  \n"      + 
				"}                            \n" ;
		String fragmentShader = "#ifdef GL_ES\n" +
				"precision mediump float;\n" + 
				"#endif\n" + 
				"varying vec4 v_color;\n" + 
				"varying vec2 v_texCoords;\n" + 
				"uniform sampler2D u_texture;\n" + 
				"void main()                                  \n" + 
				"{                                            \n" + 
				"  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" +
				"}";
		ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader); 
		spread.render(shader, GL20.GL_TRIANGLES); 
		
	}

}
