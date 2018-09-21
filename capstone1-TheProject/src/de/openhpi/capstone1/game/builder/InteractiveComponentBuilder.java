package de.openhpi.capstone1.game.builder;

import de.openhpi.capstone1.game.component.InteractiveComponent;
import processing.core.PApplet;

public class InteractiveComponentBuilder {
	public static InteractiveComponent create(PApplet applet, InteractiveComponentType type) {

		Builder builder = new InteractivePongBuilder();
		switch (type) {
		case BREAK_OUT:
			builder = new InteractiveBreaksBuilder();
			break;
		case PONG:
			break;
		case SPACE_INVADERS:
			builder = new InteractiveSpaceInvadersBuilder();
			break;
		case SQUASH:
			builder = new InteractiveSquashBuilder();
			break;
		default:
			break;
		}

		construct(applet, builder);

		return builder.getComponent();
	}

	private static void construct(PApplet applet, Builder builder) {
		builder.buildComponent();
		builder.buildModel(applet);
		builder.buildView(applet);
		builder.buildController();
		builder.buildPhysics();
	}
}
