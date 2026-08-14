package com.petpulse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonDataHandler<T> implements DataHandler<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(List<T> data, String filename) {
        try {
            mapper.writeValue(new File(filename), data);
            System.out.println("Data saved to local JSON storage: " + filename);
        } catch (IOException e) {
            System.err.println("File I/O Error saving data: " + e.getMessage());
        }
    }

    @Override
    public List<T> load(String filename, Class<T[]> clazz) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                T[] array = mapper.readValue(file, clazz);
                return Arrays.asList(array);
            }
        } catch (IOException e) {
            System.err.println("File I/O Error loading data: " + e.getMessage());
        }
        return List.of();
    }
}
