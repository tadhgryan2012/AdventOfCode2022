package Day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DayFive {
	@SuppressWarnings("unchecked") private static LinkedList<String>[] warehouse = new LinkedList[9];
	public static void main(String[] args) {
		ArrayList<int[]> orders = getInput();
		System.out.printf("Part 1: %s%n", partOne(orders));
		System.out.printf("Part 2: %s%n", partTwo(orders));
	}
	
	private static ArrayList<int[]> getInput() {
		ArrayList<int[]> input = new ArrayList<>();
		ArrayList<String> crates = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Day5/inputDay5.txt")));
			boolean atOrders = false;
			while (reader.ready()) {
				if (atOrders) {
					String line = reader.readLine();
					int[] order = new int[] {Integer.parseInt(line.substring(5, line.indexOf(" from"))), 
					Integer.parseInt(line.substring(line.indexOf(" from")+6, line.indexOf(" to"))), 
					Integer.parseInt(line.substring(line.indexOf(" to ")+4))
				};
				input.add(order);
				} else {
					String line = reader.readLine();
					if (line.isBlank()) {
						atOrders = true;
					} else {
						crates.add(line);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String line : crates) {
			String whitespace = "";
			int counter = 0;
			String[] layer = line.split("\\[");
			for (int i = 0; i < layer.length; i++) {
				if (layer[i].isBlank()) continue;
				String letter = layer[i].charAt(0) + "";

				int index = i - 1;
				if (whitespace.length() > 1) {
					index += ((whitespace.length()-1) / 4) + counter;
					counter++;
				}
				if (warehouse[index] == null)
				warehouse[index] = new LinkedList<>();
				warehouse[index].addLast(letter);
				whitespace = layer[i].substring(2);
			}
		}
		return input;
	}

	private static String partOne(ArrayList<int[]> input) {
		LinkedList<String>[] warehouseTemp = new LinkedList[warehouse.length];
		for (int i = 0; i < warehouse.length; i++) {
			warehouseTemp[i] = new LinkedList<>();
			for (String element : warehouse[i]) {
				warehouseTemp[i].add(element);
			}
		}
		StringBuilder answer = new StringBuilder();

		for (int[] orders : input) {
			for (int i = 0; i < orders[0]; i++) {
				int from = orders[1] - 1;
				int to = orders[2] - 1;

				warehouseTemp[to].push(warehouseTemp[from].pop());
			}
		}

		for (LinkedList<String> stack : warehouseTemp) {
			answer.append(stack.peek());
		}

		return answer.toString();
	}

	private static String partTwo(ArrayList<int[]> input) {
		LinkedList<String>[] warehouseTemp = new LinkedList[warehouse.length];
		for (int i = 0; i < warehouse.length; i++) {
			warehouseTemp[i] = new LinkedList<>();
			for (String element : warehouse[i]) {
				warehouseTemp[i].add(element);
			}
		}
		StringBuilder answer = new StringBuilder();

		for (int[] orders : input) {
			int from = orders[1] - 1;
			int to = orders[2] - 1;
			ArrayList<String> toMove = new ArrayList<>();
			for (int i = 0; i < orders[0]; i++) {
				toMove.add(warehouseTemp[from].pop());
			}
			for (int i = toMove.size()-1; i >= 0; i--) {
				warehouseTemp[to].push(toMove.get(i));
			}
		}

		for (LinkedList<String> stack : warehouseTemp) {
			answer.append(stack.peek());
		}

		return answer.toString();
	}
}