package com.company;

import com.company.jamaSolver.JamaSolver;
import com.company.lang.Equation;
import com.company.lang.ISolver;

public class ISolverTestedClass {
    // TODO: construct your solver here
    public static ISolver createTestedSolver(Equation equation, double eps){
        return new JamaSolver(equation.A, equation.b);
    }
}
