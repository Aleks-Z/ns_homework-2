package com.company.lang;

import Jama.Matrix;

/**
 * Created by asus on 18.02.2015.
 */
public abstract class ISolverNonIterative extends ISolver {

    public ISolverNonIterative(double[][] A, double[] b) {
        super(A, b, 0.0);
    }

    private boolean counted = false;

    protected abstract Matrix count();

    @Override
    protected Matrix countNext() {
        counted = true;
        return count();
    }

    @Override
    protected boolean isPreciousEnoungh(Matrix m) {
        return counted;
    }
}
