package de.openhpi.capstone1.game.physics;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.Controller;

public class BreaksPhysics implements Physics{
	
	private Controller controller;
	private Thread breaksMover;
	private boolean running;
	private int delay;
	
	public BreaksPhysics(Controller controller, int delay) {
		this.controller = controller;
		this.delay = delay;
	}
	
	@Override
	public void start() {
		running = true;
		breaksMover = new Thread(() -> {
			while (running) {
				controller.handleEvent(InteractiveEvent.PHYSICS);
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		breaksMover.start();
	}

	@Override
	public void stop() {
		running = false;
	}

}
