package com.company;

import Jama.Matrix;
import com.company.conjugateGradient.DaiYuan;
import com.company.conjugateGradient.FletcherReeves;
import com.company.conjugateGradient.HestenesStiefel;
import com.company.conjugateGradient.PolakRibiere;
import com.company.gauss.GaussSolver;
import com.company.jacobi.Jacobi;
import com.company.lang.ISolver;
import com.company.lang.ISolverIterative;
import com.company.seidel.Seidel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {
	public static final String FILE_SOLVERS_NAME = "Result2.xls";

	static double[][] A;
	static double[] b;

	public static void main(String[] args) throws IOException, InvalidFormatException {
		// lets write some solution to .xlsx file
		/*File inFile = new File("Template.xlsx");
		File outFile = new File("Results.xlsx");

		// initialize solver
		Matrix A = new Matrix(new double[][]{{1., 2.}, {2., 1.}});
		Matrix b = new Matrix(new double[]{5., 4.}, 2);
		ISolver solver = new DaiYuan(A.getArray(), b.getColumnPackedCopy());

		Workbook workbook;
		try (InputStream in = new BufferedInputStream(new FileInputStream(inFile)   )) {
//            read from template file
			workbook = new SXSSFWorkbook(new XSSFWorkbook(in), 100);
			Sheet sheet = workbook.getSheetAt(0);

//            insert values (into file copy which is stored into memory / temp file when necessary)
			int rowNum = 1;
			for (double[] x : solver) {
				Row row = sheet.createRow(rowNum++);
				for (int i = 0; i < x.length; i++) {
					row.createCell(i + 1).setCellValue(x[i]);
				}
			}
		}
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
//            write to result file
			workbook.write(out);
			out.close();
		}*/

		A = new double[][]{{1., 2.}, {2., 1.}};
		b = new double[]{5., 4.};
		readData();
		writeSolve();

	}

	public static void readData() {


	}

	public static void writeSolve() {
		double esp = 1e-10;
		int maxIteration = (int) 10e2;

		ISolver[] solvers = new ISolver[]{
				new DaiYuan(A, b),
				new FletcherReeves(A, b),
				new HestenesStiefel(A, b),
				new PolakRibiere(A, b),
				new GaussSolver(A, b),
				new Jacobi(A, b, esp, maxIteration),
				new Seidel(A, b, esp, maxIteration)
		};

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
