package de.openhpi.capstone1.game.controller;

import de.openhpi.capstone1.game.builder.InteractiveEvent;
import de.openhpi.capstone1.game.model.Board;
import de.openhpi.capstone1.game.model.Paddle;
import de.openhpi.capstone1.game.model.SpaceShip;

public class FirePaddleController extends PaddleController {

	private static final int KEY_SHOT = 10;
	private ShotsController shotController;

	private FirePaddleController(Paddle paddle, Board board, CollisionHandler collisionHandler,
			PaddleCollision paddleCollision, InteractiveEvent interactiveEvent, ShotsController shotController) {
		super(paddle, board, collisionHandler, paddleCollision, interactiveEvent);
		this.shotController = shotController;
	}

	@Override
	public void handleEvent(InteractiveEvent event, Object... arguments) {

		super.handleEvent(event, arguments);

		if (event == InteractiveEvent.KEY_PRESS) {
			int keyShot = KEY_SHOT;
			if ((int) arguments[0] == keyShot) {
				shot();
			}
		}
	}

	private void shot() {
		shotController.handleEvent(InteractiveEvent.KEY_PRESS);
	}

	public static class FirePaddleBuilder {
		private SpaceShip spaceShip;
		private Board board;
		private CollisionHandler collisionHandler;
		private PaddleCollision paddleCollision = PaddleCollision.COLLISION_ON_BOTTOM;
		private InteractiveEvent interactiveEvent;
		private ShotsController shotController;

		public FirePaddleBuilder appendSpaceShip(SpaceShip spaceShip) {
			this.spaceShip = spaceShip;
			return this;
		}
		
		public FirePaddleBuilder appendBoard(Board board) {
			this.board = board;
			return this;
		}
		
		public FirePaddleBuilder appendCollisionHandler(CollisionHandler collisionHandler) {
			this.collisionHandler = collisionHandler;
			return this;
		}
		
		public FirePaddleBuilder appendInteractiveEvent(InteractiveEvent event) {
			this.interactiveEvent = event;
			return this;
		}
		
		public FirePaddleBuilder appendShotsController(ShotsController shotController) {
			this.shotController = shotController;
			return this;
		}
		
		public FirePaddleController build() {
			if (spaceShip != null) {
				throw new IllegalArgumentException("SpaceShip is not Initialized");
			}
			if (board != null) {
				throw new IllegalArgumentException("Board is not Initialized");
			}
			if (collisionHandler != null) {
				throw new IllegalArgumentException("CollisionHandler is not Initialized");
			}
			if (paddleCollision != null) {
				throw new IllegalArgumentException("PaddleCollision is not Initialized");
			}
			if (interactiveEvent != null) {
				throw new IllegalArgumentException("InteractiveEvent is not Initialized");
			}
			if (shotController != null) {
				throw new IllegalArgumentException("ShotController is not Initialized");
			}

			return new FirePaddleController(spaceShip, board, collisionHandler, paddleCollision, interactiveEvent,
					shotController);
		}

	}
}
