package base.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ExcelUtils {
    private static Workbook wb;
    private static Sheet sh;

    public static Object[][] getExcelData(String fileName, String sheetName, String testcases) {
        Object[][] data = null;
        Object[][] refinedData = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheet(sheetName);
            Row row = sh.getRow(0);

            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cellToString(cell);
                }
            }
            refinedData = new Object[][]{
                    Arrays.stream(data)
                            .filter(r -> String.valueOf(r[0]).equalsIgnoreCase(testcases)) // filter row that first element of row that equal testcases name
                            .flatMap(Arrays::stream)
                            .dropWhile(e -> String.valueOf(e).equalsIgnoreCase(testcases)) // drop the first element also
                            .toArray()
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        return refinedData;
    }

    public static Object[][] getDataForSpecificTest(String filename, String sheetname, String testname) {
        Workbook wb;
        Sheet sh;

        Object[][] data = null;
        try {
            FileInputStream file = new FileInputStream(filename); // open the Excel file
            wb = new XSSFWorkbook(file); // create a workbook object
            sh = wb.getSheet(sheetname);
            String text = "Doe"; // the text to match
            ArrayList<Object> result = new ArrayList<>(); // a list to store the result
            for (Row row : sh) { // iterate over each row
                Cell cell = row.getCell(0); // get the first cell
                if (cellToString(cell).equalsIgnoreCase(testname)) { // check if the cell value matches the text
                    for (int i = 1; i < row.getLastCellNum(); i++) { // iterate over the remaining cells in the row
                        result.add(cellToString(row.getCell(i))); // add the cell value to the result list
                    }
                    break;
                }
            }
            data = new Object[][]{result.toArray()};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Object[][] getExcelData(String fileName, String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheet(sheetName);
            Row row = sh.getRow(0);

            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cellToString(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static String cellToString(Cell cell) {
        CellType type = cell.getCellType();
        Object result = switch (type) {
            case NUMERIC -> // numeric value in excel
                    cell.getNumericCellValue();
            case STRING -> // string value in excel
                    cell.getStringCellValue();
            case BOOLEAN -> // boolean value in excel
                    cell.getBooleanCellValue();
            case BLANK -> // blank value in excel
                    "";
            case ERROR -> // error value in excel
                    "ERROR";
            case FORMULA -> // formula value in excel
                    cell.getCellFormula();
            case _NONE -> // None value in excel
                    "";
        };
        return result.toString();
    }

}
