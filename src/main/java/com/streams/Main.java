package com.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streams.data.CsvReader;
import com.streams.data.DataChunker;
import com.streams.model.Employee;
import com.streams.model.EmployeeSalary;
import com.streams.model.User;

public class Main {

	// practice in this method
	public void processChunks(List<List<Employee>> chunks) {

		List<User> users = Stream.of(
				new User("user1", "1234567890", Arrays.asList("abc@gmail.com", "xyz@gmail.com")),
				new User("user2", "9876543210", Arrays.asList("bdg@gmail.com", "ijk@gmail.com")))
				.collect(Collectors.toList());

		// Map Operations

		List<String> phoneNumbers = users.stream().map(User::getPhone).collect(Collectors.toList());

		List<List<String>> email = users.stream().map(User::getEmail).collect(Collectors.toList());

		List<String> emails = users.stream().flatMap(u -> u.getEmail().stream()).collect(Collectors.toList());

		System.out.println(email);
		System.out.println(emails);

		List<EmployeeSalary> employeeSalaries = Stream.of(
				new EmployeeSalary(1, "John", "dev", 2000),
				new EmployeeSalary(1, "Wayne", "dev", 5000),
				new EmployeeSalary(1, "Sam", "dev", 7000),
				new EmployeeSalary(1, "Ross", "qa", 9000),
				new EmployeeSalary(1, "Riya", "qa", 12000),
				new EmployeeSalary(1, "Zara", "qa", 1000),
				new EmployeeSalary(1, "Kelly", "prod", 6000),
				new EmployeeSalary(1, "Akka", "prod", 4000)).collect(Collectors.toList());

		Comparator<EmployeeSalary> compareBySalary = Comparator.comparing(EmployeeSalary::getSalary);

		Map<String, Optional<EmployeeSalary>> employeeMap = employeeSalaries.stream().collect(
				Collectors.groupingBy(EmployeeSalary::getDepartment,
						Collectors.reducing(BinaryOperator.maxBy(compareBySalary))));

	}

	public static void main(String[] args) {
		String filePath = "C:\\Amrita\\VCS_Java_Prj\\streams_practice\\practice\\src\\main\\java\\com\\streams\\file\\Employee.csv";

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