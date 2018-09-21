package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;

public interface Controller {
	void handleEvent(InteractiveEvent event, Object... arguments);
}
