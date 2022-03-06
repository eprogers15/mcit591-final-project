import java.util.ArrayList;

/**
 * @title Gameplay
 * @description Handles gameplay input, and main game logic.
 * 
 * @author Emmett Rogers
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Gameplay {
	
	int levelCounter;
	boolean quitStatus;

	/**
	 * This is the method that handles the gameplay input and ties together the rest
	 * of the classes.
	 */
	public void playGame() {

		Animation animation = new Animation();
		Map map = new Map();
		DuelManager dm;

		animation.initializeWindow();

		// create hero attacks
		ArrayList<Attack> attackOptions = new ArrayList<Attack>();
		attackOptions.add(new Attack("Swipe", 2, 1, 0.75));
		attackOptions.add(new Attack("Punch", 5, 4, 0.3333));
		attackOptions.add(new Attack("Kick", 10, 10, 0.11111));
		attackOptions.add(new Attack("One-Punch", 100, 50, 0.001));
		
		while (true) {

			animation.startMenu();

			Hero knight = new Hero("Knight Man", 100, 100, 100, 150, attackOptions, 75.0, 52.0, "left");
			map.setLevel(1, knight);
			levelCounter = 1;
			quitStatus = false;
			
			animation.instructionsScreen(knight);

			while (levelCounter < 5 && knight.getHealth() > 0 && quitStatus == false) {

				while (map.heroHasExitedMap(knight) == false && knight.getHealth() > 0 && quitStatus == false) {

					if (PennDraw.hasNextKeyTyped()) {

						char moveDirection = Character.toLowerCase(PennDraw.nextKeyTyped());

						if (moveDirection == 'w') {

							if (knight.move(map, "up")) {
							
								animation.drawScene(map, knight, 0.0, knight.getMoveSpeed());
							}
						} else if (moveDirection == 's') {

							if (knight.move(map, "down")) {
								
								animation.drawScene(map, knight, 0.0, -(knight.getMoveSpeed()));
							}
						} else if (moveDirection == 'a') {

							knight.setDirectionFacing("left");
							if (knight.move(map, "left")) {
								
								animation.drawScene(map, knight, -(knight.getMoveSpeed()), 0.0);
							}
						} else if (moveDirection == 'd') {

							knight.setDirectionFacing("right");
							if (knight.move(map, "right")) {
								
								animation.drawScene(map, knight, knight.getMoveSpeed(), 0.0);
							}
						} else if (moveDirection == ' ') {

							quitStatus = animation.pauseMenu();
						}

						Enemy enemy = knight.intersectWithPerson(map);
						if (enemy != null) {
							
							PennDraw.closeFrame();
							dm = new DuelManager(knight, enemy);
							dm.run();
							map.getEnemies().remove(enemy);
							animation.initializeWindow();
						}
					} else {

						animation.drawScene(map, knight, 0.0, 0.0);
					}
				}
				levelCounter++;
				if (levelCounter <= 4) {
					
					map.setLevel(map.getLevel() + 1, knight);
				}
			}
			if (knight.getHealth() <= 0) {

				animation.gameOver(knight);
			} else if (levelCounter == 5) {

				animation.win();
			}
		}
	}
}