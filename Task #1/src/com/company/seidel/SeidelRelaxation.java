package com.company.seidel;

import Jama.Matrix;
import com.company.lang.ISolverIterative;


public class SeidelRelaxation extends ISolverIterative{

    private int n;
    private double posterioriEps;

    private Matrix x;
    private Matrix next;

    private double relaxation;

    public SeidelRelaxation(double[][] A, double[] b, double eps, int maxIterationsNum, double relaxationParameter) {
        super(A, b, eps, maxIterationsNum);
        n = this.A.getRowDimension();
        initPosterioriEstimation();

        x = this.b;
        next = new Matrix(new double[n], n);

        relaxation = relaxationParameter;
    }

    protected void initPosterioriEstimation() {
        // aposterior estimation here differs from Jacobi method
        Matrix matrixFirst = new Matrix(n, n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                matrixFirst.set(i, j, -A.get(i, j) / A.get(i, i));
            }
        }

        Matrix matrixSecond = new Matrix(n, n);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                matrixSecond.set(i, j, -A.get(i, j) / A.get(i, i));
            }
        }

        double matrixFirstNorm = matrixFirst.normF();
        double matrixSecondNorm = matrixSecond.normF();
        posterioriEps = (1.0 - matrixFirstNorm) * eps / (matrixSecondNorm);
    }

    private int counter = 0;
    @Override
    protected Matrix countNext() {
        double sum = 0.0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j < i) {
                    sum += A.get(i, j) * next.get(j, 0);
                }

                if (j > i) {
                    sum += A.get(i, j) * x.get(j, 0);
                }
            }

            double value = relaxation * (b.get(i, 0) - sum) / A.get(i, i) + (1 - relaxation) * x.get(i, 0);

            next.set(i, 0, value);
            sum = 0.0;
        }

        x = next;
        ++counter;

        return x;
    }

    protected boolean isPreciousEnough(Matrix deltaX) {
        return counter >= maxIterationsNum || deltaX.normF() < posterioriEps;

    }
}
