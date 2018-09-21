package de.openhpi.capstone1.game.physics;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.Controller;

public class BallPhysics implements Physics{
	private Thread ballMover;
	private Controller controller;
	
	private boolean running;
	
	public BallPhysics(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void start() {
		running = true;
		ballMover = new Thread(() -> {
			while (running) {
				controller.handleEvent(InteractiveEvent.PHYSICS);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		ballMover.start();
	}

	@Override
	public void stop() {
		running = false;
	}
}
