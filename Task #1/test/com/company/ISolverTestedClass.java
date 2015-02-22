package com.company;

import com.company.jacobi.Jacobi;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.ISolver;
import com.company.seidel.Seidel;

public class ISolverTestedClass {
    // TODO: select your class here
    public static final Class<? extends ISolver> testedClass = Seidel.class;
}
