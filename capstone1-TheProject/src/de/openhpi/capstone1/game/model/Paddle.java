package de.openhpi.capstone1.game.model;

public class Paddle implements Dimension{

	private int width;
	private int height;
	private int x;
	private int y;
	
	public Paddle(int width,int height){
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
