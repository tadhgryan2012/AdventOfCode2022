package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayTen {
	public static void main(String[] args) {
		ArrayList<String[]> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %n%s", partTwo(input));
	}
	
	private static ArrayList<String[]> getInput() {
		ArrayList<String[]> input = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day10/inputDay10.txt")));
			while (reader.ready()) {
				String[] line = reader.readLine().split(" ");
				input.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private static int partOne(ArrayList<String[]> input) {
		int output = 0;
		Integer instructionNumber = null;
		
		for (int cycleNo=1, offset=cycleNo, timeout = 0, x = 1; cycleNo<221; cycleNo++) {
			
			if ((cycleNo+20)%40 == 0) {
				output += cycleNo * x;
			}
			
			if (instructionNumber != null) {
				x += instructionNumber;
				instructionNumber = null;
			}
			if (timeout == 0) {
				if (cycleNo-offset < input.size()) {
					String[] instruction = input.get(cycleNo-offset);
					
					if (instruction[0].equals("addx")) {
						instructionNumber = Integer.parseInt(instruction[1]);
						timeout = 1;
					}
				}
			} else {
				offset++;
				timeout--;
			}
		}
		
		return output;
	}
	
	private static String partTwo(ArrayList<String[]> input) {
		Integer instructionNumber = null;
		StringBuilder output = new StringBuilder();
		
		for (int cycleNo=1, offset=cycleNo, timeout = 0, x = 1; cycleNo<241; cycleNo++) {
			System.out.printf("Cycle Number: %3d\t%2d <= %2d >= %2d-> %b%n", cycleNo, (cycleNo%40)-2, x, cycleNo%40, (cycleNo%40)-2 <= x && cycleNo%40 >= x);
			if ((cycleNo%40)-2 <= x && (cycleNo%40) >= x) {
				output.append("#");
			} else {
				output.append(".");
			}
			
			if (cycleNo%40 == 0 && cycleNo >= 30) {
				output.append("\n");
			}
			
			if (instructionNumber != null) {
				x += instructionNumber;
				instructionNumber = null;
			}
			if (timeout == 0) {
				if (cycleNo-offset < input.size()) {
					String[] instruction = input.get(cycleNo-offset);
					
					if (instruction[0].equals("addx")) {
						instructionNumber = Integer.parseInt(instruction[1]);
						timeout = 1;
					}
				}
			} else {
				offset++;
				timeout--;
			}
		}
		
		return output.toString();
	}
}
