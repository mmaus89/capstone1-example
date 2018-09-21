package de.openhpi.capstone1.game.starter;

import de.openhpi.capstone1.game.builder.InteractiveComponentBuilder;
import de.openhpi.capstone1.game.builder.InteractiveComponentType;
import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.component.InteractiveComponent;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class TheApp extends PApplet {

	InteractiveComponent interactiveComponent;

	@Override
	public void settings() {
		size(200, 200);
	}

	@Override
	public void setup() {  // setup() runs once
		frameRate(30);
		interactiveComponent = InteractiveComponentBuilder.create(this, InteractiveComponentType.SPACE_INVADERS);
	}

	@Override
	public void draw() {  // draw() loops forever, until stopped
		clear();
		interactiveComponent.update();
	}
	
	@Override
	public void mouseClicked() {
		interactiveComponent.handleEvent(InteractiveEvent.MOUSE_CLICK);
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		interactiveComponent.handleEvent(InteractiveEvent.MOUSE_HOVER,event.getX(),event.getY() );
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		interactiveComponent.handleEvent(InteractiveEvent.KEY_PRESS, event.getKeyCode());
	}
}
