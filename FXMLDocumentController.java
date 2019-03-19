/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


/**
 *
 * @author Batuhan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private BarChart<?, ?> ChartExample;

    @FXML
    private CategoryAxis XChartExample;

    @FXML
    private NumberAxis YChartExample;
    
    @FXML
    private Button button;
    
    @FXML
    private TextArea output1;
    
    public ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("."));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        Stage stage = new Stage();
        File file = chooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }
        System.out.println("file " + file.getAbsolutePath());
        String filePath = file.getAbsolutePath();

        
        ExcelReader ec = new ExcelReader(filePath);
        try{
            ec.readExcelFile(lines);
        } 
        catch(IOException e){
            
        }
        catch(InvalidFormatException ie){
            
        }
        
        lines.remove(0);
        lines.remove(0);
        
        String dataString = "";
        
        for(ArrayList<String> line: lines) {
            for(String data: line) {
                dataString += data + "\t";
            }
            dataString += "\n"; 
        }

        output1.setText(dataString);
        
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series = new XYChart.Series();
        
        for(int i = 1; i<=6; i++){
            String name = "test" + i;
            series.getData().add(new XYChart.Data(name, i));
        }
        for(int i = 7; i>=1; i--){
            String name = "test" + (13-i);
            series.getData().add(new XYChart.Data(name, i));
        }
        
        
        ChartExample.getData().addAll(series);
    }    
}
   

