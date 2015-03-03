package com.company.jamaSolver;

import Jama.Matrix;
import com.company.lang.ISolverNonIterative;

public class JamaSolver extends ISolverNonIterative {
    public JamaSolver(double[][] A, double[] b) {
        super(A, b);
    }

    @Override
    protected Matrix count() {
        return A.solve(b);
    }

    @Override
    public String getName() {
        return "Etalon";
    }

}
