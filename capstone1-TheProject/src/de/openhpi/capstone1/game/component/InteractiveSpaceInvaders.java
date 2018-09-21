package de.openhpi.capstone1.game.component;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.BoardController;
import de.openhpi.capstone1.game.controller.BreaksController;
import de.openhpi.capstone1.game.controller.FirePaddleController;
import de.openhpi.capstone1.game.controller.ShotsController;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Breaks;
import de.openhpi.capstone1.game.model.SpaceShip;
import de.openhpi.capstone1.game.model.Shots;
import de.openhpi.capstone1.game.view.BreaksView;
import de.openhpi.capstone1.game.view.SpaceShipView;
import de.openhpi.capstone1.game.view.ShotsView;
import processing.core.PApplet;

public class InteractiveSpaceInvaders extends InteractiveComponent {
	private SpaceShip spaceShip;
	private Breaks breaks;
	private Board board;
	private Shots shots;

	private BreaksController breaksController;

	private Thread ballMover;
	private Thread breaksMover;

	public InteractiveSpaceInvaders() {
	}

	public void addModel(PApplet applet) {
		board = new Board(applet);
		spaceShip = new SpaceShip(20, 20);

		breaks = new Breaks(board, 5, 100);
		shots = new Shots(2, 10);
	}

	public void createViews(PApplet applet) {
		views.add(new BreaksView(applet, breaks));
		views.add(new SpaceShipView(applet, spaceShip, board));
		views.add(new ShotsView(applet, shots));
	}

	public void addPhysics() {
		ballMover = new Thread(() -> {
			while (true) {
				handleEvent(InteractiveEvent.PHYSICS);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		ballMover.start();

		breaksMover = new Thread(() -> {
			while (true) {
				breaksController.handleEvent(InteractiveEvent.PHYSICS);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		breaksMover.start();

	}

	public void addController() {
		controllers.add(new BoardController(board, collisionHandler));
		breaksController = new BreaksController(breaks, board, collisionHandler);
		ShotsController shotsController = new ShotsController(shots, board, spaceShip, collisionHandler);
		
		FirePaddleController firePaddleController = new FirePaddleController.FirePaddleBuilder()
		.appendSpaceShip(spaceShip)
		.appendBoard(board)
		.appendCollisionHandler(collisionHandler)
		.appendInteractiveEvent(InteractiveEvent.KEY_PRESS)
		.appendShotsController(shotsController)
		.build();
		
		controllers.add(firePaddleController);
	}
}
