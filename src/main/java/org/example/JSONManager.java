package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();
        //чтение файла JSON
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
    //метод поиска по имени
    public static List<Student> findStudentByName(StudentList studentList, String name) {
        return studentList.getStudents().stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .toList();// возвращаем список студентов с таким именем
    }
    //метод добавления студента
    public static void addStudent(StudentList studentList, String name, String filePath){
        int newId = studentList.getStudents().stream()
                .mapToInt(Student::getId)
                .max()
                .orElse(0)+1; // новый ID = максимальный +1

        Student newStudent = new Student(newId, name);
        studentList.getStudents().add(newStudent);

        saveStudentsToFile(studentList, filePath);
        System.out.println("Добавлен новый студент: "+ newStudent);
    }
    //сохранение JSON в файл
    public static void saveStudentsToFile(StudentList studentList, String filePath){
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), studentList);
            System.out.println("Файл обновлен: "+filePath);
        }catch (IOException e){
            System.out.println("Ошибак при сохранении JSON^ "+e.getMessage());
        }
    }
}
