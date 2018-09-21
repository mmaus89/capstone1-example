package de.openhpi.capstone1.game.view;

import de.openhpi.capstone1.game.model.Breaks;
import de.openhpi.capstone1.game.model.Breaks.Break;
import processing.core.PApplet;

public class BreaksView extends AbstractView{

	private Breaks breaks;
	
	public BreaksView(PApplet pApplet, Breaks breaks) {
		super(pApplet);
		
		this.breaks = breaks;
	}

	@Override
	public void update() {
		
		for(Break[] breakY:breaks.getBreaks()) {
			for(Break breakXY:breakY) {
				if(!breakXY.isDestroyed()) {
					display.rect(breakXY.getX(), breakXY.getY(), breakXY.getWidth(), breakXY.getHeight());
				}
			}
		}
	}
}
