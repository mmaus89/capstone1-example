package de.openhpi.capstone1.game.builder;

import de.openhpi.capstone1.game.component.InteractiveBreaks;
import de.openhpi.capstone1.game.component.InteractiveComponent;
import processing.core.PApplet;

public class InteractiveBreaksBuilder implements Builder{

	private InteractiveBreaks interactiveBreak;
	
	@Override
	public void buildComponent() {
		interactiveBreak = new InteractiveBreaks();
	}

	@Override
	public void buildModel(PApplet applet) {
		interactiveBreak.addModel(applet);
	}

	@Override
	public void buildView(PApplet applet) {
		interactiveBreak.createViews(applet);
	}

	@Override
	public void buildController() {
		interactiveBreak.addController();
	}

	@Override
	public InteractiveComponent getComponent() {
		return interactiveBreak;
	}

	@Override
	public void buildPhysics() {
		interactiveBreak.addPhysics();
	}

}