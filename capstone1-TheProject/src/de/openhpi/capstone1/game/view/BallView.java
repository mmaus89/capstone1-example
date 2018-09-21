package de.openhpi.capstone1.game.view;

import de.openhpi.capstone1.game.model.Ball;
import processing.core.PApplet;

public class BallView extends AbstractView{
	private Ball ball;
	
	public BallView(PApplet display, Ball ball) {
		super(display);
		this.ball = ball;
	}

	@Override
	public void update() {
		int x = ball.getX();
		int y = ball.getY();
		int width = ball.getWidth();
		int height = ball.getHeight();
		
		this.display.ellipse(x, y, width, height);
	}

}
