package Day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DaySix {
	public static void main(String[] args) {
		ArrayList<Character> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}
	
	private static ArrayList<Character> getInput() {
		ArrayList<Character> input = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day6/inputDay6.txt")));
			while (reader.ready()) {
				char[] line = reader.readLine().toCharArray();
				for (char ch : line) {
					input.add(ch);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private static int partOne(ArrayList<Character> input) {
		int score = 0;

		LinkedList<Character> chars = new LinkedList<Character>();
		for (int i=0; i< 4; i++) {
			chars.add(input.get(i));
		}

		for (int i=4; i < input.size(); i++) {
			if (noDups(chars, 4)) {
				score += i;
				break;
			}

			chars.remove();
			chars.add(input.get(i));
		}

		return score;
	}
	private static int partTwo(ArrayList<Character> input) {
		int score = 0;

		LinkedList<Character> chars = new LinkedList<Character>();
		for (int i=0; i< 14; i++) {
			chars.add(input.get(i));
		}

		for (int i=14; i < input.size(); i++) {
			if (noDups(chars, 14)) {
				score += i;
				break;
			}

			chars.remove();
			chars.add(input.get(i));
		}

		return score;
	}

	private static boolean noDups(LinkedList<Character> chars, int size) {
		for (int i=0; i < chars.size(); i++) {
			for (int j=0; j < size; j++) {
				if (chars.get(i).equals(chars.get(j)) && i != j) return false;
			}
		}
		return true;
	}
}
