package com.example;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TestCase> allTestCases = TestCases.generateAllTestCases();

        String outputPath = args.length > 0 ? args[0] : "data.json";


        Path filePath = Paths.get(outputPath).isAbsolute()
                ? Paths.get(outputPath)
                : Paths.get(System.getProperty("user.dir"), outputPath);

        Path parentDir = filePath.getParent();
        if (parentDir != null) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                System.err.println("Ошибка при создании директории: " + parentDir);
                e.printStackTrace();
                return;
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Читабельный JSON

        try {
            mapper.writeValue(filePath.toFile(), allTestCases);
            System.out.println("Файл data.json успешно создан по пути: " + filePath.toAbsolutePath());
        } catch (JsonMappingException e) {
            System.err.println("Ошибка маппинга JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (StreamWriteException e) {
            System.err.println("Ошибка записи JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода при записи файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}