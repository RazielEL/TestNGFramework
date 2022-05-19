package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook book;
    static Sheet sheet;

    public static void openExcel (String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // returns in sheet
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }
    // returns number of rows in sheet
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    // returns total number of columns in row
    public static int getColumntCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
    //returns data from cell in strings
    public static String getCellData (int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    public static List<Map<String, String>> excelIntoMap(String filePath, String sheetName){

        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> ListData = new ArrayList<>();

        // outer loop
        for (int row = 1; row <getRowCount(); row++){
            // creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();
            for (int col=0; col<getColumntCount(row); col++){
                map.put(getCellData(0,col),getCellData(row,col));
            }

            ListData.add(map);

        }

        return ListData;

    }



}
