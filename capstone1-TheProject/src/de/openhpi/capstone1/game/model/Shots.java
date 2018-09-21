package de.openhpi.capstone1.game.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Shots {

	private int width;
	private int height;
	private List<Shot> shots = new CopyOnWriteArrayList<>();

	public Shots(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public void addShot(Shot shot) {
		shots.add(shot);
	}

	public List<Shot> getShots() {
		return shots;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public static class Shot {
		private int x;
		private int y;
		
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
	}

	public void removeShot(Shot shot) {
		shots.remove(shot);
	}
}
