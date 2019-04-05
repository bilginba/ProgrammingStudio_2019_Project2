/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampleproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Batuhan
 */
public class FXMLDocumentController implements Initializable {
    
    private List<Course> Courses = new ArrayList<>();
    
    @FXML
    private ChoiceBox sectionChoiceBox;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private Button chooseFileButton;

    @FXML
    private ListView courseList;

    @FXML
    private Button giveInfo;

    @FXML
    private Label label;

    @FXML
    private Button chooseDirectoryButton;

    @FXML
    void openFileAction(ActionEvent event) {
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
        
        String filePath = file.getAbsolutePath().toString();
        
        readLines(filePath);
    }

    @FXML
    void openDirectoryAction(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();

        
        Stage stage = new Stage();
        File folder = dirChooser.showDialog(stage);
        

        
        if (folder == null) {
            return;
        }
        
        File[] listOfFiles = folder.listFiles();
        
        
            
        for(File file : listOfFiles){
            if (file.isFile()) {
                String filePath = file.getAbsolutePath().toString();

                readLines(filePath);
            }
        }
    }

    @FXML
    void displayData(ActionEvent event) {
        String text ="";
        int cID = getIndexFromCourseName(this.courseList.getSelectionModel().getSelectedItem().toString());
        System.out.println("Course ID : " + cID);
        int sID = Integer.parseInt(this.sectionChoiceBox.getValue().toString())-1;
        System.out.println("Section ID : " + sID);
        for(Student ss : this.Courses.get(cID).getSections().get(sID).getStudents()){
            text += ss.toString() + "\n";
        }

        this.infoTextArea.setText(text);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> sList  = FXCollections.observableArrayList();
        sList.add(1);
        sList.add(2);
        this.sectionChoiceBox.setItems(sList);
        this.sectionChoiceBox.setValue(1);
    }    

    private void readLines(String filePath) {
        ArrayList<ArrayList<ArrayList<String>>> sheets = new ArrayList<ArrayList<ArrayList<String>>>();
        
        ExcelReader ec = new ExcelReader(filePath);
        try{
            ec.readExcelFile(sheets);
        } 
        catch(IOException e){
        }
        catch(InvalidFormatException ie){
        }
        
        for(int i = 0; i<sheets.size(); i++){
            insertData(sheets.get(i));
        }
        
        ObservableList<String> cList  = FXCollections.observableArrayList();
        for(Course c: this.Courses){
            cList.add(c.getName());
        }

        this.courseList.setItems(cList);
    }


    private void insertData(ArrayList<ArrayList<String>> sheet) {
        int counter = 0;
        
        Course course = new Course();
        course.setName(sheet.get(counter++).get(1));
        course.setYear(sheet.get(counter++).get(1));
        
        Section section1 = new Section();
        section1.setSectionNum(Integer.parseInt(sheet.get(counter++).get(1)));
        for(; counter<6;counter++){
            Student student = new Student();
            student.setName(sheet.get(counter).get(1));
            student.setPoint(Integer.parseInt(sheet.get(counter).get(2)));
            section1.getStudents().add(student);
        }
        
        course.getSections().add(section1);
        
        
        Section section2 = new Section();
        section1.setSectionNum(Integer.parseInt(sheet.get(counter++).get(1)));
        for(; counter<10;counter++){
            Student student = new Student();
            student.setName(sheet.get(counter).get(1));
            student.setPoint(Integer.parseInt(sheet.get(counter).get(2)));
            section2.getStudents().add(student);
        }
        
        course.getSections().add(section2);
        
        
        this.Courses.add(course);
    }

    private int getIndexFromCourseName(String selectedItem) {
        for(int i = 0; i<this.Courses.size(); i++){
            if(this.Courses.get(i).getName().equals(selectedItem)){
                return i;
            }
        }
        return 0;
    }
    
}
