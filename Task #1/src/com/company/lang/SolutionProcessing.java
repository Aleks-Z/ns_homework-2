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
    public static void writeXtoXls(ISolver solution, Document doc){
        for (double[] x : solution) {
            // TODO: write something here
        }
    }

    /**
     * Writes values of x{i+1} - x{i} into Excel table.
     * @param solution some solution
     * @param start index from which writing will be started. All x{i+1} - x{i} with less indices are skipped.
     * @param doc file where data will be written
     */
    public static void writeDeltaXtoXls(ISolver solution, int start, Document doc){
        // TODO: and something here please
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
