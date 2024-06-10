package org.criss;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Readexcel {

    private static final String READ_FILE_PATH = System.getProperty("inputfile");

    InputStream ExcelFileToRead = new FileInputStream(READ_FILE_PATH);
    XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

    XSSFWorkbook test = new XSSFWorkbook();

    XSSFSheet sheet = wb.getSheetAt(0);
    XSSFRow row;
    XSSFCell cell;

    Iterator rows = sheet.rowIterator();

    public Readexcel() throws IOException {
    }

    public List<Mancare> metoda() throws IOException {

        List<Mancare> listamancare = new ArrayList<>();
        rows.next();
        while (rows.hasNext()) {
            Mancare m = new Mancare();

            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            m.setNumemancare(row.getCell(0).getStringCellValue());
            m.setCalorii((long) row.getCell(1).getNumericCellValue());
            m.setGrame((long) row.getCell(2).getNumericCellValue());
            m.setProteine((long) row.getCell(3).getNumericCellValue());
            m.setMicdejun(row.getCell(4).getStringCellValue().charAt(0));
            m.setPranz(row.getCell(5).getStringCellValue().charAt(0));
            m.setCina(row.getCell(6).getStringCellValue().charAt(0));
            m.setGustare(row.getCell(7).getStringCellValue().charAt(0));

            listamancare.add(m);
        }
        return listamancare;
        }
    }





