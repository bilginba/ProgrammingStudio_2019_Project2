/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampleproject;

/**
 *
 * @author STUDENT
 */
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Volkan Özer, Artun Burak Meçik and Batuhan Bilgin
 */
public class ExcelReader {
    
    public String FilePath;
    
    ExcelReader(){
        
    }
    
    ExcelReader(String FilePath){
        this.FilePath = FilePath;
    }
    
    public void readExcelFile(ArrayList<ArrayList<ArrayList<String>>> sheets) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(FilePath));
        
        
        
        
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
       
        if(workbook.getNumberOfSheets()==1){
            File file = new File(FilePath);
            ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
            DataFormatter dataFormatter = new DataFormatter();

            for (Row row: workbook.getSheetAt(0)) {
                ArrayList<String> cellData = new ArrayList<>(); 
                for(Cell cell: row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    cellData.add(cellValue);
                }
                lines.add(cellData);
            }         
            sheets.add(lines);
   
        }
        else{
            for(int i=0; i<workbook.getNumberOfSheets();i++){
                ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

                DataFormatter dataFormatter = new DataFormatter();

                for (Row row: workbook.getSheetAt(i)) {
                    ArrayList<String> cellData = new ArrayList<>(); 
                    for(Cell cell: row) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        cellData.add(cellValue);
                    }
                    lines.add(cellData);
                }

                sheets.add(lines);
            }  
        }

        

        
        
        
    }
    
 
    
    
}
