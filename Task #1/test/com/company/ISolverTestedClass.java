package com.company;

import com.company.jacobi.Jacobi;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.Equation;
import com.company.lang.ISolver;
import com.company.seidel.Seidel;

public class ISolverTestedClass {
    // TODO: construct your solver here
    public static ISolver createTestedSolver(Equation equation, double eps){
        // return new JamaSolver(equation.A, equation.b);
        // return new GaussSolver(equation.A, equation.b);
        // return new Seidel(equation.A, equation.b, eps, 100);
        return new Jacobi(equation.A, equation.b, eps, 100);
    }
}
