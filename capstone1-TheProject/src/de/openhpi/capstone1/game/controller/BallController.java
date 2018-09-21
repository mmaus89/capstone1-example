package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.BoardController.BoardCollision;
import de.openhpi.capstone1.game.controller.CollisionHandler.CollisionResult;
import de.openhpi.capstone1.game.controller.PaddleController.PaddleCollision;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Board;

public class BallController implements Controller {

	private boolean jumpReverseX = false;
	private boolean jumpReverseY = false;

	private Ball ball;
	private int stepSize = 0;
	private CollisionHandler collisionHandler;

	public BallController(Ball ball, int stepSize, Board board, CollisionHandler collisionHandler) {
		this.ball = ball;
		this.stepSize = stepSize;
		this.collisionHandler = collisionHandler;
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		switch (event) {
		case PHYSICS:
			handlePhysics();
			break;
		default:
			break;
		}
	}

	private void handlePhysics() {
		CollisionResult result = collisionHandler.checkCollision(ball.getX(), ball.getY());
		if (!result.isEmpty()) {
			if (result.getCollisionType().isAssignableFrom(BoardController.class)) {
				BoardCollision boardCollision = (BoardCollision) result.getArgument();
				if (boardCollision.hasTopBoardCollision()) {
					jumpReverseY = false;
				}

				if (boardCollision.hasLeftBoardCollision()) {
					jumpReverseX = false;
				}

				if (boardCollision.hasRightBoardCollision()) {
					jumpReverseX = true;
				}

				if (boardCollision.hasBottomBoardCollision()) {
					jumpReverseY = true;
				}
			} else if (result.getCollisionType().isAssignableFrom(PaddleController.class)) {
				PaddleCollision paddleCollision = (PaddleCollision) result.getArgument();

				if (paddleCollision.equals(PaddleCollision.COLLISION_ON_TOP)) {
					jumpReverseY = false;
				}
				if (paddleCollision.equals(PaddleCollision.COLLISION_ON_BOTTOM)) {
					jumpReverseY = true;
				}
			} else if (result.getCollisionType().isAssignableFrom(BreaksController.class)) {
				Boolean breakHit = (Boolean) result.getArgument();
				if (breakHit) {
					jumpReverseY = !jumpReverseY;
				}
			}
		}

		ball.setX(ball.getX() + (jumpReverseX ? -1 : 1) * stepSize);
		ball.setY(ball.getY() + (jumpReverseY ? -1 : 1) * stepSize);
	}

	public void inverseXY(boolean reverseX, boolean reverseY) {
		this.jumpReverseX = reverseX;
		this.jumpReverseY = reverseY;
	}
}
