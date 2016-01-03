package com.pol.games.Winter_Scene;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Light {
	ByteBuffer vtbb;
	FloatBuffer posicio;
	FloatBuffer ambient;
	FloatBuffer difuse;
	FloatBuffer specular;
	GL10 gl;
	int lightid;
	
	public Light(GL10 gl, int lightid) {
		this.gl = gl;
		this.lightid = lightid;

		//gl.glLightf(lightid, GL10.GL_SPOT_CUTOFF, 20);
		//gl.glLightf(lightid, GL10.GL_SPOT_EXPONENT, 2.0f);

	}
	
	//To enable and disable the light
	public void enable() {gl.glEnable(lightid);}
	public void disable() {gl.glDisable(lightid);}
	
	//To position the light
	public void setPosition(float[] pos) {
		vtbb = ByteBuffer.allocateDirect(pos.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		posicio = vtbb.asFloatBuffer();
		posicio.put(pos);
		posicio.position(0);
		gl.glLightfv(lightid, GL10.GL_POSITION, posicio);
	}
	
	public void setPosition() {
		gl.glLightfv(lightid, GL10.GL_POSITION, posicio);
	}	
	
	//To set the light colors
	public void setAmbientColor(float[] color) {
		vtbb = ByteBuffer.allocateDirect(color.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		ambient = vtbb.asFloatBuffer();
		ambient.put(color);
		ambient.position(0);
		gl.glLightfv(lightid, GL10.GL_AMBIENT, ambient);
	}

	public void setDiffuseColor(float[] color) {
		vtbb = ByteBuffer.allocateDirect(color.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		difuse = vtbb.asFloatBuffer();
		difuse.put(color);
		difuse.position(0);
		gl.glLightfv(lightid, GL10.GL_DIFFUSE, difuse);
	}

	public void setSpecular(float[] specularf){
		vtbb = ByteBuffer.allocateDirect(specularf.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		specular = vtbb.asFloatBuffer();
		specular.put(specularf);
		specular.position(0);
		gl.glLightfv(lightid, GL10.GL_SPECULAR, specular);
		//gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specular);
		//gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 128);
	}
	
}
