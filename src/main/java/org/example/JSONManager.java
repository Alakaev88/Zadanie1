package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static StudentList readStudentsFromFile(String filePuth) {
        try {
            return objectMapper.readValue(new File(filePuth), StudentList.class);
        }catch (IOException e) {
            System.out.println("Ошибка чтения JSON: "+ e.getMessage());
            return new StudentList(); // если ошибка, вернем пустой список
        }
    }
    //метод поиска по id
    public static Student findStudentById(StudentList studentList, int id){
        return studentList.getStudents().stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null); // если не найдено, возвращаем null
    }
    //метод поиска по name
    public static List<Student> findStudentByName(StudentList studentList, String name) {
        return studentList.getStudents().stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .toList();// возвращаем список студентов с таким именем
    }
}
