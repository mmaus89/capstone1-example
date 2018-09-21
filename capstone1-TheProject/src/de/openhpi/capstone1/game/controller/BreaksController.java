package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Breaks;
import de.openhpi.capstone1.game.model.Breaks.Break;

public class BreaksController implements Controller{
	private Breaks breaks;
	private Board board;

	private boolean reversePos = false;
	
	public BreaksController(Breaks breaks, Board board, CollisionHandler collisionHandler) {
		this.breaks = breaks;
		this.board = board;
		
		collisionHandler.registerCollisionChecker(BreaksController.class, this::checkCollision);
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		if (event == InteractiveEvent.PHYSICS) {
			int stepX = 5, stepY = 5;

			boolean yAdd = false;
			// first
			if (reversePos) {
				Break findFirstBreak = findFirstVisible();
				if(findFirstBreak==null) {
					return;
				}
				if (findFirstBreak.getX() <= 0) {
					reversePos = false;
					yAdd = true;
				}
			}

			// last
			if (!reversePos) {
				Break lastBreak = findLastVisible();
				if(lastBreak==null) {
					return;
				}
				
				if (lastBreak.getX() + lastBreak.getWidth() >= board.getWidth()) {
					reversePos = true;
					yAdd = true;
				}
			}

			for (Break[] breakY : breaks.getBreaks()) {
				for (Break breakXY : breakY) {
					// x Limit überschritten.
					if (yAdd) {
						breakXY.setY(breakXY.getY() + stepY);
					} else {
						breakXY.setX(breakXY.getX() + (reversePos ? -1 : 1) * stepX);
					}
				}
			}
		}
	}

	private Break findFirstVisible() {
		Break[][] breakItems = breaks.getBreaks();

		for (int cntX = 0; cntX < breakItems[0].length; cntX++) {
			for (int cntY = 0; cntY < breakItems.length; cntY++) {
				if (!breakItems[cntY][cntX].isDestroyed()) {
					return breakItems[cntY][cntX];
				}
			}
		}
		return null;
	}

	private Break findLastVisible() {
		Break[][] breakItems = breaks.getBreaks();

		for (int cntX = breakItems[0].length-1; cntX >=0 ; cntX--) {
			for (int cntY = breakItems.length-1; cntY >=0 ; cntY--) {
				if (!breakItems[cntY][cntX].isDestroyed()) {
					return breakItems[cntY][cntX];
				}
			}
		}
		return null;
	}

	private Boolean checkCollision(int x, int y) {
		for (Break[] breakY : breaks.getBreaks()) {
			for (Break breakXY : breakY) {
				if (breakXY.isDestroyed())
					continue;

				int x1 = breakXY.getX();
				int x2 = x1 + breakXY.getWidth();
				int y1 = breakXY.getY();
				int y2 = y1 + breakXY.getHeight();
				if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
					breakXY.destroyed();
					return true;
				}
			}
		}
		return null;
	}
}
