/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Batuhan
 */
public class ExcelReader {
    
    public String filePath;
    
    ExcelReader(){
        
    }
    
    ExcelReader(String filePath){
        this.filePath = filePath;
    }
    
    public void readExcelFile(ArrayList<ArrayList<String>> lines) throws IOException, InvalidFormatException{
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        
        for (Row row: sheet) {
            ArrayList<String> cellData = new ArrayList<>(); 
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                cellData.add(cellValue);
            }
            lines.add(cellData);
        }
    }
    
    
}
