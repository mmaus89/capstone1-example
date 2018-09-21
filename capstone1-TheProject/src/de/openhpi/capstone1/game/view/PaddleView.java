package de.openhpi.capstone1.game.view;

import de.openhpi.capstone1.game.model.Paddle;
import processing.core.PApplet;

public class PaddleView extends AbstractView {

	private Paddle paddle;

	public PaddleView(PApplet display, Paddle paddle) {
		super(display);
		this.paddle = paddle;
	}

	@Override
	public void update() {
		int x = paddle.getX();
		int y = paddle.getY();
		int width = paddle.getWidth();
		int height = paddle.getHeight();

		display.rect(x, y, width, height);
	}

}
