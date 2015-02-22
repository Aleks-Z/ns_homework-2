package com.company;

import Jama.Matrix;
import com.company.conjugateGradient.DaiYuan;
import com.company.lang.ISolver;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        // lets write some solution to .xlsx file
        File inFile = new File("Template.xlsx");
        File outFile = new File("Results.xlsx");

        int n = 10;
        ISolver solver = new DaiYuan(Matrix.random(n, n).getArray(), Matrix.random(1, n).getColumnPackedCopy());

        Workbook workbook;
        try (InputStream in = new BufferedInputStream(new FileInputStream(inFile))) {
//            read from template file
            workbook = new SXSSFWorkbook(new XSSFWorkbook(in), 100);
            Sheet sheet = workbook.getSheetAt(0);

//            insert values (into file copy which is stored into memory)
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
        }

    }


}
