package de.openhpi.capstone1.game.model;

public class Ball implements Dimension{

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Ball(int width,int height) {
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
