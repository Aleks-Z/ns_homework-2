package com.company;

import com.company.jamaSolver.JamaSolver;
import com.company.lang.ISolver;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public abstract class ISolverTest extends Assert {
    private static final double precision = 1e-3;
    protected static final int defaultMaxSize = 10;

    private double[][] A;
    private double[] b;

    public ISolverTest(double[][] A, double[] b) {
        this.A = A;
        this.b = b;
    }

    private static void printErrorMessage(String s) {
        System.err.println(s);
        System.err.println();
        assertTrue(false);
    }

    private static double[] solve(double[][] A, double[] b) {
        Class<? extends ISolver> testedClass = ISolverTestedClass.testedClass;
        ISolver solver = null;
        // constructor selection
        try {
            try {
                solver = testedClass.getConstructor(double.class).newInstance(precision);
            } catch (NoSuchMethodException e) {
                try {
                    solver = testedClass.getConstructor().newInstance();
                } catch (NoSuchMethodException e1) {
                    printErrorMessage("No appropriate constructor found in class " + testedClass.getName() + "; <init>() or <init>(double) required");
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert solver != null;

        // launch solution
        try {
            return solver.solve(A, b);
        } catch (ISolver.SolverException e){
            printErrorMessage(e.getMessage());
        }
        return null;
    }

    @Test
    public void check() {
        double[] x = solve(A, b);
        double[] x_etalon = new JamaSolver().solve(A, b); //
//        double[] b2 = new Matrix(A).times(new Matrix(new double[][]{x}).transpose()).transpose().getArray()[0];           // b2 = A*x

        for (int i = 0; i < b.length; i++) {
            if (Math.abs(x[i] - x_etalon[i]) > precision) {
                printErrorMessage("Wrong answer \nFor test:\nA:\n" + matrixToString(A) + "\nb:\n" + Arrays.toString(b)
                        + "\nExpected: " + Arrays.toString(x_etalon)
                        + "\nGained: " + Arrays.toString(x));
            }
        }
    }

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