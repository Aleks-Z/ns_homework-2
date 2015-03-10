package com.company;

import Jama.Matrix;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.Equation;
import com.company.lang.ISolver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

public abstract class ISolverTest extends Assert {

    private static final double precision = 0.001;

    private Equation equation;

    public ISolverTest(Equation equation) {
        this.equation = equation;
    }

    /**
     * Prints message and fails this test case
     *
     * @param s - message
     */
    private static void printErrorMessage(String s) {
        throw new AssertionError(s);
    }

    /**
     * Gets solution for equality provided with {@code ISolverTestedClass.testedClass} class
     */
    private static double[] solve(Equation equation) {
        ISolver testedSolver = ISolverTestedClass.createTestedSolver(equation, precision);
        try {
            return testedSolver.solve();
        } catch (ISolver.SolverException e) {
            printErrorMessage(e.getMessage());
        }
        return null;
    }

    /**
     * For this.A and this.b compares solution provided with {@code ISolverTestedClass.testedClass} class
     * with etalon one and asserts difference suits to necessary precision
     */
    @Test
    public void check() {
        double[] x = solve(equation);
        double[] x_etalon = new JamaSolver(equation.A, equation.b).solve();
        if (!(new Matrix(x_etalon, x_etalon.length).minus(new Matrix(x, x.length)).normInf() < precision)) {
            if (equation.size() < 20)
                printErrorMessage("Wrong answer \nFor test\n<-- A -->\n" + matrixToString(equation.A) + "\n<-- b -->\n" + Arrays.toString(equation.b) + "\n<- Result ->\nExpected:  " + Arrays.toString(x_etalon) + "\nGained:    " + Arrays.toString(x));
            else
                printErrorMessage("Wrong answer \nOn matrix of size " + equation.size() + " \nAnswer difference: " + new Matrix(x_etalon, x_etalon.length).minus(new Matrix(x, x.length)).normInf() + "\n");
        }

    }

    /**
     * toString for matrix
     */
    public static String matrixToString(double[][] a) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            s.append("[[");
            for (int j = 0; j < a[i].length; j++) {
                s.append(a[i][j]);
                if (j != a[i].length - 1) s.append(", ");
            }
            s.append("]]");
            if (i != a.length - 1) s.append("\n");
        }
        return s.toString();
    }

    protected static final Function<Equation, Object[]> asObjectArray = (Equation eq) -> new Object[]{eq};

}