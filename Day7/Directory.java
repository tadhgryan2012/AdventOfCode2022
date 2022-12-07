package Day7;

import java.util.ArrayList;

public class Directory implements Comparable<Directory> {
	private int size;
	private ArrayList<Directory> directories;
	private String name;

	public Directory(String name) {
		this.name = name;
		directories = new ArrayList<>();
		size = 0;
	}
	public Directory(int size, ArrayList<Directory> directories, String name) {
		this.size = size;
		this.directories = directories;
		this.name = name;
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Directory> getDirectories() {
		return directories;
	}
	public void setDirectories(ArrayList<Directory> directories) {
		this.directories = directories;
	}
	public void addDirectory(Directory directory) {
		this.directories.add(directory);
	}
	public Directory getDirectory(String dirName) {
		for (Directory dir : directories) {
			if (dirName.equals(dir.name)) return dir;
		}
		return null;
	}
	public void getSizesOfChildren() {
		for (Directory dir : directories) {
			size += dir.getSize();
		}
	}
	@Override
	public int compareTo(Directory o) {
		if (size > o.size) return 1;
		else if (size == o.size) return 0;
		else if (size < o.size) return -1;
		return 0;
	}
}
