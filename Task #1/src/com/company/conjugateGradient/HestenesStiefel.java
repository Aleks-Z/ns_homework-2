package com.company.conjugateGradient;

import Jama.Matrix;

public class HestenesStiefel extends ConjugateGradient {

    public HestenesStiefel(double[][] A, double[] b) {
        super(A, b);
    }

    @Override
    protected double countStep(Matrix prev_r, Matrix r, Matrix p) {
        return -scalarMultiply(r, r.minus(prev_r)) / scalarMultiply(p, r.minus(prev_r));
    }
}
