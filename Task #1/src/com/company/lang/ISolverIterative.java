package com.company.lang;

import Jama.Matrix;

public abstract class ISolverIterative extends ISolver {

    private final double posterioriEps;
    private final double eps;

    public ISolverIterative(double[][] A, double[] b, double eps) {
        super(A, b);
        posterioriEps = getPosterioriEstimation();
        this.eps = eps;
    }

    @Override
    protected boolean isPreciousEnough(Matrix deltaX) {
        return deltaX.normInf() > posterioriEps;
    }

    protected abstract double getPosterioriEstimation();
}
