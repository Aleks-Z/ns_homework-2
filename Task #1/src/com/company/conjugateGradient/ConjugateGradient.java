package com.company.conjugateGradient;

import Jama.Matrix;
import com.company.lang.ISolver;

public abstract class ConjugateGradient extends ISolver {
    @Override
    protected final Matrix solve(Matrix A, Matrix b) {
        b = A.transpose().times(b);
        A = A.transpose().times(A);
        int n = b.getRowDimension();

        Matrix x = new Matrix(new double[n], n);
        Matrix r = b.minus(A.times(x));
        Matrix p = r;
        for (int i = 1; i <= n + 1; i++) {
            double alpha = scalarMultiply(r, r) / scalarMultiply(A.times(p), p);
            x = (x.plus(p.times(alpha)));
            Matrix prev_r = r;
            r = r.minus(A.times(p).times(alpha));
            p = r.plus(p.times(countStep(prev_r, r, p)));
        }
        return x;
    }

    protected static double scalarMultiply(Matrix a, Matrix b) {
        return a.transpose().times(b).get(0, 0);
    }

    protected abstract double countStep(Matrix prev_r, Matrix r, Matrix p);
}
