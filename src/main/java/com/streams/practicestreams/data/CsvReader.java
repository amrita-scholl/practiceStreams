package com.streams.practicestreams.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.streams.practicestreams.model.Employee;

public class CsvReader {
    public List<Employee> readCSV(String filePath) {
        List<Employee> employees = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Employee employee = new Employee(
                        values[0],
                        values[1],
                        values[2],
                        Integer.parseInt(values[3]),
                        Integer.parseInt(values[4]),
                        values[5],
                     	values[6],
                     	Integer.parseInt(values[7]),
                        Boolean.getBoolean(values[8])
                );
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}


