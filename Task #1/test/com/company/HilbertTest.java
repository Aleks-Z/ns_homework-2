package com.company;

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
            double[][] A = new double[defaultMaxSize][defaultMaxSize];
            double[] b = generateRandomVector(defaultMaxSize);

            for (int i = 0; i < defaultMaxSize; i++) {
                for (int j = 0; j < defaultMaxSize; j++) {
                    A[i][j] = 1.0 / (1 + i + j);
                }
            }
            params.add(new Object[]{A, b});
        }
        return params;
    }

}