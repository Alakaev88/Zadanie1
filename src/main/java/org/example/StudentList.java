package org.example;

import java.util.List;

public class StudentList {
    private List<Student> students;

    public StudentList(){}

    public StudentList(List<Student> students){
        this.students = students;
    }
    public List<Student> getStudents(){
        return students;
    }
}
