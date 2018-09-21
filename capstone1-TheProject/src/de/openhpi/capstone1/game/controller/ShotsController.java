package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Paddle;
import de.openhpi.capstone1.game.model.Shots;

public class ShotsController implements Controller {

	private Shots shots;
	private Board board;
	private Paddle paddle;

	private int stepSize = 5;
	private CollisionHandler collisionHandler;

	public ShotsController(Shots shots, Board board, Paddle paddle, CollisionHandler collisionHandler) {
		this.shots = shots;
		this.board = board;
		this.paddle = paddle;

		this.collisionHandler = collisionHandler;
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		Shots.Shot shot = new Shots.Shot();
		shots.addShot(shot);
		shot.setX(paddle.getX() + paddle.getWidth() / 2);
		shot.setY(board.getHeight() - paddle.getHeight());

		Thread shotsPhysics = new Thread(() -> {
			while (shot.getY() >= 0) {
				shot.setY(shot.getY() - stepSize);
				try {
					if (!collisionHandler.checkCollision(shot.getX(), shot.getY()).isEmpty()) {
						break;
					}
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			shots.removeShot(shot);
		});
		shotsPhysics.start();
	}

}
