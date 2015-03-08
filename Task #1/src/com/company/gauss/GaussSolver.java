package com.company.gauss;

import Jama.Matrix;
import com.company.lang.ISolverNonIterative;

public class GaussSolver extends ISolverNonIterative {
	private static final double EPSILON = 1e-10;

	public GaussSolver(double[][] A, double[] b) {
		super(A, b);
	}

	@Override
	protected Matrix count() {
		int N = A.getRowDimension();

		for (int p = 0; p < N; p++) {
			int max = p;
			for (int i = p + 1; i < N; i++)
				if (Math.abs(A.get(i, p)) > Math.abs(A.get(max, p)))
					max = i;

			swap(p, max);
			double t = b.get(p, 0);
			b.set(p, 0, b.get(max, 0));
			b.set(max, 0, t);

			if (Math.abs(A.get(p, p)) <= EPSILON)
				throw new RuntimeException("Matrix is singular or nearly singular");

			for (int i = p + 1; i < N; i++) {
				double alpha = A.get(i, p) / A.get(p, p);
				b.set(i, 0, b.get(i, 0) - alpha * b.get(p, 0));
				for (int j = p; j < N; j++)
					A.set(i, j, A.get(i, j) - alpha * A.get(p, j));
			}
		}

		double[] x = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++)
				sum += A.get(i, j) * x[j];
			x[i] = (b.get(i, 0) - sum) / A.get(i, i);
		}
		return new Matrix(x, x.length);
	}



	private void swap(int i1, int i2) {
		for (int i = 0; i < A.getColumnDimension(); i++) {
			double tmp = A.get(i1, i);
			A.set(i1, i, A.get(i2, i));
			A.set(i2, i, tmp);
		}
	}

	@Override
	public String getName() {
		return "Gauss";
	}
}
