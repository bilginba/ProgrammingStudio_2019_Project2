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
class Section {
    private int sectionNum;
    private ArrayList<Student> students;

    public Section(int sectionNum, ArrayList<Student> students) {
        this.sectionNum = sectionNum;
        this.students = students;
    }

    public Section() {
        this.sectionNum = 0;
        this.students = new ArrayList<Student>();
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    
    
    
    
}
