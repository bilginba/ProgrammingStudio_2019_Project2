/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampleproject;

import java.util.ArrayList;

/**
 *
 * @author Batuhan
 */
class Course {
    private String name;
    private String year;
    private ArrayList<Section> sections;

    public Course() {
        this.name = "";
        this.year = "";
        this.sections = new ArrayList<Section>();
    }

    public Course(String name, String year, ArrayList<Section> sections) {
        this.name = name;
        this.year = year;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }
    
    
    
    
}
