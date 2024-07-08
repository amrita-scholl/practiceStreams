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

	public void processChunks(List<List<Employee>> chunks) {

		// 1: one way of printing All Joining Years in Sorted way
		// chunks.forEach(chunk -> {
		// System.out.println("Processing chunk:");
		// List<String> joiningyear = chunk.parallelStream()
		// .map(Employee::getJoiningYear).sorted()
		// .collect(Collectors.toList());

		// joiningyear.forEach(System.out::println);
		// });
		// System.out.println("num of chunks : " + chunks.size());

		// 2: Another way to Print All Joining Years in Sorted way
		// chunks.forEach(chunk ->
		// chunk.stream().map(Employee::getJoiningYear).sorted().collect(Collectors.toList())
		// .forEach(System.out::println));

		// 3: Filter based on a joining year

		// chunks.forEach(chunk -> chunk.stream().filter(
		// employee ->
		// employee.getJoiningYear().equals("2014")).sorted().collect(Collectors.toList())
		// .forEach(System.out::println));

		// 4: count
		// chunks.forEach(chunk -> {
		// chunk.stream().count();
		// System.out.println("num of chunks : " + chunk.stream().count());
		// });

		List<User> users = Stream.of(
				new User("user1", "1234567890", Arrays.asList("abc@gmail.com", "def@gmail.com")),
				new User("user2", "9876543210", Arrays.asList("xyz@gmail.com", "ihg@gmail.com")))
				.collect(Collectors.toList());

		// List<String> phoneNumbers =
		// users.stream().map(User::getName).collect(Collectors.toList());

		// System.out.println(phoneNumbers);

		// List<List<String>> emails =
		// users.stream().map(User::getEmail).collect(Collectors.toList());

		// System.out.println(emails);

		// List<String> emails = users.stream().flatMap(u ->
		// u.getEmail().stream()).collect(Collectors.toList());

		// System.out.println(emails);

		// String input = "amrita";

		// Map<String, Long> countMap = Arrays.stream(input.split("")).collect(
		// Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// System.out.println(countMap);

		List<EmployeeSalary> employeeSalaries = Stream.of(
				new EmployeeSalary(1, "John", "dev", 2000),
				new EmployeeSalary(2, "Sam", "qa", 1000),
				new EmployeeSalary(3, "Wayn", "qa", 4000),
				new EmployeeSalary(4, "Zara", "dev", 7000),
				new EmployeeSalary(5, "Riya", "prod", 3000),
				new EmployeeSalary(6, "Rosh", "dev", 2000)).collect(Collectors.toList());

		// Comparator<EmployeeSalary> compareBySalary =
		// Comparator.comparing(EmployeeSalary::getSalary);

		// Map<String, Optional<EmployeeSalary>> employeeMap =
		// employeeSalaries.stream().collect(
		// Collectors.groupingBy(EmployeeSalary::getDepartment,
		// Collectors.reducing(BinaryOperator.maxBy(compareBySalary))));

		// System.out.println("Employee Map >>> " + employeeMap);

		Map<String, EmployeeSalary> employeeMap1 = employeeSalaries.stream().collect(
				Collectors.groupingBy(EmployeeSalary::getDepartment,
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparingDouble(EmployeeSalary::getSalary)),
								Optional::get)));

		System.out.println("Employee Map >>> " + employeeMap1);

		for (Map.Entry<String, EmployeeSalary> entry : employeeMap1.entrySet()) {
			String employeeId = entry.getKey();
			double salary = entry.getValue().getSalary();
			System.out.println("Employee ID: " + employeeId + ", Employee Salary: " + salary);
		}

		List<Integer> numbers = Arrays.asList(3, 7, 8, 1, 5, 9);

		List<String> words = Arrays.asList("corejava", "spring", "hibernate");

		int sum = 0;
		for (int no : numbers) {
			sum = sum + no;
		}
		System.out.println(sum);

		int sum1 = numbers.stream().mapToInt(i -> i).sum();
		System.out.println(sum1);

		Integer reduceSum = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println(reduceSum);

		Optional<Integer> reduceSumWithMethodReference = numbers.stream().reduce(Integer::sum);
		System.out.println(reduceSumWithMethodReference.get());

		Integer mulResult = numbers.stream().reduce(1, (a, b) -> a * b);
		System.out.println(mulResult);

		Integer maxvalue = numbers.stream().reduce(0, (a, b) -> a > b ? a : b);
		System.out.println(maxvalue);

		Integer maxvalueWithMethodReference = numbers.stream().reduce(Integer::max).get();
		System.out.println(maxvalueWithMethodReference);

		String longestString = words.stream()
				.reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2)
				.get();
		System.out.println(longestString);

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