package com.company.lang;

import Jama.Matrix;

public class Equation {
    public final double[][] A;
    public final double[] b;

    public Equation(double[][] A, double[] b) {
        this.A = A;
        this.b = b;
    }

    public Equation(Matrix A, Matrix b){
        if (A.getRowDimension() != A.getColumnDimension() || A.getRowDimension() != b.getRowDimension() || b.getColumnDimension() != 1)
            throw new IllegalArgumentException("Given matrices have inappropriate dimensions");

        this.A = A.getArray();
        this.b = b.getColumnPackedCopy();
    }

    public Equation copy(){
        return new Equation(new Matrix(A).copy().getArray(), new Matrix(b, b.length).copy().getColumnPackedCopy());
    }

    public int size() {
        return b.length;
    }
}
