package de.openhpi.capstone1.game.component;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.BallController;
import de.openhpi.capstone1.game.controller.BoardController;
import de.openhpi.capstone1.game.controller.BreaksController;
import de.openhpi.capstone1.game.controller.PaddleController;
import de.openhpi.capstone1.game.controller.PaddleController.PaddleCollision;
import de.openhpi.capstone1.game.model.Ball;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Breaks;
import de.openhpi.capstone1.game.model.Paddle;
import de.openhpi.capstone1.game.physics.BallPhysics;
import de.openhpi.capstone1.game.physics.BreaksPhysics;
import de.openhpi.capstone1.game.view.BallView;
import de.openhpi.capstone1.game.view.BreaksView;
import de.openhpi.capstone1.game.view.PaddleView;
import processing.core.PApplet;

public class InteractiveBreaks extends InteractiveComponent {
	private static final int STEP_SIZE = 5;
	
	private Paddle paddleBottom;
	private Ball ball;
	private Breaks breaks;
	private Board board;
	private BreaksController breaksController;

	public InteractiveBreaks() {
	}

	public void addModel(PApplet applet) {
		paddleBottom = new Paddle(20, 10);
		ball = new Ball(5, 5);
		board = new Board(applet);
		breaks = new Breaks(board, 5, 100);

		ball.setX(board.getWidth() / 2);
		ball.setY(board.getHeight());

		paddleBottom.setY(board.getHeight() - paddleBottom.getHeight());
	}

	public void createViews(PApplet applet) {
		views.add(new PaddleView(applet, paddleBottom));
		views.add(new BreaksView(applet, breaks));
		views.add(new BallView(applet, ball));
	}

	public void addPhysics() {
		new BallPhysics(this::handleEvent).start();
		new BreaksPhysics(breaksController::handleEvent,200).start();
	}

	public void addController() {
		controllers.add(new BoardController(board, collisionHandler));

		PaddleController paddleControllerBottom = new PaddleController.PaddleControllerBuilder()
				.appendPaddle(paddleBottom).appendBoard(board).appendCollisionHandler(collisionHandler)
				.appendPaddleCollisionType(PaddleCollision.COLLISION_ON_BOTTOM)
				.appendInteractiveEvent(InteractiveEvent.MOUSE_HOVER).build();

		controllers.add(paddleControllerBottom);
		breaksController = new BreaksController(breaks, board, collisionHandler);
		
		BallController ballController = new BallController(ball, STEP_SIZE, board, collisionHandler);
		ballController.inverseXY(true, true);
		
		controllers.add(ballController);
	}
}