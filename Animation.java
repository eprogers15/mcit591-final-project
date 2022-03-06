
/**
 * @title Animation
 * 
 * @description Handles the window properties and animation for overworld screens and menu screens.
 * 
 * @author Emmett Rogers
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Animation {

	private int canvasWidth = 514;
	private int canvasHeight = 449;
	private double canvasScaleMin = 0.0;
	private double canvasScaleMax = 96.0;
	private int animationSpeed = 15;

	/**
	 * Initialize window properties.
	 */
	public void initializeWindow() {

		PennDraw.setCanvasSize(canvasWidth, canvasHeight);
		PennDraw.setScale(canvasScaleMin, canvasScaleMax);
		PennDraw.enableAnimation(animationSpeed);
	}

	/**
	 * Draw the scene including the map, hero, and enemies.
	 * 
	 * @param map        The game Map.
	 * @param hero       The Hero character.
	 * @param xIncrement The increment that the character moves on the X axis during
	 *                   move animations.
	 * @param yIncrement The increment that the character moves on the Y axis during
	 *                   move animations.
	 */
	public void drawScene(Map map, Hero hero, double xIncrement, double yIncrement) {

		if (hero.getCurrentY() != hero.getNewY() || hero.getCurrentX() != hero.getNewX()) {

			int index = 0;
			while (hero.getCurrentY() != hero.getNewY() || hero.getCurrentX() != hero.getNewX()) {

				PennDraw.clear();
				map.drawMap();
				map.getExitArrow().drawObject();
				hero.drawHeroHealth();
				hero.drawMoving(index);
				for (Enemy enemy : map.getEnemies()) {

					enemy.drawIdle(index);
				}
				drawPauseReminder();
				PennDraw.advance();
				index = (index + 1) % hero.getMoveLeftImages().length;
				hero.setCurrentX(hero.getCurrentX() + xIncrement);
				hero.setCurrentY(hero.getCurrentY() + yIncrement);
			}
		} else {

			int index = 0;
			while (index < hero.getIdleLeftImages().length) {

				PennDraw.clear();
				map.drawMap();
				map.getExitArrow().drawObject();
				hero.drawHeroHealth();
				hero.drawIdle(index);
				for (Enemy enemy : map.getEnemies()) {

					enemy.drawIdle(index);
				}
				drawPauseReminder();
				PennDraw.advance();
				index++;
			}
		}
	}

	/**
	 * Draws the Hero's health to the top of the screen.
	 */
	public void drawPauseReminder() {

		PennDraw.setPenColor(PennDraw.BLACK);
		PennDraw.filledRectangle(48.0, 2.0, 48.0, 4.0);
		PennDraw.setPenColor(PennDraw.WHITE);
		PennDraw.setFont("Trebuchet MS", 16);
		PennDraw.setFontBold();
		PennDraw.text(48.0, 3.0, "Press SPACEBAR to Pause");
	}

	/**
	 * Draw Start Menu on the screen.
	 */
	public void startMenu() {

		Character keyPressed = Character.MIN_VALUE;

		while (keyPressed == Character.MIN_VALUE) {

			PennDraw.clear(PennDraw.BLACK);
			PennDraw.setPenColor(PennDraw.WHITE);
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.text(16.0, 60.0, "Welcome to...");
			PennDraw.setPenColor(PennDraw.YELLOW);
			PennDraw.setFont("Trebuchet MS", 56);
			PennDraw.setFontBoldItalic();
			PennDraw.text((canvasScaleMax / 2), 52.0, "KNIGHT BATTLER");
			PennDraw.setPenColor(PennDraw.WHITE);
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.setFontPlain();
			PennDraw.text((canvasScaleMax / 2), 36.0, "Press Q to QUIT");
			PennDraw.text((canvasScaleMax / 2), 28.0, "Press any other key to start...");
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = Character.toLowerCase(PennDraw.nextKeyTyped());
			}

			if (keyPressed == 'q') {

				System.exit(0);
			}
		}
	}

	/**
	 * Draw instructions screen on the page.
	 * 
	 * @param hero The Hero character.
	 */
	public void instructionsScreen(Hero hero) {

		Character keyPressed = Character.MIN_VALUE;

		while (keyPressed == Character.MIN_VALUE) {

			PennDraw.clear(PennDraw.BLACK);
			PennDraw.setPenColor(PennDraw.WHITE);
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.text((canvasScaleMax / 2), 80.0,
					"You are " + hero.getName() + " and enemies have invaded your world!");
			PennDraw.text((canvasScaleMax / 2), 70.0, "Battle your way through each level ");
			PennDraw.text((canvasScaleMax / 2), 65.0, " and reach the treasure chest at the end to win.");
			PennDraw.text((canvasScaleMax / 2), 50.0, " * * * INSTRUCTIONS * * * ");
			PennDraw.text((canvasScaleMax / 2), 45.0, "Move Up - W");
			PennDraw.text((canvasScaleMax / 2), 40.0, "Move Down - S");
			PennDraw.text((canvasScaleMax / 2), 35.0, "Move Left - A");
			PennDraw.text((canvasScaleMax / 2), 30.0, "Move Right - D");
			PennDraw.text((canvasScaleMax / 2), 25.0, "Pause - SPACEBAR");
			PennDraw.text((canvasScaleMax / 2), 10.0, "Press ANY key to begin..");
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = Character.toLowerCase(PennDraw.nextKeyTyped());
			}
		}
	}

	/**
	 * Draw Pause Menu on the screen.
	 */
	public boolean pauseMenu() {

		Character keyPressed = Character.MIN_VALUE;
		boolean quitStatus = false;

		while (keyPressed == Character.MIN_VALUE) {

			PennDraw.setPenColor(PennDraw.BLACK);
			PennDraw.filledSquare((canvasScaleMax / 2), (canvasScaleMax / 2), 30);
			PennDraw.setPenColor(PennDraw.WHITE);
			PennDraw.setFont("Trebuchet MS", 60);
			PennDraw.setFontBoldItalic();
			PennDraw.text((canvasScaleMax / 2), 54.0, "PAUSED");
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.setFontPlain();
			PennDraw.text((canvasScaleMax / 2), 36.0, "Press Q to QUIT");
			PennDraw.text((canvasScaleMax / 2), 28.0, "Press any other key to continue...");
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = Character.toLowerCase(PennDraw.nextKeyTyped());
			}

			if (keyPressed == 'q') {

				quitStatus = quit();
			}
		}
		return quitStatus;
	}

	/**
	 * Draw Quit Menu on the screen.\
	 */
	public boolean quit() {

		Character keyPressed = Character.MIN_VALUE;
		boolean quitStatus = false;

		while (keyPressed == Character.MIN_VALUE) {

			PennDraw.setPenColor(PennDraw.BLACK);
			PennDraw.filledSquare((canvasScaleMax / 2), (canvasScaleMax / 2), 30);
			PennDraw.setPenColor(PennDraw.WHITE);
			PennDraw.setFont("Trebuchet MS", 18.0);
			PennDraw.setFontBoldItalic();
			PennDraw.text((canvasScaleMax / 2), 54.0, "Are you sure you want to quit?");
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.setFontPlain();
			PennDraw.text((canvasScaleMax / 2), 36.0, "Yes (Y) or No (N)?");
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = PennDraw.nextKeyTyped();
			}

			if (keyPressed == 'y') {

				quitStatus = true;
			}
		}
		return quitStatus;
	}

	/**
	 * Draw Game Over screen on the page.
	 * 
	 * @param hero The Hero character.
	 */
	public void gameOver(Hero hero) {

		String keyPressed = null;

		while (keyPressed == null) {

			PennDraw.clear(PennDraw.BLACK);
			PennDraw.setPenColor(PennDraw.RED);
			PennDraw.setFont("Trebuchet MS", 50.0);
			PennDraw.setFontBold();
			PennDraw.text((canvasScaleMax / 2), 64.0, "You lost!");
			PennDraw.picture(40.0, (canvasScaleMax / 2), hero.getIdleRightImages()[0], 128.0, 128.0, 90);
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.setFontPlain();
			PennDraw.text((canvasScaleMax / 2), 32.0, "Press ANY key to play again...");
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = Character.toString(PennDraw.nextKeyTyped());
			}
		}
	}

	/**
	 * Draw Win screen on the page.
	 */
	public void win() {

		String keyPressed = null;

		while (keyPressed == null) {

			PennDraw.clear(PennDraw.BLACK);
			PennDraw.setPenColor(PennDraw.YELLOW);
			PennDraw.setFont("Trebuchet MS", 50);
			PennDraw.setFontBold();
			PennDraw.text((canvasScaleMax / 2), 64.0, "Congratulations!");
			PennDraw.text((canvasScaleMax / 2), 48.0, "You WON!");
			PennDraw.setFont("Trebuchet MS", 16);
			PennDraw.setFontPlain();
			PennDraw.text((canvasScaleMax / 2), 32.0, "Press ANY key to play again...");
			PennDraw.advance();
			PennDraw.advance();

			if (PennDraw.hasNextKeyTyped()) {

				keyPressed = Character.toString(PennDraw.nextKeyTyped());
			}
		}
	}

	/**
	 * Get the width of the canvas in number of pixels.
	 * @return the width of the canvas in pixels
	 */
	public int getCanvasWidth() {

		return canvasWidth;
	}

	/**
	 * Get the height of the canvas in number of pixels.
	 * @return the height of the canvas in pixels
	 */
	public int getCanvasHeight() {

		return canvasHeight;
	}

	/**
	 * Get the max scale of the canvas.
	 * @return max scale of the canvas
	 */
	public double getCanvasScaleMax() {

		return canvasScaleMax;
	}
}