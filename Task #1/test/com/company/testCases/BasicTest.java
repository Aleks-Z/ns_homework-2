package com.company.testCases;

import com.company.ISolverTest;
import com.company.lang.Equation;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BasicTest extends ISolverTest {
    public BasicTest(Equation equation) {
        super(equation);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Equation(new double[][]{{1., 2.}, {2., 1.}}, new double[]{5., 4.})},
                {new Equation(new double[][]{{2., 1.}, {1., 2.}}, new double[]{5., 4.})},
                {new Equation(new double[][]{{0.33333}}, new double[]{1.})},
                {new Equation(new double[][]{{0., 1.}, {1., 0.}}, new double[]{Math.E, Math.PI})},  // this test case establishes majority of number pi
                {new Equation(new double[][]{{1., 2., 3.}, {2., 4., 7.}, {4., 7., 9.}}, new double[]{1., 2., 3.})}
        });
    }

}