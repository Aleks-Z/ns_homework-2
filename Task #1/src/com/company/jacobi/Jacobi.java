package com.company.jacobi;

import Jama.Matrix;
import com.company.lang.ISolverIterative;

public class Jacobi extends ISolverIterative {
    private Matrix alpha;
    private Matrix beta;
    private int n;

    double alphaNorm;
    private double posterioriEps;

    private Matrix x;
    private Matrix next = new Matrix(new double[n], n);

    public Jacobi(double[][] A, double[] b, double eps, int maxIterationsNum) {
        super(A, b, eps, maxIterationsNum);
        initNewParams();
        initPosterioriEstimation();

        x = this.b;
    }

    private void initNewParams() {
        n = A.getRowDimension();

        alpha = new Matrix(n, n);
        beta = new Matrix(new double[n], n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    alpha.set(i, j, -A.get(i, j) / A.get(i, i));
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            beta.set(i, 0, b.get(i, 0) / A.get(i, i));
        }
    }

    protected void initPosterioriEstimation() {
        alphaNorm = alpha.normF();
        posterioriEps = (1.0 - alphaNorm) * eps / (alphaNorm);
    }

    private int counter = 0;
    @Override
    protected Matrix countNext() {
        if (alphaNorm >= 1) {
            throw new RuntimeException("Input matrix is ill-conditioned.");
        }

        next = alpha.times(x).plus(beta);
        x = next;
        ++counter;

        return x;
    }

    protected boolean isPreciousEnough(Matrix deltaX) {
        if (counter >= maxIterationsNum) {
            return true;
        }

        if (deltaX.normF() < posterioriEps) {
//            return true;
        }

        return false;
    }
}
