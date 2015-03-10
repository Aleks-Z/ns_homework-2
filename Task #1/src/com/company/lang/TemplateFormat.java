package com.company.lang;

/**
 * Keeps data about available template .xlsx files.
 * Each template has its own amount of solutions / versions of x which will be grabbed by diagram,
 * so this information is stored along with pathname of corresponding file.
 */
public enum TemplateFormat {
    ConvergenceManyComparisonShort("convergence/cmp_many_short", 10, 50),               // many solvers,  few x versions
    ConvergenceManyComparisonLong("convergence/cmp_many_long", 10, 1000),               // many solvers,  many x versions
    ConvergenceTwoComparison("convergence/cmp_two", 2, 1000),                            // two solvers,   many x versions
    ConvergenceSingle("convergence/single", 1, 1000),                                    // single solver, many x versions
    ConvergenceSingleVeryLong("convergence/single_very_large", 1, 10000),                // single solver, huge amount of x versions

    CountIterations("count_iterations", 10, 20);


    public final String filePath;
    public final int displayedSolversNum;
    public final int displayedRowsNum;

    TemplateFormat(String fileName, int displayedSolversNum, int displayedRowsNum) {
        this.filePath = "table_templates/" + fileName + ".xlsx";
        this.displayedSolversNum = displayedSolversNum;
        this.displayedRowsNum = displayedRowsNum;
    }
}
