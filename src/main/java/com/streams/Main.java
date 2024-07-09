package com.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streams.data.CsvReader;
import com.streams.data.DataChunker;
import com.streams.model.Employee;
import com.streams.model.EmployeeSalary;
import com.streams.model.User;

public class Main {

	public void processChunks(List<List<Employee>> chunks) {
		
		List<User> users = Stream.of(
				new User("user1","1234567890",Arrays.asList("abc@gmail.com","xyz@gmail.com")),
				new User("user2","9876543210",Arrays.asList("ijk@gmail.com","def@gmail.com"))).collect(Collectors.toList());
	
		
		
		List<EmployeeSalary> employeeSalaries = Stream.of(
				new EmployeeSalary(1, "John", "dev", 1000),
				new EmployeeSalary(2, "Sam", "dev", 2000),
				new EmployeeSalary(3, "Rose", "dev", 3000),
				new EmployeeSalary(4, "Jack", "qa", 4000),
				new EmployeeSalary(5, "Riya", "qa", 5000),
				new EmployeeSalary(6, "Zara", "qa", 6000),
				new EmployeeSalary(7, "Kelly", "prod", 7000),
				new EmployeeSalary(8, "Akka", "prod", 8000),
				new EmployeeSalary(9, "Ross", "prod", 9000)
				).collect(Collectors.toList());
		
		// difference map and flat map 
		
//		List<String> phoneNumbers = users.stream().map(User::getPhone).collect(Collectors.toList());
//		System.out.println(phoneNumbers);
		
		//map
//		List<List<String>> email = users.stream().map(User::getEmail).collect(Collectors.toList());
//		System.out.println(email);
//		
		//flatmap
//		List<String> emails = users.stream().flatMap(u-> u.getEmail().stream()).collect(Collectors.toList());
//		System.out.println(emails);
//		
		Comparator<EmployeeSalary> compareBySalary = Comparator.comparing(EmployeeSalary::getSalary);
		
		Map<String , Optional<EmployeeSalary>> employeeMap = employeeSalaries.stream().
				collect(Collectors.groupingBy(EmployeeSalary::getDepartment,Collectors.reducing(BinaryOperator.maxBy(compareBySalary))));
		
		System.out.println(employeeMap );

		Map<String , EmployeeSalary> employeeMap1 = employeeSalaries.stream().
				collect(Collectors.groupingBy(EmployeeSalary::getDepartment,
						Collectors.collectingAndThen(Collectors.maxBy(
							Comparator.comparingDouble(EmployeeSalary::getSalary)), Optional::get)));
					
						
								
		System.out.println(employeeMap1 );
		
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
		Main streamProcessor = new Main();
		streamProcessor.processChunks(chunks);
	}

}