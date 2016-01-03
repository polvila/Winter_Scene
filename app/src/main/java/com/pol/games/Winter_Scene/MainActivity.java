package com.pol.games.Winter_Scene;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyOpenGLRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView view = new GLSurfaceView(this);
        mRenderer = new MyOpenGLRenderer(this);
        view.setRenderer(mRenderer);
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRenderer.pauseMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRenderer.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRenderer.replayMusic();
    }
}
