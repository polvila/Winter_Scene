package com.pol.games.Winter_Scene;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pol on 18/11/15.
 *
 */
public class Particles {

    private int MAX_PARTICLES = 700;
    float	slowdown=2.0f;				// Slow Down Particles
    float	zoom=-40.0f;				// Used To Zoom Out

    List<ParticleData> particlelist = new ArrayList<>(1000);

    public Particles(GL10 gl, Context context){
        gl.glShadeModel(GL10.GL_SMOOTH);                            //Enables Smooth Color Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               //This Will Clear The Background Color To Black
        gl.glClearDepthf(1.0f);                                  //Enables Clearing Of The Depth Buffer
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // Really Nice Perspective Calculations
        gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_NICEST);				// Really Nice Point Smoothing

        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        gl.glTexEnvx(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);

        for (int i=0;i<MAX_PARTICLES;i++)		//Create all the initial particles
        {
            particlelist.add(new ParticleData(i, new Texture(gl, context, R.raw.particle)));
        }

    }

    public void draw(GL10 gl){
        for(ParticleData pd: particlelist)
        {
            if (pd.active)							// If The Particle Is Active
            {
                pd.drawParticle(gl,zoom);
                pd.x+=pd.xi/(slowdown*1000);// Move On The X Axis By X Speed
                pd.y+=pd.yi/(slowdown*1000);// Move On The Y Axis By Y Speed
                pd.z+=pd.zi/(slowdown*1000);// Move On The Z Axis By Z Speed

                pd.xi+=pd.xg;			// Take Pull On X Axis Into Account
                pd.yi+=pd.yg;			// Take Pull On Y Axis Into Account
                pd.zi+=pd.zg;			// Take Pull On Z Axis Into Account
                pd.life-=pd.fade;		// Reduce Particles Life By 'Fade'

                if (pd.life < 0.0f) pd.initParticle();	// If Particle Is Burned Out
            }
        }
    }

}
