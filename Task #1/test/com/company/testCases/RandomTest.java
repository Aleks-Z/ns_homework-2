package com.company.testCases;

import Jama.Matrix;
import com.company.ISolverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class RandomTest extends ISolverTest {
    public RandomTest(double[][] A, double[] b) {
        super(A, b);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Random r = new Random();
        ArrayList<Object[]> params = new ArrayList<>();
        for (int t = 0; t < 10; t++) {
            int n = r.nextInt(100) + 1;
            params.add(new Object[]{Matrix.random(n, n).getArray(), Matrix.random(1, n).getArray()[0]});
        }
        return params;
    }

}