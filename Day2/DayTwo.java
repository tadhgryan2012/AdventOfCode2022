package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayTwo {

	private static HashMap<String, Integer> pickPoints = new HashMap<>(Map.of(
		"A X", 4,
		"A Y", 8,
		"A Z", 3,
		"B X", 1,
		"B Y", 5,
		"B Z", 9,
		"C X", 7,
		"C Y", 2,
		"C Z", 6
	));
	public static void main(String[] args) {
		ArrayList<String> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static ArrayList<String> getInput() {
		ArrayList<String> input = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day2/inputDay2.txt")));
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
		for (String line : input) {
			try {
				score += pickPoints.get(line);
			} catch (NullPointerException e) {
				System.out.println(line.toString());
			}
		}
		return score;
	}
	private static int partTwo(ArrayList<String> input) {
		HashMap<String, String> getGameMap = new HashMap<>(Map.of(
		"A X", "Z",
		"A Y", "X",
		"A Z", "Y",
		"B X", "X",
		"B Y", "Y",
		"B Z", "Z",
		"C X", "Y",
		"C Y", "Z",
		"C Z", "X"
		));
		int score = 0;
		for (String line : input) {
			StringBuilder lineBetter = new StringBuilder(line);
			lineBetter.replace(2, 3, getGameMap.get(line));
			score += pickPoints.get(lineBetter.toString());
		}
		return score;
	}
}