package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayThree {
	public static void main(String[] args) {
		ArrayList<String> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static ArrayList<String> getInput() {
		ArrayList<String> input = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day3/inputDay3.txt")));
			while (reader.ready()) {
				input.add(reader.readLine());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private static int partOne(ArrayList<String> input) {
		int score = 0;

		for (String bag : input) {
			String firstCompartment = bag.substring(0, bag.length()/2);
			String secondCompartment = bag.substring(bag.length()/2, bag.length());

			score += getScore(getCommonItems(firstCompartment, secondCompartment));
		}

		return score;
	}

	private static int partTwo(ArrayList<String> input) {
		int score = 0;

		for (int i = 0; i < input.size(); i += 3) {
			String items = getCommonItems(input.get(i), getCommonItems(input.get(i+1), input.get(i+2)));
			score += getScore(items);
		}

		return score;
	}

	private static String getCommonItems(String first, String second) {
		StringBuilder commonItems = new StringBuilder();

		char[] items = second.toCharArray();
		String other = first;

		if (first.length() <= second.length()) {
			items = first.toCharArray();
			other = second;
		}
		for (char item : items) {
			if (other.indexOf(item) != -1 && commonItems.indexOf(item + "") == -1) {
				commonItems.append(item);
			}
		}
		return commonItems.toString();
	}

	private static int getScore(String commonItems) {
		int score = 0;

		for (char item : commonItems.toCharArray()) {
			int offset = 'a' - 1;

			if (Character.isUpperCase(item))  
				offset = 'A' - 27;
			score += item - offset;
		}
		return score;
	}
}
