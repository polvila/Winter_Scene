package com.pol.games.Winter_Scene;

/**
 * Created by Pol on 12/11/15.
 *
 */
public class Matrix {
    private float[][] matriu;

    public Matrix(int n){
        setMatriu(identity(n));
    }

    public Matrix(float[][] matriu){
        this.matriu = matriu;
    }



    public Matrix rotateMatrix(float x, float y, float z, float a){
        Matrix matrix = new Matrix(4);
        float[][] m = {{ (float) (x * x * (1 - Math.cos(a)) + Math.cos(a)), (float) (x * y * (1 - Math.cos(a)) - (z * Math.sin(a))), (float) (x * z * (1 - Math.cos(a)) + (y * Math.sin(a))), 0 },
                { (float) (y * x * (1 - Math.cos(a)) + (z * Math.sin(a))), (float) (y * y * (1 - Math.cos(a)) + Math.cos(a)), (float) (y * z * (1 - Math.cos(a)) - (x * Math.sin(a))), 0 },
                { (float) (z * x * (1 - Math.cos(a)) - (y * Math.sin(a))), (float) (z * y * (1 - Math.cos(a)) + (x * Math.sin(a))), (float) (z * z * (1 - Math.cos(a)) + Math.cos(a)), 0 },
                { 0, 0, 0, 1 } };
        matrix.setMatriu(m);
        return matrix;

    }

    // return n-by-n identity matrix I
    public static float[][] identity(int n) {
        float[][] I = new float[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }

    // return C = A * B
    public float[][] multiply(float[][] B) {
        int mA = matriu.length;
        int nA = matriu[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        float[][] C = new float[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += matriu[i][k] * B[k][j];

        return C;
    }

    public float[][] getMatriu() {
        return matriu;
    }

    public void setMatriu(float[][] matriu) {
        this.matriu = matriu;
    }
}
