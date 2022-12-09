package Day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayEight {
	public static void main(String[] args) {
		int[][] input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static int[][] getInput() {
		ArrayList<int[]> input = new ArrayList<>();
		int[][] actualInput = null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day8/inputDay8.txt")));
			while (reader.ready()) {
				char[] line = reader.readLine().toCharArray();
				int[] row = new int[line.length];
				for (int i = 0; i < line.length; i++) {
					row[i] = Character.getNumericValue(line[i]);
				}
				input.add(row);
			}
			reader.close();
			actualInput = input.toArray(new int[input.size()][]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualInput;
	}
	
	private static int partOne(int[][] input) {
		int visable = 0;
		visable += (input.length * 2) + (input[0].length * 2) - 4;
		
		for (int i=1; i<input.length-1; i++) {
			for (int j=1; j<input[i].length-1; j++) {
				if (checkVisable(input, i, j)) {
					visable++;
				}
			}
		}
		
		return visable;
	}

	private static int partTwo(int[][] input) {
		int maxScore = 0;
		for (int i=0; i<input.length; i++) {
			for (int j=0; j<input[i].length; j++) {
				int newScore;
				if ((newScore = getScore(input, i, j)) > maxScore) 
					maxScore = newScore;
			}
		}

		return maxScore;
	}
	
	private static int getScore(int[][] input, int x, int y) {
		int visable = 1;

		int valueOfLine = 0;
		for (int i=x+1; i<input.length; i++) {
			if (input[i][y] >= input[x][y]) {
				valueOfLine++;
				break;
			}
			valueOfLine++;
		}
		visable *= valueOfLine;

		valueOfLine = 0;
		for (int i=x-1; i>=0; i--) {
			if (input[i][y] >= input[x][y]) {
				valueOfLine++;
				break;
			}
			valueOfLine++;
		}
		visable *= valueOfLine;

		valueOfLine = 0;
		for (int i=y+1; i<input[0].length; i++) {
			if (input[x][i] >= input[x][y]) {
				valueOfLine++;
				break;
			}
			valueOfLine++;
		}
		visable *= valueOfLine;

		valueOfLine = 0;
		for (int i=y-1; i>=0; i--) {
			if (input[x][i] >= input[x][y]) {
				valueOfLine++;
				break;
			}
			valueOfLine++;
		}
		visable *= valueOfLine;
		return visable;
	}
	
	private static boolean checkVisable(int[][] input, int x, int y) {
		boolean visable = true;
		for (int i=x+1; i<input.length; i++) {
			if (input[i][y] >= input[x][y]) {
				visable = false;
				break;
			}
		}
		if (visable) return visable;
		visable = true;
		for (int i=x-1; i>=0; i--) {
			if (input[i][y] >= input[x][y]) {
				visable = false;
				break;
			}
		}
		if (visable) return visable;
		visable = true;
		for (int i=y+1; i<input.length; i++) {
			if (input[x][i] >= input[x][y]) {
				visable = false;
				break;
			}
		}
		if (visable) return visable;
		visable = true;
		for (int i=y-1; i>=0; i--) {
			if (input[x][i] >= input[x][y]) {
				visable = false;
				break;
			}
		}
		return visable;
	}
	
}
