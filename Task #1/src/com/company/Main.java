package com.company;

import Jama.Matrix;
import com.company.lang.SolutionHandlers;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, InvalidFormatException {
//		101 way to initialize equality, chapter 0
        int n = 10;
		double[][] A = Matrix.random(n, n).getArray();
		double[] b = Matrix.random(n, 1).getRowPackedCopy();

//		double[][] A = new double[][]{{2., 1.}, {1., 2.}};
//		double[] b = new double[]{5., 4.};

//		for using this you may need to mark "test" folder as sources root (Right click on "test" -> Mark directory as)
//		Object[] equality = GoodConditionedTest.data().iterator().next();
//		double[][] A = (double[][])equality[0];
//		double[] b = (double[])equality[1];


//		<--  Examples of printing to table  -->
//		Check Results.xlsx after launch

//		SolutionHandlers.showConvergence(A, b, 1e-5, -1, SolutionHandlers.TemplateFormat.ConvergenceSingleVeryLong);
//		shows convergence for first solution of SolutionHandlers.constructAllSolutions(), up to 1000 iterations

//		SolutionHandlers.showConvergence(A, b, 1e-5, -1, SolutionHandlers.TemplateFormat.ConvergenceManyComparisonShort);
//		shows convergence for all solutions (up to 10 actually) of SolutionHandlers.constructAllSolutions(), up to 50 iterations

		SolutionHandlers.showIterationsNum(20, 1e-5, true, 10);
//		shows number of iterations, on input data of various sizes, for all solutions (meaning of parameters see in documentation to this method)
	}

}
