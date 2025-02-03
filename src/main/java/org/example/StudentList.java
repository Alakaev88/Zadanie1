package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentList {
    private List<Student> students;

    public StudentList(){}

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students){
        this.students = students;
    }
    @Override
    public String toString(){
        return "StudentLisn{" +"students=" + students+ '}';
    }
}





//import java.util.List;
//
//public class StudentList {
//    private List<Student> students;
//
//    public StudentList(){}
//
//    public StudentList(List<Student> students){
//        this.students = students;
//    }
//    public List<Student> getStudents(){
//        return students;
//    }
//}
