package com.company.lang;

import Jama.Matrix;

/**
 * Created by asus on 18.02.2015.
 */
public abstract class ISolverIterative extends ISolver {

    private final double posterioriEps;

    public ISolverIterative(double[][] A, double[] b, double eps) {
        super(A, b, eps);
        posterioriEps = getPosterioriEstimation();
    }

    @Override
    protected boolean isPreciousEnoungh(Matrix deltaX) {
        return deltaX.normF() > posterioriEps;
    }

    protected abstract double getPosterioriEstimation();
}
