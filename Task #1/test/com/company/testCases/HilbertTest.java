package com.company.testCases;

import Jama.Matrix;
import com.company.ISolverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HilbertTest extends ISolverTest {
    public HilbertTest(double[][] A, double[] b) {
        super(A, b);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();
        for (int n = 1; n < 10; n++) {
            double[][] A = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = 1. / (i + j + 1);
                }
            }
            params.add(new Object[]{A, Matrix.random(1, n).getArray()[0]});
        }
        return params;
    }

}