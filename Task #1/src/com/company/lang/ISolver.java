package com.company.lang;

import Jama.Matrix;

public abstract class ISolver {

    public final double[] solve(double[][] a, double[] b) {
        Matrix A = new Matrix(a), B = new Matrix(new double[][]{b}).transpose();
        if (
                A.getColumnDimension() != A.getRowDimension()
                        || A.getColumnDimension() != B.getRowDimension()
                        || B.getColumnDimension() != 1
                )
            throw new ParamsException("Invalid parameters' dimensions\n");

        Matrix result = solve(A, B);

        if (result.getColumnDimension() != 1 || result.getRowDimension() != A.getColumnDimension())
            throw new SolverException("Illegal return value of method \"solve\" \n\tdimensions expected: " + b.length + " x 1 \n\tgained: " + result.getRowDimension() + " x " + result.getColumnDimension() + "\n");
        return result.transpose().getArray()[0];
    }

    protected abstract Matrix solve(Matrix A, Matrix b);


    public class SolverException extends RuntimeException {
        public SolverException(String message) {
            super(message);
        }
    }


    public class ParamsException extends RuntimeException {
        public ParamsException(String message) {
            super(message);
        }
    }
}
