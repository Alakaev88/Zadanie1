package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private int id;
    private String name;

    // Конструктор без параметров (обязательно для Jackson)
    public Student() {}

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}







//public class Student {
//    private int id;
//    private String name;
//
//    public Student() {}
//
//    public Student(int id, String name){
//        this.id = id;
//        this.name = name;
//    }
//    public int getId(){
//        return id;
//    }
//    public String getName(){
//        return name;
//    }
//}
