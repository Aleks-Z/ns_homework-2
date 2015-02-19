package com.company.lang;

import sun.plugin.dom.core.Document;

public class SolutionProcessing {
    /**
     * Gets solution
     */
    public static double[] solve(ISolver solution) {
        // iterate through x virtual collection and returns last element
        double[] ans = null;
        for (double[] x : solution) {
            ans = x;
        }
        return ans;
    }

    /**
     * Writes all versions of x into Excel table.
     * In case of number of iterations is too large, stops somewhere.
     */
    public static void writeToXls(ISolver solution, Document doc){
        // TODO: write something here
    }

    /**
     * Counts number of versions of x till accurate one.
     */
    public static int countIterations(ISolver solution){
        int i = 0;
        for (double[] x : solution) {
            i++;
        }
        return i;
    }
}
