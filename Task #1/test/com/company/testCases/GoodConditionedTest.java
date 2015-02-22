package com.company.testCases;

import Jama.Matrix;
import com.company.ISolverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GoodConditionedTest extends ISolverTest {
    public GoodConditionedTest(double[][] A, double[] b) {
        super(A, b);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();
        for (int t = 0; t < 10; t++) {
            int n = 10;

            Matrix matrix = Matrix.random(n, n);
            for (int i = 0; i < n; ++i) {
                double sum = 0.0;
                for (int j = 0; j < n; ++j) {
                    if (i != j) {
                        sum += Math.abs(matrix.get(i, j));
                    }
                }
                matrix.set(i, i, 2 * sum + 0.1);
            }

            params.add(new Object[]{matrix.getArray(), Matrix.random(1, n).getArray()[0]});
        }
        return params;
    }

}