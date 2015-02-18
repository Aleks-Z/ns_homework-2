package com.company.conjugateGradient;

import Jama.Matrix;

public class DaiYuan extends ConjugateGradient {

    public DaiYuan(double[][] A, double[] b, double eps) {
        super(A, b, eps);
    }

    @Override
    protected double countStep(Matrix prev_r, Matrix r, Matrix p) {
        return -scalarMultiply(r, r) / scalarMultiply(p, r.minus(prev_r));
    }
}
