package com.company.testCases;

import com.company.ISolverTest;
import com.company.lang.EquationFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class HilbertTest extends ISolverTest {
    public HilbertTest(double[][] A, double[] b) {
        super(A, b);
    }

    private static final Function<Integer, Object[]> producer = asObject.compose(EquationFactory.Hilbert);

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();
        for (int n = 1; n < 10; n++) {
            params.add(producer.apply(n));
        }
        return params;
    }

}