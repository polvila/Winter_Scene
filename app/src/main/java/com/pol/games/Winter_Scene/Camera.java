package com.pol.games.Winter_Scene;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

public class Camera {
	GL10 gl;
	Vertex4 pos;
	Vertex4 forward, up, side;
	
	public Camera(GL10 gl) {
		this.gl = gl;
		
		pos = new Vertex4(0.0f, 0.0f, 0.0f, 1.0f);	//camera position
		forward = new Vertex4(0.0f, 0.0f, -1.0f, 0.0f);	//camera direction
		up = new Vertex4(0.0f, 1.0f, 0.0f, 0.0f);	//vertical vector
		side = new Vertex4(1.0f, 0.0f, 0.0f, 0.0f);	//perpendicular to up & forward
	}
	
	public void moveLeft(float inc) {
		pos = pos.add( side.normalize().mult(-inc) );
	}
	
	public void moveRight(float inc) {
		pos = pos.add( side.normalize().mult(inc) );
	}
	
	public void moveUp(float inc) {
		pos = pos.add(up.normalize().mult(inc));
	}
	
	public void moveDown(float inc) {
		pos = pos.add(up.normalize().mult(-inc));
	}
	
	public void moveForward(float inc) {
		pos = pos.add( forward.normalize().mult(inc) );
	}
	
	public void moveBackward(float inc) {
		pos = pos.add( forward.normalize().mult(-inc) );
	}
	
	public void yaw(float angle) {
		Matrix m = new Matrix(4);
		angle = (float)Math.toRadians( angle);
		m.setMatriu( m.rotateMatrix(forward.get(0), forward.get(1), forward.get(2), angle).getMatriu());
		float [][] side2 = new float[4][1];
		side2[0][0] = side.get(0);
		side2[1][0] = side.get(1);
		side2[2][0] = side.get(2);
		side2[3][0] = side.get(3);

		Matrix m2 = new Matrix( m.multiply(side2));

		side = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        side = side.normalize();

		float [][] up2 = new float[4][1];
		up2[0][0] = up.get(0);
		up2[1][0] = up.get(1);
		up2[2][0] = up.get(2);
		up2[3][0] = up.get(3);
		m2.setMatriu(m.multiply(up2));

		up = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        up = up.normalize();
	}
	
	public void pitch(float angle) {
        Matrix m = new Matrix(4);
        angle = (float)Math.toRadians( angle);
        m.setMatriu( m.rotateMatrix(up.get(0), up.get(1), up.get(2), angle).getMatriu());
        float [][] side2 = new float[4][1];
        side2[0][0] = side.get(0);
        side2[1][0] = side.get(1);
        side2[2][0] = side.get(2);
        side2[3][0] = side.get(3);

        Matrix m2 = new Matrix( m.multiply(side2));

        side = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        side = side.normalize();

        float [][] forward2 = new float[4][1];
        forward2[0][0] = forward.get(0);
        forward2[1][0] = forward.get(1);
        forward2[2][0] = forward.get(2);
        forward2[3][0] = forward.get(3);
        m2.setMatriu(m.multiply(forward2));

        forward = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        forward = forward.normalize();
	}
	
	public void roll(float angle) {
        Matrix m = new Matrix(4);
        angle = (float)Math.toRadians( angle);
        m.setMatriu( m.rotateMatrix(side.get(0), side.get(1), side.get(2), angle).getMatriu());
        float [][] up2 = new float[4][1];
        up2[0][0] = up.get(0);
        up2[1][0] = up.get(1);
        up2[2][0] = up.get(2);
        up2[3][0] = up.get(3);

        Matrix m2 = new Matrix( m.multiply(up2));

        up = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        up = up.normalize();

        float [][] forward2 = new float[4][1];
        forward2[0][0] = forward.get(0);
        forward2[1][0] = forward.get(1);
        forward2[2][0] = forward.get(2);
        forward2[3][0] = forward.get(3);
        m2.setMatriu(m.multiply(forward2));

        forward = new Vertex4(m2.getMatriu()[0][0],m2.getMatriu()[1][0],m2.getMatriu()[2][0],m2.getMatriu()[3][0]);
        forward = forward.normalize();
	}
	
	public void look()
	{
		Vertex4 center = pos.add(forward);

		GLU.gluLookAt(gl, pos.get(0), pos.get(1), pos.get(2), 
						  center.get(0), center.get(1), center.get(2), 
						  up.get(0), up.get(1), up.get(2));
	}
}
