package de.openhpi.capstone1.game.component;

import java.util.ArrayList;
import java.util.List;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.controller.CollisionHandler;
import de.openhpi.capstone1.game.controller.Controller;
import de.openhpi.capstone1.game.view.AbstractView;

public abstract class InteractiveComponent {
	public void handleEvent(InteractiveEvent event, Object... arguments) {
		for (Controller controller : controllers) {
			controller.handleEvent(event, arguments);
		}
	}

	protected List<Controller> controllers = new ArrayList<>();
	protected CollisionHandler collisionHandler = new CollisionHandler();
	protected List<AbstractView> views = new ArrayList<>();

	public void update() {
		for (AbstractView view : views) {
			view.update();
		}
	}

}
