package com.company.testCases;

import com.company.ISolverTest;
import com.company.lang.EquationFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class RandomTest extends ISolverTest {
    public RandomTest(double[][] A, double[] b) {
        super(A, b);
    }

    private static final Function<Integer, Object[]> producer = asObject.compose(EquationFactory.Random);

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Random r = new Random();
        ArrayList<Object[]> params = new ArrayList<>();
        for (int t = 0; t < 200; t++) {
            params.add(producer.apply(r.nextInt(100) + 1));
        }
        return params;
    }

}