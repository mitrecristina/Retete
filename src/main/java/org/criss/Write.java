package org.criss;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Write {

    private static final String OUT_FILE_PATH = System.getProperty("outputfile");
    private Map<Pair<Integer, Long>, List<Mancare>> map;
    public Write(Map<Pair<Integer, Long>, List<Mancare>> map) {
        this.map = map;
    }

    public void doWrite() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet("Mancare Data ");

        // creating a row object
        XSSFRow row;

        int rowid = 0;

        String[] header = new String[]{"Numarul zilei", "Calorii", "Mic dejung", "Pranz", "Cina", "Gustare"};

        // write header
        row = spreadsheet.createRow(rowid++);
        int index = 0;
        for (String head : header) {
            row.createCell(index).setCellValue(head);
            spreadsheet.setColumnWidth(index++, 25 * 256);
        }

        for (Map.Entry<Pair<Integer, Long>, List<Mancare>> mp : map.entrySet()) {
            index = 0;

            List<Mancare> listaMancare = mp.getValue();

            row = spreadsheet.createRow(rowid++);
            row.createCell(index++).setCellValue(mp.getKey().getLeft());
            row.createCell(index++).setCellValue(mp.getKey().getRight());

            for (Mancare m : listaMancare) {
                row.createCell(index++).setCellValue(m.getNumemancare());
            }
        }

        FileOutputStream out = new FileOutputStream(OUT_FILE_PATH);

        workbook.write(out);
        out.close();
    }
}


