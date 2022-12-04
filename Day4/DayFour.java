package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayFour {
	public static void main(String[] args) {
		ArrayList<String[]> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static ArrayList<String[]> getInput() {
		ArrayList<String[]> input = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day4/inputDay4.txt")));
			while (reader.ready()) {
				input.add(reader.readLine().split(","));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private static int partOne(ArrayList<String[]> input) {
		input = fixInput(input);
		int score = 0;
		for (String[] line : input) {
			if (Integer.parseInt(line[0]) >= Integer.parseInt(line[2]) && Integer.parseInt(line[1]) <= Integer.parseInt(line[3]))
				score++;
			else if (Integer.parseInt(line[2]) >= Integer.parseInt(line[0]) && Integer.parseInt(line[3]) <= Integer.parseInt(line[1]))
				score++;
		}
		return score;
	}

	private static int partTwo(ArrayList<String[]> input) {
		input = fixInput(input);
		int score = 0;
		for (String[] line : input) {
			if (Integer.parseInt(line[0]) >= Integer.parseInt(line[2]) && Integer.parseInt(line[0]) <= Integer.parseInt(line[3]))
				score++;
			else if (Integer.parseInt(line[1]) >= Integer.parseInt(line[2]) && Integer.parseInt(line[1]) <= Integer.parseInt(line[3]))
				score++;
			else if (Integer.parseInt(line[2]) >= Integer.parseInt(line[0]) && Integer.parseInt(line[2]) <= Integer.parseInt(line[1]))
				score++;
			else if (Integer.parseInt(line[3]) >= Integer.parseInt(line[0]) && Integer.parseInt(line[3]) <= Integer.parseInt(line[1]))
				score++;
		}
		return score;
	}

	private static ArrayList<String[]> fixInput(ArrayList<String[]> input) {
		ArrayList<String[]> fixed = new ArrayList<>();
		for (String[] line : input) {
			fixed.add(new String[] {line[0].split("-")[0], line[0].split("-")[1], line[1].split("-")[0], line[1].split("-")[1]});
		}
		return fixed;
	}
}
