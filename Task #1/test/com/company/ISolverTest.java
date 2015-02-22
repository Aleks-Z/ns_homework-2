package com.company;

import Jama.Matrix;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.ISolver;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public abstract class ISolverTest extends Assert {

    private static final double precision = 0.001;
    private static final int maxIterationsNum = 1000;

    private double[][] A;
    private double[] b;

    public ISolverTest(double[][] A, double[] b) {
        this.A = A;
        this.b = b;
    }

    /**
     * Prints message and fails this test case
     *
     * @param s - message
     */
    private static void printErrorMessage(String s) {
        System.err.println(s);
        System.err.println();
        assertTrue(false);
    }

    /**
     * Gets solution for equality provided with {@code ISolverTestedClass.testedClass} class
     */
    private static double[] solve(double[][] A, double[] b) {
        Class<? extends ISolver> testedClass = ISolverTestedClass.testedClass;
        ISolver solver = null;

        // constructor selection
        try {
            try {
                // attempt to call <init>(A, b, eps, maxIterationsNum)
                solver = testedClass.getConstructor(double[][].class, double[].class, double.class, int.class).newInstance(A, b, precision, maxIterationsNum);
            } catch (NoSuchMethodException e) {
                try {
                    // attempt to call <init>(A, b)
                    solver = testedClass.getConstructor(double[][].class, double[].class).newInstance(A, b);
                } catch (NoSuchMethodException e1) {
                    printErrorMessage("No appropriate constructor found in class " + testedClass.getName() + "; <init>(double[][], double[]) or <init>(double[][], double[], double, int) required");
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert solver != null;

        // launch solution
        try {
            return solver.solve();
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
        double[] x = solve(A, b);
        double[] x_etalon = new JamaSolver(A, b).solve();
        if (!(new Matrix(x_etalon, x_etalon.length).minus(new Matrix(x, x.length)).normInf() < precision)) {
            printErrorMessage("Wrong answer \nFor test:\nA:\n" + matrixToString(A) + "\nb:\n" + Arrays.toString(b) + "\nExpected: " + Arrays.toString(x_etalon) + "\nGained: " + Arrays.toString(x));
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

}