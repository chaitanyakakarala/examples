package com.example.demo.service;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Iterator;

@Service
public class ReadExcelService {

    public void readXls(String filenameWthPath) {

        try (FileInputStream fileInputStream = new FileInputStream(filenameWthPath)){

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

            HSSFSheet datatypeSheet = workbook.getSheetAt(0);
            Iterator iterator = datatypeSheet.rowIterator();

            while (iterator.hasNext()) {

                HSSFRow currentRow = (HSSFRow) iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }

        }catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public void readXlsx(String filenameWthPath) {

        try (FileInputStream fileInputStream = new FileInputStream(filenameWthPath)){

            Workbook workbook = new XSSFWorkbook(fileInputStream);

            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.rowIterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    cellRead(cellIterator.next());
                }
                System.out.println();

            }

        }catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public void cellRead (Cell currentCell) {
        if (currentCell.getCellTypeEnum() == CellType.STRING) {
            System.out.print(currentCell.getStringCellValue() + "--");
        } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
            System.out.print(currentCell.getNumericCellValue() + "--");
        }
    }

}
