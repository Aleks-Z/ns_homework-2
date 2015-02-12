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
        for (int t = 0; t < 10; t++) {
            int n = defaultMaxSize;
            params.add(new Object[]{Matrix.random(n, n).getArray(), Matrix.random(1, n).getArray()[0]});
        }
        return params;
    }

}