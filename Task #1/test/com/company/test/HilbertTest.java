package com.company.test;

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
        for (int t = 0; t < 10; t++) {
            double[][] A = new double[maxSize][maxSize];
            double[] b = generateRandomVector(maxSize);

            for (int i = 0; i < maxSize; i++) {
                for (int j = 0; j < maxSize; j++) {
                    A[i][j] = 1.0 / (1 + i + j);
                }
            }
            params.add(new Object[]{A, b});
        }
        return params;
    }

}