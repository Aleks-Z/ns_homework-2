package com.company.lang;

import Jama.Matrix;

/**
 * ConvergenceSingle-iteration solutions
 */
public abstract class ISolverNonIterative extends ISolver {

    public ISolverNonIterative(double[][] A, double[] b) {
        super(A, b);
    }

    private boolean counted = false;

    /**
     * Calculates x
     */
    protected abstract Matrix count();

    @Override
    protected final Matrix countNext() {
        counted = true;
        return count();
    }

    @Override
    protected final boolean isPreciousEnough(Matrix m) {
        return counted;
    }
}
