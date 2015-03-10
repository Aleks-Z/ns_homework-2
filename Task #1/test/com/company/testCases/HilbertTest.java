package com.company.testCases;

import com.company.ISolverTest;
import com.company.lang.Equation;
import com.company.lang.EquationFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class HilbertTest extends ISolverTest {

    private static final Function<Integer, Object[]> producer = asObjectArray.compose(EquationFactory.Hilbert);

    public HilbertTest(Equation equation) {
        super(equation);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();
        for (int n = 1; n < 2; n++) {
            params.add(producer.apply(n));
        }
        return params;
    }

}