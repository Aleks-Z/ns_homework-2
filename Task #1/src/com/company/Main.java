package com.company;

import com.company.lang.Equation;
import com.company.lang.EquationFactory;
import com.company.lang.SolutionHandlers;
import com.company.lang.TemplateFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {
//		101 way to initialize equation, chapter 0

//      int n = 10;
//		Equation equation = new Equation(Matrix.random(n, n), Matrix.random(n, 1));
        // does the same as:
//		Equation equation = EquationFactory.Random.apply(10);

//		Equation equation = new Equation(new double[][]{{2., 1.}, {1., 2.}}, new double[]{5., 4.});

        Equation equation = EquationFactory.GoodConditioned.apply(10);


//		<--  Examples of printing to table  -->
//		Check Results.xlsx after launch

		SolutionHandlers.showConvergence(equation, 1e-5, -1, TemplateFormat.ConvergenceManyComparisonShort, SolutionHandlers::constructAllSolutions);
//		shows convergence for all solutions (up to 10 actually) of SolutionHandlers.constructAllSolutions(), up to 50 iterations, uses
//		    ConvergenceManyComparisonShort template .xlsx file

//		SolutionHandlers.showConvergence(equation, 1e-5, -1, TemplateFormat.ConvergenceSingleVeryLong,
//				(Equation eq, Double eps) -> new ISolver[]{new Seidel(eq.A, eq.b, eps, Integer.MAX_VALUE)});
//		shows convergence for Seidel, do up to 1000 iterations, uses ConvergenceSingleVeryLong template .xlsx file

//        SolutionHandlers.showIterationsNum(EquationFactory.Random, 100, 1e-5, true, 10, SolutionHandlers::constructAllSolutions);
//		shows number of iterations, on input data of various sizes (no more than 100, sizes grow exponentially),
// 			for all solutions in SolutionHandlers.constructAllSolutions, gets average between 10 launches
    }

}
