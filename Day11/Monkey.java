package Day11;

import java.util.ArrayList;

public class Monkey implements Cloneable {
	private static ArrayList<Long> itemsWorryLevel;
	private ArrayList<Integer> items;
	private boolean operationPlus;
	private long operationNum;
	private long testNum;
	private int testTrue;
	private int testFalse;
	
	public Monkey(ArrayList<Integer> items, boolean operationPlus, long operationNum, long testNum, int testTrue, int testFalse) {
		this.items = items;
		this.operationPlus = operationPlus;
		this.operationNum = operationNum;
		this.testNum = testNum;
		this.testTrue = testTrue;
		this.testFalse = testFalse;
	}
	
	public static Long getitemsWorryLevel(int index) {
		return itemsWorryLevel.get(index);
	}
	public static void setitemsWorryLevel(ArrayList<Long> itemsWorryLevel) {
		Monkey.itemsWorryLevel = itemsWorryLevel;
	}
	public static void addActionToItem(int index, long itemToAdd) {
		Monkey.itemsWorryLevel.set(index, itemToAdd);
	}

	public ArrayList<Integer> getItems() {
		return items;
	}
	public void setItems(ArrayList<Integer> items) {
		this.items = items;
	}
	public void addItem(int item) {
		items.add(item);
	}
	public void removeItem(int itemToRemove) {
		items.remove(items.indexOf(itemToRemove));
	}

	public boolean isOperationPlus() {
		return operationPlus;
	}
	public void setOperationPlus(boolean operationPlus) {
		this.operationPlus = operationPlus;
	}

	public long getOperationNum() {
		return operationNum;
	}
	public void setOperationNum(long operationNum) {
		this.operationNum = operationNum;
	}

	public long getTestNum() {
		return testNum;
	}
	public void setTestNum(long testNum) {
		this.testNum = testNum;
	}

	public int getTestTrue() {
		return testTrue;
	}
	public void setTestTrue(int testTrue) {
		this.testTrue = testTrue;
	}

	public int getTestFalse() {
		return testFalse;
	}
	public void setTestFalse(int testFalse) {
		this.testFalse = testFalse;
	}

    @Override
    public Monkey clone() {
		try {
        	Monkey monk = (Monkey) super.clone();
			monk.items = new ArrayList<>(items);
			monk.operationNum = operationNum;
			monk.operationPlus = operationPlus;
			monk.testTrue = testTrue;
			monk.testFalse = testFalse;
			monk.testNum = testNum;
			return monk;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
    }
}
