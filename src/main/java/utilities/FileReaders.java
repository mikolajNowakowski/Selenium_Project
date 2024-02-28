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
    public static Object[][] loadExcelData(String filePath, int sheetNumber) {
        return loadExcelData(filePath, -1, -1, sheetNumber);
    }

    public static Object[][] loadExcelData(String filePath, int maxRows, int maxColumns, int sheetNumber) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Unsupported file format");
            }

            Sheet sheet = workbook.getSheetAt(sheetNumber); // Assuming you are reading from the first sheet

            int rowCount = maxRows > 0 ? Math.min(maxRows, sheet.getPhysicalNumberOfRows()) : sheet.getPhysicalNumberOfRows();
            int colCount = 0;

            if (rowCount > 0) {
                colCount = maxColumns > 0 ? Math.min(maxColumns, sheet.getRow(0).getPhysicalNumberOfCells()) : sheet.getRow(0).getPhysicalNumberOfCells();
            }

            Object[][] data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[i][j] = convertCellToString(cell).replaceAll("'","").replaceAll("’","");
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String convertCellToString(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> new DecimalFormat("0").format(cell.getNumericCellValue());
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

}
