package com.streams.data;

import java.util.ArrayList;
import java.util.List;

import com.streams.model.Employee;

public class DataChunker {
    public List<List<Employee>> chunkData(List<Employee> employees, int chunkSize) {
        List<List<Employee>> chunks = new ArrayList<>();
        for (int i = 0; i < employees.size(); i += chunkSize) {
            chunks.add(employees.subList(i, Math.min(i + chunkSize, employees.size())));
        }
        return chunks;
    }
}
