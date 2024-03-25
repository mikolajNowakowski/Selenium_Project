package utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class FileReaders {

    // ==== properties ====
    public static Properties propertiesLoader(String filepath) {
        var props = new Properties();
        try (FileInputStream input = new FileInputStream(filepath)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }


    // ==== exel ====
    public static List<ArrayList<String>> loadExcelData(String filePath, String sheetName) {
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Unsupported file format");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        var rowData = new ArrayList<ArrayList<String>>();


        int sheetsNumber = workbook.getNumberOfSheets();

        for (int i = 0; i < sheetsNumber; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                var sheet = workbook.getSheetAt(i);
                var rowsIterator = sheet.iterator();
                while (rowsIterator.hasNext()) {
                    var colIterator = rowsIterator.next().cellIterator();
                    if (colIterator.hasNext()) {
                        var listOfTestData = new ArrayList<String>();
                        colIterator.forEachRemaining(cell -> listOfTestData.add(convertCellToString(cell)));
                       rowData.add(listOfTestData);
                    }
                }
            }
        }
            return rowData;
    }


    public static void main(String[] args) {
        var data = loadExcelData("./src/main/resources/RemovingProductFromBasket.xlsx","orderCompleteTest");

        for (var row:data) {
            for (var col:row) {
                System.out.println(col);
            }
        }

    }

    private static String convertCellToString(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> new DecimalFormat("0.00").format(cell.getNumericCellValue());
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue()).replaceAll("[^a-zA-Z]", "");
            case FORMULA -> cell.getCellFormula();

            default -> "";
        };
    }

}
