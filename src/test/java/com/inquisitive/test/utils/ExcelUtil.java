package com.inquisitive.test.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class ExcelUtil {
    public String xlFilePath;
    String sheetName;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    public XSSFCell cell1 = null;
    int firstRow = 1;

    public ExcelUtil(String xlFilePath, String sheetName) throws Exception {
        this.xlFilePath = xlFilePath;
        this.sheetName = sheetName;
        try {
            fis = new FileInputStream(xlFilePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } finally {
            workbook.close();
            fis.close();
        }

    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        int number = sheet.getPhysicalNumberOfRows();
        return number;
    }

    public int getColumnCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(0).getLastCellNum();
    }

    public  Object[][] testData(String sheetName) throws Exception {
        int rowCount=getRowCount(sheetName);
        int ColCount=getColumnCount(sheetName);
        Object data[][]=new Object[rowCount-1][ColCount];
        for(int i=firstRow; i<rowCount;i++){
            for(int j=0;j<ColCount;j++){
                cell1 = sheet.getRow(i).getCell(j);
              data[i-1][j]=cellValue(cell1);
            }
        }
    return data;
    }

    public String cellValue(Cell cell) {
        String celltext = "";
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // format in form of M/D/YY
                double d = cell.getNumericCellValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(HSSFDateUtil.getJavaDate(d));
                celltext = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + celltext;
            } else {
                return String.valueOf(cell.getCellFormula());
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else {
            return "";
        }
    }
}
