package com.company.seidel;


public class Seidel extends SeidelRelaxation {

    public Seidel(double[][] A, double[] b, double eps, int maxIterationsNum) {
        super(A, b, eps, maxIterationsNum, 1);
    }
}


