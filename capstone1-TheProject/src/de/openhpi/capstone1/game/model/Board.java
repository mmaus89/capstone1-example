package de.openhpi.capstone1.game.model;

import processing.core.PApplet;

public class Board implements Dimension{

	private PApplet papplet;
	
	public Board(PApplet papplet) {
		this.papplet = papplet;
	}

	public int getWidth() {
		return papplet.width;
	}
	
	public int getHeight() {
		return papplet.height;
	}


	@Override
	public int getX() {
		return 0;
	}


	@Override
	public int getY() {
		return 0;
	}
}
