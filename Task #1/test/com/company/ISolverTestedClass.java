package com.company;

import com.company.conjugateGradient.PolakRibiere;
import com.company.lang.ISolver;

public class ISolverTestedClass {
    // TODO: construct your solver here
    public static ISolver createTestedSolver(double[][] A, double[] b, double eps){
        return new PolakRibiere(A, b);
    }
}
