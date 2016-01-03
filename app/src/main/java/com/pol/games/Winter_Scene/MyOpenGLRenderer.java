package com.pol.games.Winter_Scene;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;


public class MyOpenGLRenderer implements Renderer {

	private Context context;
	private Object3D winter;
    private Object3D sphere;
    private float angle = 0.0f;
	private Camera camera1;
	private Light light0;
	private Light light1;
	private Light light2;
	private Light light3;
	private Light light4;
    private Particles particles;
	private Fog fog;
	private int setLights;
	private MediaPlayer mediaPlayer;



	public MyOpenGLRenderer(Context context){
		this.context = context;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		// Image Background color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		//Enable Smooth Shading
		gl.glShadeModel(GL10.GL_SMOOTH);

		//Enable Depth Testing
		gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glEnable(GL10.GL_LIGHTING);

		light0 = new Light(gl, GL10.GL_LIGHT0);		//red light
        light0.enable();
        float [] pos0 = {-13.0f, 20.0f, 15.0f, 1.0f};
        light0.setPosition(pos0);
        float[] color_ambient0 = {1.0f, 0.0f, 0.0f, 1.0f};
		light0.setAmbientColor(color_ambient0);
        float[] color_difusa0 = {100.0f, 0.0f, 0.0f, 1.0f};
		light0.setDiffuseColor(color_difusa0);
        float[] specular = {1.0f, 1.0f, 1.0f, 1.0f};
        light0.setSpecular(specular);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_QUADRATIC_ATTENUATION, 1.0f);

        light1 = new Light(gl, GL10.GL_LIGHT1);	//moon ambient light
        light1.enable();
        float [] pos1 = {-7.0f, 20.0f, 0.0f, 1.0f};
		light1.setPosition(pos1);
		float[] color_ambient1 = {20.0f, 20.0f, 20.0f, 1.0f};
		light1.setAmbientColor(color_ambient1);
		float[] color_difusa1 = {0.0f, 0.0f, 0.0f, 1.0f};
        light1.setDiffuseColor(color_difusa1);
        light1.setSpecular(specular);
        gl.glLightf(GL10.GL_LIGHT1, GL10.GL_QUADRATIC_ATTENUATION, 1.0f);


		light2 = new Light(gl, GL10.GL_LIGHT2);	//main light
        light2.enable();
        float [] pos2 = {-10.0f, 40.0f, 0.0f, 1.0f};
		light2.setPosition(pos2);
		float[] color_ambient2 = {20.0f, 20.0f, 20.0f};
		light2.setAmbientColor(color_ambient2);
		float[] color_difusa2 = {300.0f, 300.0f, 500.0f};
        light2.setDiffuseColor(color_difusa2);
        light2.setSpecular(specular);
        gl.glLightf(GL10.GL_LIGHT2, GL10.GL_QUADRATIC_ATTENUATION, 1.0f);

		light3 = new Light(gl, GL10.GL_LIGHT3);	//green light
		light3.enable();
		float [] pos3 = {3.0f, 20.0f, 15.0f, 1.0f};
		light3.setPosition(pos3);
		float[] color_ambient3 = {0.0f, 1.0f, 0.0f, 1.0f};
		light3.setAmbientColor(color_ambient3);
		float[] color_difusa3 = {0.0f, 100.0f, 0.0f, 1.0f};
		light3.setDiffuseColor(color_difusa3);
		light3.setSpecular(specular);
		gl.glLightf(GL10.GL_LIGHT3, GL10.GL_QUADRATIC_ATTENUATION, 1.0f);

		light4= new Light(gl, GL10.GL_LIGHT4);	//blue light
		light4.enable();
		float [] pos4 = {-5.0f, 20.0f, 15.0f, 1.0f};
		light4.setPosition(pos4);
		float[] color_ambient4 = {0.0f, 0.0f, 1.0f, 1.0f};
		light4.setAmbientColor(color_ambient4);
		float[] color_difusa4 = {0.0f, 0.0f, 100.0f, 1.0f};
		light4.setDiffuseColor(color_difusa4);
		light4.setSpecular(specular);
		gl.glLightf(GL10.GL_LIGHT4, GL10.GL_QUADRATIC_ATTENUATION, 1.0f);

        ByteBuffer vtbb = ByteBuffer.allocateDirect(specular.length * 4);
        vtbb.order(ByteOrder.nativeOrder());
        FloatBuffer specularBuffer = vtbb.asFloatBuffer();
        specularBuffer.put(specular);
        specularBuffer.position(0);

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specularBuffer);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 100.0f);

		camera1 = new Camera(gl);

		camera1.moveUp(15.0f);
		camera1.moveBackward(30.0f);
		camera1.moveLeft(7.0f);
        camera1.roll(-15.0f);

		winter = new Object3D(context, R.raw.winter);
        sphere = new Object3D(context, R.raw.earth);
        particles = new Particles(gl,context);

		fog = new Fog(0.005f, new float[] {204.0f/255.0f, 207.0f/255.0f, 188.0f/255.0f, 1.0f});
		mediaPlayer = MediaPlayer.create(context, R.raw.santa_claus_song);
		mediaPlayer.start();
	}

	public void onDrawFrame(GL10 gl) {
		
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();

		camera1.look();

        fog.draw(gl);

		switch((setLights/10)%3){
			case 0:
				light4.disable();
				light0.enable();
				light3.enable();
				light0.setPosition();
				light3.setPosition();
				break;
			case 1:
				light0.disable();
				light3.enable();
				light4.enable();
				light3.setPosition();
				light4.setPosition();
				break;
			case 2:
				light3.disable();
				light0.enable();
				light4.enable();
				light0.setPosition();
				light4.setPosition();
				break;
			default:
				break;
		}

		setLights++;

		gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
		gl.glPushMatrix();
		gl.glRotatef(-90.0f - angle, 0.0f, 1.0f, 0.0f);
		winter.draw(gl);

		light1.setPosition();
		light2.setPosition();

		gl.glTranslatef(-7.0f, 20.0f, 0.0f);
		sphere.draw(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		particles.draw(gl);
		gl.glPopMatrix();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Define the Viewport
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 60.0f, (float) width / (float) height, 0.1f, 100.0f);
		
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);	
	}

	public void pauseMusic(){
		mediaPlayer.pause();
	}

	public void replayMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
	}

}
