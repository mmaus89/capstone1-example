package de.openhpi.capstone1.game.view;

import de.openhpi.capstone1.game.model.Shots;
import de.openhpi.capstone1.game.model.Shots.Shot;
import processing.core.PApplet;

public class ShotsView extends AbstractView{

	private Shots shots;
	
	public ShotsView(PApplet display, Shots shots) {
		super(display);
		
		this.shots = shots;
	}

	@Override
	public void update() {
		for (Shot shot : shots.getShots()) {
			display.rect(shot.getX(), shot.getY(), shots.getWidth(), shots.getHeight());
		}
	}

}
