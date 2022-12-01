package Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayOne {
	public static void main(String[] args) {
		ArrayList<Integer> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static ArrayList<Integer> getInput() {
		ArrayList<Integer> input = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day1/inputDay1.txt")));
			String line = "";
			int calories = 0;
			while (reader.ready()) {
				if (!(line = reader.readLine()).isEmpty())
					calories += Integer.parseInt(line);
				else {
					input.add(calories);
					calories = 0;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private static int partOne(ArrayList<Integer> input) {
		input.sort(null);
		return input.get(input.size() - 1);
	}
	private static int partTwo(ArrayList<Integer> input) {
		input.sort(null);
		int size = input.size();
		return input.get(size - 1) + input.get(size - 2) + input.get(size - 3);
	}
}