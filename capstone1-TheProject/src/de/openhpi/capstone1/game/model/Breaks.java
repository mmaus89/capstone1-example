package de.openhpi.capstone1.game.model;

public class Breaks{

	private Break[][] breaks;
	private int breakWidth = 10;
	private int breakHeight = 4;
	private int offsetX = 2;
	private int offsetY = 2;

	public Breaks(Board board,int y, int offset) {
		int x = (board.getWidth()-offset)/breakWidth;
		breaks = new Break[y][x];

		int positionX = 0;
		int positionY = 0;

		for (int cntY = 0; cntY < y; cntY++) {
			positionX = 0;
			for (int cntX = 0; cntX < x; cntX++) {
				breaks[cntY][cntX] = new Break(positionX+offset/2, positionY, breakWidth, breakHeight);

				positionX += breakWidth + offsetX;
			}

			positionY += breakHeight + offsetY;
		}
	}

	public Break[][] getBreaks() {
		return breaks;
	}

	public static class Break {
		private int x;
		private int y;
		private int width;
		private int height;

		private boolean destroyed = false;

		public Break(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public void destroyed() {
			destroyed = true;
		}

		public boolean isDestroyed() {
			return destroyed;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setX(int x) {
			this.x = x;
		}
	}
}