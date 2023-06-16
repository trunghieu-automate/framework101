package base.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ExcelUtils {

    /**
     * @Description: return Object[1][] for @DataProvider, the second dimension is all the row data of testcase's name
     * @Param: filename: path to excels file to read and update
     * tcToFetch: take a method name to write its results
     * result: result value to write
     * @Author: hieu.trungvu
     **/
    public static Object[][] getDataForSpecificTest(String filename, String sheetName, String testName) {
        Workbook wb;
        Sheet sh;
        Object[][] data = null;
        try {
            FileInputStream file = new FileInputStream(filename); // open the Excel file
            wb = new XSSFWorkbook(file); // create a workbook object
            sh = wb.getSheet(sheetName);
            ArrayList<Object> result = new ArrayList<>(); // a list to store the result
            for (Row row : sh) { // iterate over each row
                Cell cell = row.getCell(0); // get the first cell
                if (cellToString(cell).equalsIgnoreCase(testName)) { // check if the cell value matches the text case
                    for (int i = 1; i < row.getLastCellNum(); i++) { // iterate over the remaining cells in the row
                        result.add(cellToString(row.getCell(i))); // add the cell value to the result list
                    }
                    break;
                }
            }
            data = new Object[][]{result.toArray()}; // convert array to 2 dimension object for data provider
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * @Description: update test result to excel data file at the last column for specific testcases name.
     * @Param: filename: path to excels file to read and update
     * tcToFetch: take a method name to write its results
     * result: result value to write
     * @Author: hieu.trungvu
     **/
    public static void updateTestResultToExcelFile(String filename, String tcToFetch, String result) {
        Workbook wb;
        try {
            FileInputStream file = new FileInputStream(filename); // open the Excel file
            wb = WorkbookFactory.create(file);
            // loop through every sheet to find testcase's name
            boolean resultCellExist = false;
            for (Sheet sheet : wb) {
                int colIndex = 0;
                int rowIndex = 0;
                if (sheet == null) {
                    System.out.println("Unable to find the File/Sheet. Pls check if Sheet exists");
                } else {
                    boolean headerExist = false;
                    // loop through first row to get colIndex
                    for (Cell cell : sheet.getRow(0)) {
                        // colIndex will be same with Test Result column or last column for the first time
                        if (cellToString(cell).equalsIgnoreCase("Test Result")) {
                            colIndex = cell.getColumnIndex();
                            headerExist = true; // if not found, set true value
                        } else {
                            colIndex = sheet.getRow(0).getLastCellNum();
                        }
                    }
                    // loop through every cell that equal specific value then get row index
                    for (Row r : sheet) {
                        for (Cell c : r) {
                            if (cellToString(c).equalsIgnoreCase(tcToFetch)) {
                                rowIndex = c.getRowIndex();// if it's the first time, create first row header, if not set result to proper cell
                                resultCellExist = true; // turn true if found
                                break; //break the for loop when found testcases name
                            }
                        }
                    }
                    // Create header if not founded header, and update result only if found proper testcase's name
                    if (resultCellExist) {
                        if (!headerExist) {
                            sheet.getRow(0).createCell(colIndex).setCellValue("Test Result");
                            System.out.println("Added Test result column @sheet: " + sheet.getSheetName() + " and @col:" + colIndex);
                        }
                        sheet.getRow(rowIndex).createCell(colIndex).setCellValue(result);
                        System.out.println("Update Test result for " + tcToFetch + ", at cell (" + rowIndex + ", " + colIndex + ")");
                        break; // break after updated
                    }
                }
            }
            // write file only if the proper testcase's name is found
            if (resultCellExist) {
                try {
                    file.close();
                    FileOutputStream out = new FileOutputStream(filename);
                    wb.write(out);
                    out.close();
                    wb.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: method return string of all type of value
     * @Param: cell
     * @Author: hieu.trungvu
     **/
    private static String cellToString(Cell cell) {
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
