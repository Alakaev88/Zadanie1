package org.example;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        FTPClientManager ftpManager = new FTPClientManager();
//
//        StudentManager.createSampleJson();
//
//        System.out.println("Ведите FTP - сервер: ");
//        String server = scanner.nextLine();
//
//        System.out.println("Введите порт (обычно 21): ");
//        int port = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.println("Введите логин: ");
//        String user = scanner.nextLine();
//
//        System.out.println("Введите пароль: ");
//        String password = scanner.nextLine();
//
//        if (ftpManager.connect(server, port, user, password)) {
//            ftpManager.printWorkingDirectory(); // Покажет текущий путь
//            String localFilePath = "C:\\Users\\Zveruga\\IdeaProjects\\Zadanie1\\student.json";
//            System.out.print("Введите путь для сохранения файла на сервере (например, /students.json): ");
//            String remoteFilePath = scanner.nextLine();
//
//            ftpManager.uploadFile(localFilePath, remoteFilePath);
//        }
//        scanner.close();
//        ftpManager.downloadFileToDisk("/students.json", "C:/temp/students.json");
//        ftpManager.disconnect();

        //чтение JSON
        String localFilePath = "C:/temp/students.json";
        StudentList studentList = JSONManager.readStudentsFromFile(localFilePath);
        System.out.println("Список студентов: "+ studentList);

        //поиск по id
        int searchId = 2;
        Student studentById = JSONManager.findStudentById(studentList, searchId);
        System.out.println("Найден студент по ID "+ searchId + ": "+ studentById);

        //поиск по имени
        String searchName = "Student1";
        List<Student> studentsByName = JSONManager.findStudentByName(studentList, searchName);
        System.out.println("Найдено студентов с именем "+ searchName + ": " + studentsByName);

        //добавляем нового студента
        JSONManager.addStudent(studentList, "NewStudent", localFilePath);
        System.out.println("Список студентов: "+ studentList);

    }
}