package Day9;

public class Position {
	private int x;
	private int y;

	public Position() {
		x = 0;
		y = 0;
	}
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void xMinusMinus() {
		this.x--;
	}
	public void xPlusPlus() {
		this.x++;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void yMinusMinus() {
		this.y--;
	}
	public void yPlusPlus() {
		this.y++;
	}
}
