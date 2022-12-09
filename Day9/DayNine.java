package Day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayNine {
	public static void main(String[] args) {
		ArrayList<int[]> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static ArrayList<int[]> getInput() {
		ArrayList<int[]> input = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day9/inputDay9.txt")));
			while (reader.ready()) {
				String[] line = reader.readLine().split(" ");
				int[] row = new int[2];
				row[1] = Integer.parseInt(line[1]);
				if (line[0].equals("U"))
					row[0] = 0;
				else if (line[0].equals("D"))
					row[0] = 1;
				else if (line[0].equals("L"))
					row[0] = 2;
				else if (line[0].equals("R"))
					row[0] = 3;
				input.add(row);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private static int partOne(ArrayList<int[]> input) {
		return doStuff(input, 2);
	}

	private static int partTwo(ArrayList<int[]> input) {
		return doStuff(input, 10);
	}

	private static int doStuff(ArrayList<int[]> input, int size) {
		int score = 0;
		boolean[][] hasBeen = new boolean[1000][1000];
		Position[] knots = new Position[size];
		for (int i=0; i<knots.length; i++) {
			knots[i] = new Position(500, 500);
		}

		hasBeen[knots[knots.length-1].getX()][knots[knots.length-1].getY()] = true;
		for (int[] line : input) {
			for (int i=0; i<line[1]; i++) {
				if (line[0] == 0) { // Moving Up
					knots[0].xMinusMinus();
				}
				else if (line[0] == 1) { // Moving Down
					knots[0].xPlusPlus();
				}
				else if (line[0] == 2) { // Moving Left
					knots[0].yMinusMinus();
				}
				else if (line[0] == 3) { // Moving Right
					knots[0].yPlusPlus();
				}
				updateTail(knots);
				hasBeen[knots[knots.length-1].getX()][knots[knots.length-1].getY()] = true;
			}
		}
		for (boolean[] hasBeenRow : hasBeen) {
			for (boolean hasBeenHere : hasBeenRow) {
				if (hasBeenHere)
					score++;
			}
		}
		return score;
	}

	private static void updateTail(Position[] knots) {
		for (int j=0; j<knots.length-1; j++) {
			if (!checkNear(knots[j], knots[j+1])) {
				int rowDirection = knots[j].getX() - knots[j+1].getX();
				int colDirection = knots[j].getY() - knots[j+1].getY();

				if (colDirection > 0) knots[j+1].yPlusPlus();
				else if (colDirection < 0) knots[j+1].yMinusMinus();

				if (rowDirection > 0) knots[j+1].xPlusPlus();
				else if (rowDirection < 0) knots[j+1].xMinusMinus();
			}
		}
	}

	private static boolean checkNear(Position head, Position tail) {
		if (Math.abs(head.getX() - tail.getX()) <= 1 && Math.abs(head.getY() - tail.getY()) <= 1)
			return true;
		return false;
	}
}
