package org.example;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.*;

public class FTPClientManager {
    private FTPClient ftpClient;
    // 1. Конструктор: инициализирует FTP-клиент
    public FTPClientManager(){
        this.ftpClient = new FTPClient();
    }
    // 2. Подключение к FTP-серверу
    public boolean connect(String server, int port,String user, String password){
        try {
            ftpClient.connect(server,port);
            System.out.println("Подключение к "+ server +" успешно!");

            boolean login = ftpClient.login(user, password);
            if (login) {
                System.out.println("Авторизация успешна!");
                ftpClient.enterLocalActiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                return true;
            }else {
                System.out.println("Ошибка авторизации!");
                ftpClient.disconnect();
                return false;
            }
        } catch (IOException e){
            System.out.println("Ошибка при подключении: "+ e.getMessage());
            return false;
        }
    }
    // 3 Метод для загрузки файла на сервер
    public boolean uploadFile(String localFilePath, String remoteFilePath) {
        try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
            boolean success = ftpClient.storeFile(remoteFilePath, inputStream);
            if (success) {
                System.out.println("Файл успешно загружен на сервер: " + remoteFilePath);
            } else {
                System.out.println("Ошибка при загрузке файла.");
            }
            return success;
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
            return false;
        }
    }

    // 4. Метод для скачивания файла с FTP и сохранения на диск
    public void downloadFile(String remoteFilePath, String localFilePath) {
        try (InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath)) {
            if (inputStream == null) {
                System.out.println("Ошибка: файл не найден на FTP или нет доступа.");
                return;
            }

            File file = new File(localFilePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // Создаём папку, если её нет
            }

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            ftpClient.completePendingCommand(); // Завершаем команду FTP
            System.out.println("Файл успешно скачан и сохранён: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
    // 5. Метод для вывода текущей рабочей директории на FTP-сервере
    public void printWorkingDirectory() {
        try {
            String currentDir = ftpClient.printWorkingDirectory();
            System.out.println("Текущая директория FTP: " + currentDir);
        } catch (IOException e) {
            System.out.println("Ошибка при получении текущей директории: " + e.getMessage());
        }
    }
    // 6. Метод для отключения от FTP-сервера
    public void disconnect(){
        try{
            if (ftpClient.isConnected()){
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Отключение от FTP-сервера.");
            }
        }catch (IOException e){
            System.out.println("Ошибка при отключении: " + e.getMessage());
        }
    }
}
