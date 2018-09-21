package de.openhpi.capstone1.game.component;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.BallController;
import de.openhpi.capstone1.game.controller.BoardController;
import de.openhpi.capstone1.game.controller.PaddleController;
import de.openhpi.capstone1.game.controller.PaddleController.PaddleCollision;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Paddle;
import de.openhpi.capstone1.game.physics.BallPhysics;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.view.BallView;
import de.openhpi.capstone1.game.view.PaddleView;
import processing.core.PApplet;

public class InteractivePong extends InteractiveComponent {
	private static final int STEP_SIZE = 5;
	
	private Paddle paddleBottom, paddleTop;
	private Ball ball;
	private Board board;

	private boolean hasPaddleTop;

	public InteractivePong(boolean hasPaddleTop) {
		this.hasPaddleTop = hasPaddleTop;
	}

	public void addModel(PApplet applet) {
		board = new Board(applet);
		paddleBottom = new Paddle(20, 10);
		if (hasPaddleTop) {
			paddleTop = new Paddle(20, 10);
		}
		ball = new Ball(5, 5);

		paddleBottom.setY(board.getHeight() - paddleBottom.getHeight());
	}

	public void createViews(PApplet applet) {
		views.add(new PaddleView(applet, paddleBottom));

		if (hasPaddleTop) {
			views.add(new PaddleView(applet, paddleTop));
		}
		views.add(new BallView(applet, ball));

		paddleBottom.setY(board.getHeight() - paddleBottom.getHeight());
	}

	public void addPhysics() {
		new BallPhysics(this::handleEvent).start();
	}

	public void addController() {
		controllers.add(new BoardController(board, collisionHandler));
		if (hasPaddleTop) {
			PaddleController paddleControllerTop = new PaddleController.PaddleControllerBuilder()
					.appendPaddle(paddleTop).appendBoard(board).appendCollisionHandler(collisionHandler)
					.appendPaddleCollisionType(PaddleCollision.COLLISION_ON_TOP)
					.appendInteractiveEvent(InteractiveEvent.KEY_PRESS).build();
			controllers.add(paddleControllerTop);
		}

		PaddleController paddleControllerBottom = new PaddleController.PaddleControllerBuilder()
				.appendPaddle(paddleBottom).appendBoard(board).appendCollisionHandler(collisionHandler)
				.appendPaddleCollisionType(PaddleCollision.COLLISION_ON_BOTTOM)
				.appendInteractiveEvent(InteractiveEvent.MOUSE_HOVER).build();

		controllers.add(paddleControllerBottom);
		controllers.add(new BallController(ball, STEP_SIZE, board, collisionHandler));
	}
}
