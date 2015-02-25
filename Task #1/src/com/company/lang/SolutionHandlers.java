package com.company.lang;

import Jama.Matrix;
import com.company.conjugateGradient.FletcherReeves;
import com.company.gauss.GaussSolver;
import com.company.jacobi.Jacobi;
import com.company.jamaSolver.JamaSolver;
import com.company.seidel.Seidel;
import com.company.seidel.SeidelRelaxation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.function.Function;

public class SolutionHandlers {
    private static ISolver[] constructAllSolutions(double[][] A, double[] b, double eps, int maxIterationsNum) {
        return new ISolver[]{
                new FletcherReeves(A, b),
                new GaussSolver(A, b),
                new Jacobi(A, b, eps, maxIterationsNum),
                new Seidel(A, b, eps, maxIterationsNum),
                new SeidelRelaxation(A, b, eps, maxIterationsNum, 0.5),
                new SeidelRelaxation(A, b, eps, maxIterationsNum, 1.5)
        };
    }

    /**
     * Prints to Result.xlsx some data and display diagram which shows convergence speed.
     * In case of correct solution, solver's sequence of x will end with log10(eps).
     * @param componentNum number of printed component of solution vector.
     *                     If set to -1, norm is printed instead.
     */
    public static void showConvergence(double[][] A, double[] b, double eps, int componentNum) throws IOException {
        if (componentNum < -1 || componentNum > b.length) throw new RuntimeException("Wrong component requested");

        // now lets write some solution to .xlsx file
        File inFile = new File("Template.xlsx");      // contains diagram, using it as basement
        File outFile = new File("Results.xlsx");
        int maxIterationsNum = 1000;
        Function<double[], Double> toAns = componentNum == -1 ?            // returns specified component of x / norm of x when componentNum == -1
                (double[] x) -> new Matrix(x, x.length).normInf() :
                (double[] x) -> x[componentNum];

        // initialize solvers
        ISolver[] solvers = constructAllSolutions(A, b, eps, maxIterationsNum);
        double etalon_ans = toAns.apply(new JamaSolver(A, b).solve());

        Workbook workbook;
        try (InputStream in = new BufferedInputStream(new FileInputStream(inFile))) {
//            read from template file
            workbook = new SXSSFWorkbook(new XSSFWorkbook(in), maxIterationsNum);
            Sheet sheet = workbook.getSheetAt(0);

//            fill table header with solvers' names
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("x");
            for (int i = 0; i < solvers.length; i++) {
                firstRow.createCell(i + 1).setCellValue(solvers[i].getClass().getSimpleName());
            }

//            insert values (into file copy which is stored into memory)
            for (int i = 0; i < solvers.length; i++) {
                int rowNum = 1;
                for (double[] x : solvers[i]) {
                    Row row = sheet.getLastRowNum() < rowNum ? sheet.createRow(rowNum++) : sheet.getRow(rowNum++);
                    row.createCell(i + 1).setCellValue(Math.log10(Math.max(Math.abs(toAns.apply(x) - etalon_ans), eps)));
                }
            }
        }
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
//            write to result file
            workbook.write(out);
            out.close();
        }
    }


    public static void writeSolve(double[][] A, double[] b) {
        String FILE_SOLVERS_NAME = "Result2.xls";
        double eps = 1e-10;
        int maxIteration = (int) 10e2;

        ISolver[] solvers = SolutionHandlers.constructAllSolutions(A, b, eps, maxIteration);

        try (FileOutputStream out = new FileOutputStream(FILE_SOLVERS_NAME)) {
            Workbook workbook = new HSSFWorkbook();
            for (ISolver solver : solvers)
                workbook.createSheet(solver.getClass().getSimpleName());
            for (int i = 0, size = solvers.length; i < size; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                ISolver solver = solvers[i];
				/*if (solver instanceof ISolverIterative) {
		           //TODO Write All x from iterable methods
				}*/
                double[] doubles = solver.solve();
                for (int j = 0, l = doubles.length; j < l; j++)
                    sheet.createRow(j).createCell(0).setCellValue(doubles[j]);
            }

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
