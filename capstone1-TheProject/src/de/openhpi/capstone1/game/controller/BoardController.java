package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.model.Board;

public class BoardController implements Controller {
	private Board board;
	private CollisionHandler collisionHandler;

	public BoardController(Board board, CollisionHandler collisionHandler) {
		this.board = board;
		this.collisionHandler = collisionHandler;

		this.collisionHandler.registerCollisionChecker(getClass(),this::checkCollision);
	}

	private BoardCollision checkCollision(int x, int y) {
		int x1 = board.getX();
		int x2 = x1 + board.getWidth();
		int y1 = board.getY();
		int y2 = y1 + board.getHeight();

		// LEFT x<=x1
		boolean left = x <= x1;

		// TOP y<=y1
		boolean top = y <= y1;

		// RIGHT x2<=x
		boolean right = x2 <= x;

		// BOTTOM y2<=y
		boolean bottom = y2 <= y;

		boolean hasCollision = left || top || right || bottom;
		return hasCollision ? new BoardCollision(left, top, right, bottom) : null;
	}

	public static class BoardCollision {

		private boolean left;
		private boolean top;
		private boolean right;
		private boolean bottom;

		private BoardCollision(boolean left, boolean top, boolean right, boolean bottom) {
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
		}

		public boolean hasTopBoardCollision() {
			return top;
		}

		public boolean hasLeftBoardCollision() {
			return left;
		}

		public boolean hasRightBoardCollision() {
			return right;
		}

		public boolean hasBottomBoardCollision() {
			return bottom;
		}
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		// TODO Auto-generated method stub

	}
}
