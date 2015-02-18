package com.company.conjugateGradient;

import Jama.Matrix;

public class FletcherReeves extends ConjugateGradient {

    public FletcherReeves(double[][] A, double[] b, double eps) {
        super(A, b, eps);
    }

    @Override
    protected double countStep(Matrix prev_r, Matrix r, Matrix p) {
        return scalarMultiply(r, r) / scalarMultiply(prev_r, prev_r);
    }
}
