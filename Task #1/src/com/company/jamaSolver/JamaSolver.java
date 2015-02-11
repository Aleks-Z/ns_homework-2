package com.company.jamaSolver;

import Jama.Matrix;
import com.company.lang.ISolver;

public class JamaSolver extends ISolver {
    @Override
    protected Matrix solve(Matrix A, Matrix B) {
        return A.solve(B);
    }
}
