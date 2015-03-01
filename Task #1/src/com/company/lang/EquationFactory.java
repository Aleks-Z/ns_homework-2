package com.company.lang;

import Jama.Matrix;

import java.util.function.Function;

/**
 * Describes functions which create equation of specified size
 */
public interface EquationFactory extends Function<Integer, Equation> {
    public static final EquationFactory Random = (Integer n) -> new Equation(Matrix.random(n, n), Matrix.random(n, 1));

    public static final EquationFactory Hilbert = (Integer n) -> {
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = 1. / (i + j + 1);
            }
        }
        return new Equation(A, Matrix.random(1, n).getColumnPackedCopy());
    };

    public static final EquationFactory GoodConditioned = (Integer n) -> {
        Matrix matrix = Matrix.random(n, n);
        for (int i = 0; i < n; ++i) {
            double sum = 0.0;
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    sum += Math.abs(matrix.get(i, j));
                }
            }
            matrix.set(i, i, 2 * sum + 0.1);
        }
        return new Equation(matrix, Matrix.random(n, 1));
    };
}
