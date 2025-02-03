package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static final String FILE_PATH = "student.json";

    public static void createSampleJson() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Student1"));
        students.add(new Student(2, "Student2"));
        students.add(new Student(3, "Student3"));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), new StudentList(students));
            System.out.println("JSON-файл успешно создан: "+FILE_PATH);
        }catch (IOException e) {
            System.out.println("Ошибка при создании JSON: "+ e.getMessage());
        }
    }
}
