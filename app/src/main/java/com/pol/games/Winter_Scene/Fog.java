package com.pol.games.Winter_Scene;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pol on 5/12/15.
 *
 */
public class Fog {

    private float density;
    private FloatBuffer color;

    public Fog(float density, float[] color){
        this.density = density;

        ByteBuffer vbf = ByteBuffer.allocateDirect(color.length * 4);
        vbf.order(ByteOrder.nativeOrder());
        this.color = vbf.asFloatBuffer();
        this.color.put(color);
        this.color.position(0);
    }

    public void draw(GL10 gl){
        gl.glClearDepthf(1);
        gl.glEnable(GL10.GL_FOG);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glFogfv(GL10.GL_FOG_COLOR, this.color);
        gl.glFogf(GL10.GL_FOG_MODE, GL10.GL_EXP2);

        gl.glFogf(GL10.GL_FOG_DENSITY, density);
    }
}
