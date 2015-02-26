package com.company;

import com.company.lang.SolutionHandlers;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, InvalidFormatException {
        double[][] A = new double[][]{{2., 1.}, {1., 2.}};
		double[] b = new double[]{5., 4.};

        SolutionHandlers.showConvergence(A, b, 1e-5, -1);
	}

}
