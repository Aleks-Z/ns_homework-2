package com.company.lang;

import Jama.Matrix;

public abstract class ISolver {
	public final double[] solve(double[][] a, double[] b) {
		Matrix A = new Matrix(a), B = new Matrix(new double[][]{b}).transpose();
		if (
				A.getColumnDimension() != A.getRowDimension()
						|| A.getColumnDimension() != B.getRowDimension()
						|| B.getColumnDimension() != 1
				)
			throw new SolverException();

		Matrix result = solve(A, B);

		if (result.getColumnDimension() != 1 || result.getRowDimension() != A.getColumnDimension())
			throw new SolverException();
		return result.transpose().getArray()[0];
	}

	protected abstract Matrix solve(Matrix A, Matrix B);

	class SolverException extends RuntimeException {

	}
}
