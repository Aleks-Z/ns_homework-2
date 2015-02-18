package com.company.lang;

import Jama.Matrix;

public abstract class ISolverNonIterative extends ISolver {

    public ISolverNonIterative(double[][] A, double[] b) {
        super(A, b);
    }

    private boolean counted = false;

    protected abstract Matrix count();

    @Override
    protected Matrix countNext() {
        counted = true;
        return count();
    }

    @Override
    protected boolean isPreciousEnough(Matrix m) {
        return counted;
    }
}
