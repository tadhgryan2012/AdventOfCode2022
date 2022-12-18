package Day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayTwelve {
	private static int[] start = new int[2];
	private static int[] end = new int[2];
	public static void main(String[] args) {
		int[][] input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}

	private static int[][] getInput() {
		int[][] input = new int[41][61];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day12/inputDay12.txt")));
			int row=0;
			while (reader.ready()) {
				char[] line = reader.readLine().toCharArray();
				for (int col=0; col<line.length; col++) {
					if (line[col] == 'S') {
						start[0] = row;
						start[1] = col;
						input[row][col] = 0;
					} else if (line[col] == 'E') {
						end[0] = row;
						end[1] = col;
						input[row][col] = 25;
					} else 
						input[row][col] = Character.valueOf(line[col]) - 'a';
				}
				row++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private static int partOne(int[][] input) {
		int steps = 0;
		boolean[][] beenHere = new boolean[input.length][input[0].length];
		ArrayList<int[]> currentlySearching = new ArrayList<>();
		currentlySearching.add(start);
		beenHere[start[0]][start[1]] = true;

		for (steps = 0; true; steps++) {
			ArrayList<int[]> newSearching = new ArrayList<>();
			
			for (int[] position : currentlySearching) {
				int row = position[0];
				int col = position[1];
				
				// Top
				if (row != 0) {
					if (!beenHere[row-1][col] && input[row-1][col]-1 <= input[row][col]) {
						if (row-1 == end[0] && col == end[1]) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row-1, col});
							beenHere[row-1][col] = true;
						}
					}
				}
				// Bottom
				if (row != input.length-1) {
					if (!beenHere[row+1][col] && input[row+1][col]-1 <= input[row][col]) {
						if (row+1 == end[0] && col == end[1]) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row+1, col});
							beenHere[row+1][col] = true;
						}
					}
				}
				// Left
				if (col != 0) {
					if (!beenHere[row][col-1] && input[row][col-1]-1 <= input[row][col]) {
						if (row == end[0] && col-1 == end[1]) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row, col-1});
							beenHere[row][col-1] = true;
						}
					}
				}
				// Right
				if (col != input[0].length-1) { 
					if (!beenHere[row][col+1] && input[row][col+1]-1 <= input[row][col]) {
						if (row == end[0] && col+1 == end[1]) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row, col+1});
							beenHere[row][col+1] = true;
						}
					}
				}
			}

			currentlySearching = new ArrayList<>();
			currentlySearching.addAll(newSearching);
		}
	}
	
	private static int partTwo(int[][] input) {
		int steps = 0;
		boolean[][] beenHere = new boolean[input.length][input[0].length];
		ArrayList<int[]> currentlySearching = new ArrayList<>();
		currentlySearching.add(end);
		beenHere[end[0]][end[1]] = true;

		for (steps = 0; true; steps++) {
			ArrayList<int[]> newSearching = new ArrayList<>();
			
			for (int[] position : currentlySearching) {
				int row = position[0];
				int col = position[1];

				System.out.printf("Current searching: %2d, %2d%n", row, col);
				
				// Top
				if (row != 0) {
					if (!beenHere[row-1][col] && input[row-1][col]+1 >= input[row][col]) {
						if (input[row-1][col] == 0) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row-1, col});
							beenHere[row-1][col] = true;
						}
					}
				}
				// Bottom
				if (row != input.length-1) {
					if (!beenHere[row+1][col] && input[row+1][col]+1 >= input[row][col]) {
						if (input[row+1][col] == 0) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row+1, col});
							beenHere[row+1][col] = true;
						}
					}
				}
				// Left
				if (col != 0) {
					if (!beenHere[row][col-1] && input[row][col-1]+1 >= input[row][col]) {
						if (input[row][col-1] == 0) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row, col-1});
							beenHere[row][col-1] = true;
						}
					}
				}
				// Right
				if (col != input[0].length-1) { 
					if (!beenHere[row][col+1] && input[row][col+1]+1 >= input[row][col]) {
						if (input[row][col+1] == 0) {
							return steps+1;
						} else {
							newSearching.add(new int[]{row, col+1});
							beenHere[row][col+1] = true;
						}
					}
				}
			}

			currentlySearching = new ArrayList<>();
			currentlySearching.addAll(newSearching);
		}
	}
}
