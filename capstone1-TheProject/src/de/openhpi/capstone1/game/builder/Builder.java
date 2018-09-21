package de.openhpi.capstone1.game.builder;

import de.openhpi.capstone1.game.component.InteractiveComponent;
import processing.core.PApplet;

public interface Builder {
	public void buildComponent();
	public void buildModel(PApplet applet); 
	public void buildView(PApplet applet);
	public void buildController();
	public void buildPhysics();
	
	public InteractiveComponent getComponent();
}
