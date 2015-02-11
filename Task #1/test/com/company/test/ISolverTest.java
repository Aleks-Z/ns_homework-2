package com.company.test;

import Jama.Matrix;
import com.company.jamaSolver.JamaSolver;
import com.company.lang.ISolver;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class ISolverTest extends Assert {
    // TODO: select your class here
    private static final Class<? extends ISolver> testingClass = JamaSolver.class;

    private static final double precision = 1e-3;
    private static final int maxSize = 10;

    @Test
    public void basicTest() {
        check(new double[][]{{1., 2.}, {2., 1.}}, new double[]{5., 4.});
    }

    @Test
    public void randomTest() {
        Random r = new Random();
        for (int t = 0; t < 10; t++) {
            int n = r.nextInt(maxSize) + 1;
            double[][] A = new double[n][n];
            for (int i = 0; i < n; i++) {
                A[i] = generateRandomVector(n);
            }
            double[] b = generateRandomVector(n);
            check(A, b);
        }
    }

    @Test
    public void hilbertTest() {
        for (int t = 0; t < 10; t++) {
            double[][] A = new double[maxSize][maxSize];
            double[] b = generateRandomVector(maxSize);

            for (int i = 0; i < maxSize; i++) {
                for (int j = 0; j < maxSize; j++) {
                    A[i][j] = 1.0 / (1 + i + j);
                }
            }
            check(A, b);
        }
    }


    void printErrorMessage(String s) {
        System.out.println(s);
        assertTrue(false);
    }

    private double[] solve(double[][] A, double[] b) {
        ISolver solver = null;
        try {
            try {
                Constructor<? extends ISolver> constructor = testingClass.getConstructor(double.class);
                solver = constructor.newInstance(precision);
            } catch (NoSuchMethodException e) {
                try {
                    Constructor<? extends ISolver> constructor = testingClass.getConstructor();
                    solver = constructor.newInstance();
                } catch (NoSuchMethodException e1) {
                    printErrorMessage("No appropriate constructor found");
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert solver != null;

        double[] result = solver.solve(A, b);
        if (result.length != b.length) {
            printErrorMessage("Wrong vector length");
        }

        return result;
    }

    private void check(double[][] A, double[] b) {
        double[] x = solve(A, b);
        double[] b2 = new Matrix(A).times(new Matrix(new double[][]{x}).transpose()).transpose().getArray()[0];
        for (int i = 0; i < b.length; i++) {
            if (Math.abs(b[i] - b2[i]) > precision) {
                printErrorMessage("Wrong answer \nFor test:\n" + matrixToString(A) + "\n\n" + Arrays.toString(b)
                        + "\nExpected: " + Arrays.toString(new JamaSolver().solve(A, b))
                        + "\nGained: " + Arrays.toString(x));
            }
        }
    }

    double[] generateRandomVector(int n) {
        Random r = new Random();
        double[] v = new double[n];
        for (int i = 0; i < n; i++) {
            v[i] = r.nextDouble();
        }
        return v;
    }

    private String matrixToString(double[][] a) {
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