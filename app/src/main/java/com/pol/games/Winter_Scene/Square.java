package com.pol.games.Winter_Scene;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pol on 18/11/15.
 *
 */
public class Square {
    private float vertices[] = {0.0f, 0.0f, 0.0f,
            0.0f,  1.0f, 0.0f,
            1.0f,  1.0f, 0.0f,
            1.0f, 0.0f, 0.0f};
    private short faces[] = { 0, 1, 2, 0, 2, 3 };

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    private FloatBuffer coordsBuffer;
    private Texture tex;

    public Square() {
        //Move the vertices list into a buffer
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        //Move the faces list into a buffer
        ByteBuffer ibb = ByteBuffer.allocateDirect(faces.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(faces);
        indexBuffer.position(0);
    }

    public void setTexture(Texture tex, FloatBuffer coords){
        this.tex = tex;
        this.coordsBuffer = coords;
    }

    public void setTextureImage(Texture tex){
        this.tex = tex;
    }

    public void setTextureCoords(FloatBuffer coords){
        this.coordsBuffer = coords;
    }

    public void draw(GL10 gl) {

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0,
                coordsBuffer);
        gl.glBindTexture(gl.GL_TEXTURE_2D, tex.getTexture_id()[0]);

        gl.glDrawElements(GL10.GL_TRIANGLES, faces.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

    }
}
