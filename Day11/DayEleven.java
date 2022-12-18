package Day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayEleven {
	public static void main(String[] args) {
		ArrayList<Monkey> input = getInput();
		System.out.printf("Part 1: %d%n", partOne(cloneArrayList(input)));
		System.out.printf("Part 2: %d%n", partTwo(cloneArrayList(input)));
	}

	private static ArrayList<Monkey> getInput() {
		ArrayList<Monkey> input = new ArrayList<>();
		ArrayList<Long> itemsWorryLevel = new ArrayList<>();
		int index = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day11/inputDay11.txt")));
			while (reader.ready()) {
				reader.readLine();
				String[] itemsLine = reader.readLine().substring(18).split(", ");
				ArrayList<Integer> items = new ArrayList<>();
				for (String item : itemsLine) {
					itemsWorryLevel.add(Long.parseLong(item) % 9699690);  //9699690;
					items.add(index);
					index++;
				}
				String operation = reader.readLine();
				boolean isPlus = operation.contains("+");
				long operationNum = isNumberic(operation.substring(25)) ? Integer.parseInt(operation.substring(25)) : -1;
				long testNum = Integer.parseInt(reader.readLine().substring(21));
				int testTrue = Integer.parseInt(reader.readLine().substring(29));
				int testFalse = Integer.parseInt(reader.readLine().substring(30));
				input.add(new Monkey(items, isPlus, operationNum, testNum, testTrue, testFalse));
				reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Monkey.setitemsWorryLevel(itemsWorryLevel);
		return input;
	}
	
	private static long partOne(ArrayList<Monkey> input) {
		return simulateRounds(input, 20);
	}
	
	private static long partTwo(ArrayList<Monkey> input) {
		return simulateRounds(input, 10000);
	}
	
	private static long simulateRounds(ArrayList<Monkey> monkeys, int rounds) {
		ArrayList<Long> scores = new ArrayList<>();
		
		for (int round=0; round<rounds; round++) {
			for (int j=0; j<monkeys.size(); j++) {
				Monkey monk = monkeys.get(j);
				
				ArrayList<Integer> items = new ArrayList<>(monk.getItems());
				for (int itemIndex : items) {
					while (j >= scores.size()) 
						scores.add(0l);
					scores.set(j, scores.get(j) + 1);
					
					int worry = 1;
					if (rounds<1000) worry = 3;

					long otherNum = monk.getOperationNum();
					if (otherNum == -1)
						otherNum = Monkey.getitemsWorryLevel(itemIndex);

					long milansNumber = Monkey.getitemsWorryLevel(itemIndex) % 9699690;
					
					long newItem = monk.isOperationPlus() ? ((milansNumber + otherNum) / worry) : ((milansNumber * otherNum) / worry);
					
					Monkey.addActionToItem(itemIndex, newItem);
					monk.removeItem(itemIndex);

					if (newItem % monk.getTestNum() == 0)
						monkeys.get(monk.getTestTrue()).addItem(itemIndex);
					else
						monkeys.get(monk.getTestFalse()).addItem(itemIndex);
				}
			}
		}
		
		scores.sort(null);
		return scores.get(scores.size()-1) * (scores.get(scores.size()-2));
	}
		
	private static boolean isNumberic(String input) {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	
	private static ArrayList<Monkey> cloneArrayList(ArrayList<Monkey> list) {
			ArrayList<Monkey> clonedList = new ArrayList<>();
			for (Monkey monk : list) {
				clonedList.add(monk.clone());
			}
			return clonedList;
		}
}
	