package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FTPClientManager ftpManager = new FTPClientManager();

        //  Создание JSON-файла, если его нет
        String localFilePath = "C:/temp/students.json";
        StudentManager.createSampleJson(localFilePath);

        // 1️⃣ Подключение к FTP-серверу
        System.out.print("Введите FTP-хост: ");
        String ftpHost = scanner.nextLine();

        System.out.print("Введите порт (обычно 21): ");
        int ftpPort = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.print("Введите логин: ");
        String ftpUser = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String ftpPassword = scanner.nextLine();

        if (!ftpManager.connect(ftpHost, ftpPort, ftpUser, ftpPassword)) {
            System.out.println("Ошибка подключения! Завершение работы.");
            return;
        }

        // 2️⃣ Вывод текущей директории FTP
        ftpManager.printWorkingDirectory();

        // String localFilePath = "C:/temp/students.json"; // Локальная копия
        String remoteFilePath = "/students.json"; // Путь на сервере

        // 3️⃣ Загрузка JSON-файла на сервер
        if (!ftpManager.uploadFile(localFilePath, remoteFilePath)) {
            System.out.println("Ошибка загрузки JSON-файла на сервер. Завершение работы.");
            ftpManager.disconnect();
            return;
        }

        // 4️⃣ Скачивание файла обратно с сервера
        ftpManager.downloadFile(remoteFilePath, localFilePath);
        StudentList studentList = JSONManager.readStudentsFromFile(localFilePath);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать список студентов");
            System.out.println("2. Найти студента по ID");
            System.out.println("3. Найти студента по имени");
            System.out.println("4. Добавить нового студента");
            System.out.println("5. Удалить студента по ID");
            System.out.println("6. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Список студентов: " + studentList);
                    break;
                case 2:
                    System.out.print("Введите ID студента: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Student foundStudent = JSONManager.findStudentById(studentList, searchId);
                    System.out.println(foundStudent != null ? "Найден студент: " + foundStudent : "Студент не найден");
                    break;
                case 3:
                    System.out.print("Введите имя студента: ");
                    String searchName = scanner.nextLine();
                    System.out.println("Найденные студенты: " + JSONManager.findStudentsByName(studentList, searchName));
                    break;
                case 4:
                    System.out.print("Введите имя нового студента: ");
                    String newStudentName = scanner.nextLine();
                    JSONManager.addStudent(studentList, newStudentName, localFilePath);
                    break;
                case 5:
                    System.out.print("Введите ID студента для удаления: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    JSONManager.removeStudentById(studentList, deleteId, localFilePath);
                    break;
                case 6:
                    System.out.println("Сохранение данных и загрузка файла обратно на сервер...");

                    // 5️⃣ Загружаем изменённый JSON-файл обратно на FTP
                    if (!ftpManager.uploadFile(localFilePath, remoteFilePath)) {
                        System.out.println("Ошибка загрузки файла на сервер.");
                    }

                    ftpManager.disconnect(); // Закрываем соединение
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
