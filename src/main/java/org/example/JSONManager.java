package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

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
}
