package de.openhpi.capstone1.game.view;

import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.SpaceShip;
import processing.core.PApplet;

public class SpaceShipView extends AbstractView {

	private SpaceShip spaceShip;
	private Board board;

	public SpaceShipView(PApplet display, SpaceShip spaceShip,Board board) {
		super(display);
		this.spaceShip = spaceShip;
		this.board = board;
	}

	@Override
	public void update() {
		int x = spaceShip.getX();
		int y = spaceShip.getHeight();
		int spaceShipWidth = spaceShip.getWidth();

		int x1 = x ;
		int y1 = board.getHeight() - 0;

		int x2 = x + spaceShipWidth / 2;
		int y2 = board.getHeight() - y;

		int x3 = x + spaceShipWidth;
		int y3 = board.getHeight() - 0;
		display.triangle(x1, y1, x2, y2, x3, y3);
	}
}
