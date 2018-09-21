package de.openhpi.capstone1.game.builder;

import de.openhpi.capstone1.game.component.InteractiveComponent;
import de.openhpi.capstone1.game.component.InteractiveSpaceInvaders;
import processing.core.PApplet;

public class InteractiveSpaceInvadersBuilder implements Builder{

	private InteractiveSpaceInvaders spaceInvaders;
	@Override
	public void buildComponent() {
		spaceInvaders = new InteractiveSpaceInvaders();
	}

	@Override
	public void buildModel(PApplet applet) {
		spaceInvaders.addModel(applet);
	}

	@Override
	public void buildView(PApplet applet) {
		spaceInvaders.createViews(applet);
	}

	@Override
	public void buildController() {
		spaceInvaders.addController();
	}

	@Override
	public InteractiveComponent getComponent() {
		return spaceInvaders;
	}

	@Override
	public void buildPhysics() {
		spaceInvaders.addPhysics();
	}

}
