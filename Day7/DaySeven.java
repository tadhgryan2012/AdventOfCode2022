package Day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class DaySeven {
	public static void main(String[] args) {
		ArrayList<String> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(input));
		System.out.printf("Part 2: %d%n", partTwo(input));
	}
	
	private static ArrayList<String> getInput() {
		ArrayList<String> input = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day7/inputDay7.txt")));
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
		ArrayList<Directory> dirs = new ArrayList<>();
		int score = 0;

		makeTreeMap(input, dirs);

		for (Directory dir : dirs) {
			if (dir.getSize() <= 100000) {
				score += dir.getSize();
			}
		}
		return score;
	}

	private static int partTwo(ArrayList<String> input) {
		int score = 0;

		ArrayList<Directory> dirs = new ArrayList<>();
		makeTreeMap(input, dirs);

		int sizeNeededToDelete = 0;
		for (Directory dir : dirs) {
			if (dir.getName().equals("/")) {
				sizeNeededToDelete = dir.getSize() - 40000000;
				break;
			}
		}
		dirs.sort(null);
		for (Directory dir : dirs) {
			if (dir.getSize() >= sizeNeededToDelete) {
				score = dir.getSize();
				break;
			}
		}

		return score;
	}
	
	private static void makeTreeMap(ArrayList<String> input, ArrayList<Directory> dirSizes) {
		Stack<Directory> curPath = new Stack<>();
		curPath.push(new Directory("/"));
		for (int i=2; i<input.size(); i++) {
			String line = input.get(i);
			if (line.startsWith("$ cd ") && !line.substring(5).equals("..")) {
				curPath.push(curPath.peek().getDirectory(line.substring(5)));
				i += 1;
			} else if (line.startsWith("dir ")) {
				Directory newDir = new Directory(line.substring(4));
				curPath.peek().addDirectory(newDir);
			} else if (line.contains("$ cd ..")) {
				curPath.peek().getSizesOfChildren();
				dirSizes.add(curPath.peek());

				curPath.pop();
			} else {
				try {
					curPath.peek().setSize(Integer.parseInt(line.split(" ")[0]) + curPath.peek().getSize());
				} catch (NumberFormatException e) {}
			}
		}
	}
}
