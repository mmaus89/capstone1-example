package de.openhpi.capstone1.game.builder;

import de.openhpi.capstone1.game.component.InteractiveComponent;
import de.openhpi.capstone1.game.component.InteractivePong;
import processing.core.PApplet;

public class InteractiveSquashBuilder implements Builder{

	private InteractivePong pong;
	@Override
	public void buildComponent() {
		pong = new InteractivePong(false);
	}

	@Override
	public void buildModel(PApplet applet) {
		pong.addModel(applet);
	}

	@Override
	public void buildView(PApplet applet) {
		pong.createViews(applet);
	}

	@Override
	public void buildController() {
		pong.addController();
	}

	@Override
	public InteractiveComponent getComponent() {
		return pong;
	}

	@Override
	public void buildPhysics() {
		pong.addPhysics();
	}

}
