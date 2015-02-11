package com.company;

import Jama.Matrix;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.ISolver;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public abstract class ISolverTest extends Assert {
    // TODO: select your class here
    private static final Class<? extends ISolver> testingClass = JamaSolver.class;

    private static final double precision = 1e-3;
    protected static final int defaultMaxSize = 10;

    private double[][] A;
    private double[] b;

    public ISolverTest(double[][] A, double[] b) {
        this.A = A;
        this.b = b;
    }

    private static void printErrorMessage(String s) {
        System.out.println(s);
        assertTrue(false);
    }

    private static double[] solve(double[][] A, double[] b) {
        ISolver solver = null;
        try {
            try {
                solver = testingClass.getConstructor(double.class).newInstance(precision);
            } catch (NoSuchMethodException e) {
                try {
                    solver = testingClass.getConstructor().newInstance();
                } catch (NoSuchMethodException e1) {
                    printErrorMessage("No appropriate constructor found in class " + testingClass.getName() + "; <init>() or <init>(double) required");
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert solver != null;

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
        double[] b2 = new Matrix(A).times(new Matrix(new double[][]{x}).transpose()).transpose().getArray()[0];
        for (int i = 0; i < b.length; i++) {
            if (Math.abs(b[i] - b2[i]) > precision) {
                printErrorMessage("Wrong answer \nFor test:\nA:\n" + matrixToString(A) + "\nb:\n" + Arrays.toString(b)
                        + "\nExpected: " + Arrays.toString(new JamaSolver().solve(A, b))
                        + "\nGained: " + Arrays.toString(x));
            }
        }
    }

    protected static double[] generateRandomVector(int n) {
        Random r = new Random();
        double[] v = new double[n];
        for (int i = 0; i < n; i++) {
            v[i] = r.nextDouble();
        }
        return v;
    }

    protected static String matrixToString(double[][] a) {
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