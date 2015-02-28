package com.company.testCases;

import com.company.ISolverTest;
import com.company.lang.EquationFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class GoodConditionedTest extends ISolverTest {
    public GoodConditionedTest(double[][] A, double[] b) {
        super(A, b);
    }

    private static final Function<Integer, Object[]> producer = asObject.compose(EquationFactory.GoodConditioned);

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();
        for (int t = 0; t < 100; t++) {
            params.add(producer.apply(10));
        }
        return params;
    }

}