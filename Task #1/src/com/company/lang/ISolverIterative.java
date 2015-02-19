package com.company.lang;

public abstract class ISolverIterative extends ISolver {

    protected final double eps;

    public ISolverIterative(double[][] A, double[] b, double eps) {
        super(A, b);
        this.eps = eps;
    }

}
