package com.company.conjugateGradient;

import Jama.Matrix;
import com.company.lang.ISolver;

public abstract class ConjugateGradient extends ISolver {
    private Matrix x, r, p;

    private final int n;
    private int i;

    public ConjugateGradient(double[][] A, double[] b) {
        super(new Matrix(A).transpose().times(new Matrix(A)).getArray(), new Matrix(A).transpose().times(new Matrix(b, b.length)).getColumnPackedCopy());
        n = b.length;
        x = new Matrix(new double[n], n);
        r = this.b.minus(this.A.times(x));
        p = r;
    }

    @Override
    protected final Matrix countNext() {
        double alpha = scalarMultiply(r, r) / scalarMultiply(A.times(p), p);
        x = (x.plus(p.times(alpha)));
        Matrix prev_r = r;
        r = r.minus(A.times(p).times(alpha));
        p = r.plus(p.times(countStep(prev_r, r, p)));
        i++;
        return x;
    }

    @Override
    protected boolean isPreciousEnough(Matrix deltaX) {
        return /*i >= (Math.log10(n) + 1) * (n + 1) + 1 */ i >= (n + 1) * (n + 1) ||  p.normInf() < 1e-15;
    }

    protected static double scalarMultiply(Matrix a, Matrix b) {
        return a.transpose().times(b).get(0, 0);
    }

    protected abstract double countStep(Matrix prev_r, Matrix r, Matrix p);
}
