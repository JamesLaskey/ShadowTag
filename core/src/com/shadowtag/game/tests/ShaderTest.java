package com.shadowtag.game.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShaderTest  {

	private Mesh spread; 
	
	int x; 
	int y; 
	
	
	public static final String VERT_SHADER =  
			"attribute vec2 a_position;\n" +
			"attribute vec4 a_color;\n" +			
			"uniform mat4 u_projTrans;\n" + 
			"varying vec4 vColor;\n" +			
			"void main() {\n" +  
			"	vColor = a_color;\n" +
			"	gl_Position =  u_projTrans * vec4(a_position.xy, 0.0, 1.0);\n" +
			"}";
	
	public static final String FRAG_SHADER = 
            "#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
			"varying vec4 vColor;\n" + 			
			"void main() {\n" +  
			"	gl_FragColor = vColor;\n" + 
			"}";
	
	
	//Position attribute - (x, y) 
	public static final int POSITION_COMPONENTS = 2;

	//Color attribute - (r, g, b, a)
	public static final int COLOR_COMPONENTS = 1;

	//Total number of components for all attributes
	public static final int NUM_COMPONENTS = POSITION_COMPONENTS + COLOR_COMPONENTS;

	//The "size" (total number of floats) for a single triangle
	public static final int PRIMITIVE_SIZE = 3 * NUM_COMPONENTS;

	//The maximum number of triangles our mesh will hold
	public static final int MAX_TRIS = 1;

	//The maximum number of vertices our mesh will hold
	public static final int MAX_VERTS = MAX_TRIS * 3;

	//The array which holds all the data, interleaved like so:
//	    x, y, r, g, b, a
//	    x, y, r, g, b, a, 
//	    x, y, r, g, b, a, 
//	    ... etc ...
	protected float[] verts = new float[MAX_VERTS * NUM_COMPONENTS];

	//The current index that we are pushing triangles into the array
	protected int idx = 0;
	
	ShaderProgram shader; 
	OrthographicCamera cam; 
	
	ImmediateModeRenderer20 r; 


	
	void drawTriangle(float x, float y, float width, float height, Color color) {
		//we don't want to hit any index out of bounds exception...
		//so we need to flush the batch if we can't store any more verts
		if (idx==verts.length)
			flush();
		
		//now we push the vertex data into our array
		//we are assuming (0, 0) is lower left, and Y is up
		
		float c = color.toFloatBits(); 
		//bottom left vertex
		verts[idx++] = x; 			//Position(x, y) 
		verts[idx++] = y;
		verts[idx++] = c;		  	//Color(r, g, b, a)
		
		//top left vertex
		verts[idx++] = x; 			//Position(x, y) 
		verts[idx++] = y + height;
		verts[idx++] = c;		  	//Color(r, g, b, a)
 
		//bottom right vertex
		verts[idx++] = x + width;	 //Position(x, y) 
		verts[idx++] = y;
		verts[idx++] = c;		  	//Color(r, g, b, a)

	}
	public ShaderTest(int x, int y) {
	    spread = new Mesh(true, MAX_VERTS, 0, 
	            new VertexAttribute(Usage.Position, POSITION_COMPONENTS, "a_position"),
	            new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
	    
	    shader = createMeshShader();
		cam = new OrthographicCamera();
		
		r = new ImmediateModeRenderer20(false, true, 0); 
	}
	

	
	protected static ShaderProgram createMeshShader() {
		ShaderProgram.pedantic = false;
		ShaderProgram shader = new ShaderProgram(VERT_SHADER, FRAG_SHADER);
		String log = shader.getLog();
		if (!shader.isCompiled())
			throw new GdxRuntimeException(log);		
		if (log!=null && log.length()!=0)
			System.out.println("Shader Log: "+log);
		return shader;
	}
	
	
	public void render1() {
		drawTriangle(10, 10, 40, 40, Color.RED);
		drawTriangle(50, 50, 20, 40, Color.BLUE);
		flush(); 
	}
	
	public void render2() { 
		r.begin(cam.combined, GL20.GL_TRIANGLES);
		
		drawTriangles2(10, 10, 40, 40, Color.GREEN);
		drawTriangles2(50, 50, 20, 40, Color.ORANGE);
		
		r.end();
	}
	
	void drawTriangles2(int x, int y, int width, int height, Color color) {
		//not functional
		r.color(color.r, color.g, color.b, color.a);
		r.vertex(x, y, 0);

		r.color(color.r, color.g, color.b, color.a);
		r.vertex(x, y+height, 0);

		r.color(color.r, color.g, color.b, color.a);
		r.vertex(x+width, y, 0);
		
	}
	
	
	void flush() {
		//if we've already flushed
		if (idx==0)
			return;
		
		//sends our vertex data to the mesh
		spread.setVertices(verts);
		
		//no need for depth...
		Gdx.gl.glDepthMask(false);
		
		//enable blending, for alpha
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		//number of vertices we need to render
		int vertexCount = (idx/NUM_COMPONENTS);
		
		//update the camera with our Y-up coordiantes
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//start the shader before setting any uniforms
		shader.begin();
		
		//update the projection matrix so our triangles are rendered in 2D
		shader.setUniformMatrix("u_projTrans", cam.combined);
		
		//render the mesh
		spread.render(shader, GL20.GL_TRIANGLES, 0, vertexCount);
		
		shader.end();
		
		//re-enable depth to reset states to their default
		Gdx.gl.glDepthMask(true);
		
		//reset index to zero
		idx = 0;
	}
	

}
