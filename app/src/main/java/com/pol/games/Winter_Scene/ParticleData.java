package com.pol.games.Winter_Scene;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pol on 18/11/15.
 *
 */
public class ParticleData {
    int id;
    boolean	active;					// Active (Yes/No)
    float	life;					// particle Life
    float	fade;					// Fade Speed
    float	r;						// Red Value
    float	g;						// Green Value
    float	b;						// Blue Value
    float	x;						// X Position
    float	y;						// Y Position
    float	z;						// Z Position
    float	xi;						// X Direction
    float	yi;						// Y Direction
    float	zi;						// Z Direction
    float	xg;						// X Gravity
    float	yg;						// Y Gravity
    float	zg;						// Z Gravity
    private Texture texture;
    private Square square;

    public ParticleData(int id, Texture texture)
    {
        this.id = id;
        this.texture = texture;
        initParticle();
    }

    public void initParticle()
    {
        active 	= true;												// Make All The Particles Active
        life 	= 2.0f;								// Give All The Particles Full Life
        fade	= (float)(100 * Math.random())/1000.0f+0.003f;		// Random Fade Speed
        x       = -32.0f+(float)(50*Math.random());
        y       = 35.0f;
        r		= 1.0f;			// Select White Color
        g		= 1.0f;			// Select White Color
        b		= 1.0f;			// Select White Color
        xi		= (float)((50 * Math.random())-26.0f)*10.0f;		// Random Speed On X Axis
        yi		= (float)((-50 * Math.random()))*10.0f;		// Random Speed On Y Axis
        zi		= (float)((50 * Math.random())-25.0f)*10.0f;		// Random Speed On Z Axis
        xg		= 0.0f;												// Set Horizontal Pull To Zero
        yg		= -3.0f;											// Set Vertical Pull Downward
        zg		= 0.0f;												// Set Pull On Z Axis To Zero
    }

    public void drawParticle(GL10 gl, float zoom) {
        if(active){

            gl.glPushMatrix();
            gl.glDisable(GL10.GL_FOG);
            gl.glDisable(GL10.GL_LIGHTING);
            gl.glDisable(GL10.GL_DEPTH_TEST);
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, id);

            gl.glColor4f(r, g, b, life);
            gl.glNormal3f(0.0f, 0.0f, 1.0f);

            square = new Square();

            float[] list= new float[]{0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f};

            ByteBuffer vbb = ByteBuffer.allocateDirect(list.length * 4);
            vbb.order(ByteOrder.nativeOrder());
            FloatBuffer particleBuffer = vbb.asFloatBuffer();
            particleBuffer.put(list);
            particleBuffer.position(0);

            gl.glTranslatef(x - 0.5f, y - 0.5f, z + zoom);
            square.setTexture(texture, particleBuffer);
            square.draw(gl);
            gl.glDisable(GL10.GL_TEXTURE_2D);
            gl.glDisable(GL10.GL_BLEND);
            gl.glEnable(GL10.GL_LIGHTING);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glEnable(GL10.GL_FOG);
            gl.glPopMatrix();

        }
    }


}
