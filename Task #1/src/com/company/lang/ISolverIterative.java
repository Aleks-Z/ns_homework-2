package com.company.lang;

public abstract class ISolverIterative extends ISolver {

    protected final double eps;
    protected final int maxIterationsNum;

    public ISolverIterative(double[][] A, double[] b, double eps, int maxIterationsNum) {
        super(A, b);
        this.eps = eps;
        this.maxIterationsNum = maxIterationsNum;
    }

}
