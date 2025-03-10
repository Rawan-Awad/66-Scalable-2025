package com.example.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Primary
@Repository
@SuppressWarnings("rawtypes")
public abstract class MainRepository<T> {

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected abstract String getDataPath();
    protected abstract Class<T[]> getArrayType();

    public ArrayList<T> findAll() {
        try {
            File file = new File(getDataPath());
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            T[] array = objectMapper.readValue(file, getArrayType());
            return new ArrayList<>(Arrays.asList(array));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }

    public void saveAll(ArrayList<T> data) {
        try {
            File file = new File(getDataPath());
            file.getParentFile().mkdirs();
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public void save(T data) {
        ArrayList<T> allData = findAll();
        allData.add(data);
        saveAll(allData);
    }
}