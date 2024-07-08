package com.streams.practicestreams;

import com.streams.practicestreams.data.CsvReader;
import com.streams.practicestreams.data.DataChunker;
import com.streams.practicestreams.model.Employee;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProcessor {

	 public void processChunks(List<List<Employee>> chunks) {
	        chunks.forEach(chunk -> {
	            System.out.println("Processing chunk:");
	            List<String> joiningyear = chunk.stream()
	                    .map(Employee::getJoiningYear).sorted()
	                    .collect(Collectors.toList());
	            
	            joiningyear.forEach(System.out::println);
	        });
	    }
	 
	 
	 public static void main(String[] args) {
	        String filePath = "C:\\Users\\amrit\\eclipse-workspace\\practicestreams\\src\\main\\data\\Employee.csv";

	        // Read the CSV file
	        CsvReader csvReader = new CsvReader();
	        List<Employee> employees = csvReader.readCSV(filePath);

	        // Chunk the data
	        DataChunker dataChunker = new DataChunker();
	        List<List<Employee>> chunks = dataChunker.chunkData(employees, 100); // chunk size is 100

	        // Process the chunks
	        StreamProcessor streamProcessor = new StreamProcessor();
	        streamProcessor.processChunks(chunks);
	    }
	 
	 
}
