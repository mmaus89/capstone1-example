package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Paddle;

public class PaddleController implements Controller {

	private static final int KEY_FORWARD = 39;
	private static final int KEY_BACKWARD = 37;
	
	private Paddle paddle;
	private Board board;
	private PaddleCollision paddleCollision;
	private InteractiveEvent interactiveEvent;
	private int stepSize = 5;
	
	protected PaddleController(Paddle paddle, Board board, CollisionHandler collisionHandler,
			PaddleCollision paddleCollision, InteractiveEvent interactiveEvent) {
		this.paddle = paddle;
		this.board = board;
		this.paddleCollision = paddleCollision;
		this.interactiveEvent = interactiveEvent;

		collisionHandler.registerCollisionChecker(PaddleController.class, this::checkCollision);
	}

	private PaddleCollision checkCollision(int x, int y) {
		int x1 = paddle.getX();
		int x2 = x1 + paddle.getWidth();
		int y1 = paddle.getY();
		int y2 = y1 + paddle.getHeight();

		boolean hasCollision = x1 <= x && x <= x2 && y1 <= y && y <= y2;
		return hasCollision ? paddleCollision : null;
	}

	public static enum PaddleCollision {
		COLLISION_ON_TOP, COLLISION_ON_BOTTOM
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		if (!interactiveEvent.equals(event)) {
			return;
		}

		switch (event) {
		case MOUSE_HOVER:
			int x = (int) arguments[0];
			handleMouseHoverEvent(x);
		case KEY_PRESS:


			boolean isForward = (int) arguments[0] == KEY_FORWARD;
			boolean isBackward = (int) arguments[0] == KEY_BACKWARD;

			int xKeyPress = paddle.getX();
			if (isForward) {
				xKeyPress += stepSize;
			} else if (isBackward) {
				xKeyPress -= stepSize;
			} else {
				return;
			}

			handleKeyPressEvent(xKeyPress);
			break;
		default:
			break;
		}
	}

	private void handleMouseHoverEvent(int x) {
		int width = paddle.getWidth();

		int paddleX = x - width / 2;
		if (paddleX < 0) {
			paddleX = 0;
		}
		if (paddleX + width > board.getWidth()) {
			paddleX = board.getWidth() - width;
		}
		paddle.setX(paddleX);
	}

	private void handleKeyPressEvent(int x) {
		int width = paddle.getWidth();
		int paddleX = x;
		if (paddleX < 0) {
			paddleX = 0;
		}
		if (paddleX + width > board.getWidth()) {
			paddleX = board.getWidth() - width;
		}
		paddle.setX(paddleX);
	}
	
	public static class PaddleControllerBuilder{
		private Paddle paddle;
		private Board board;
		private CollisionHandler collisionHandler;
		private PaddleCollision paddleCollision = PaddleCollision.COLLISION_ON_BOTTOM;
		private InteractiveEvent interactiveEvent;

		public PaddleControllerBuilder appendPaddle(Paddle paddle) {
			this.paddle = paddle;
			return this;
		}
		
		public PaddleControllerBuilder appendPaddleCollisionType(PaddleCollision paddleCollision) {
			this.paddleCollision = paddleCollision;
			return this;
		}
		
		public PaddleControllerBuilder appendBoard(Board board) {
			this.board = board;
			return this;
		}
		
		public PaddleControllerBuilder appendCollisionHandler(CollisionHandler collisionHandler) {
			this.collisionHandler = collisionHandler;
			return this;
		}
		
		public PaddleControllerBuilder appendInteractiveEvent(InteractiveEvent event) {
			this.interactiveEvent = event;
			return this;
		}
		
		public PaddleController build() {
			if (paddle != null) {
				throw new IllegalArgumentException("Paddle is not Initialized");
			}
			if (board != null) {
				throw new IllegalArgumentException("Board is not Initialized");
			}
			if (collisionHandler != null) {
				throw new IllegalArgumentException("CollisionHandler is not Initialized");
			}
			if (paddleCollision != null) {
				throw new IllegalArgumentException("PaddleCollision is not Initialized");
			}
			if (interactiveEvent != null) {
				throw new IllegalArgumentException("InteractiveEvent is not Initialized");
			}

			return new PaddleController(paddle, board, collisionHandler, paddleCollision, interactiveEvent);
		}
	}
}
