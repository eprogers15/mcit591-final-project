import java.util.ArrayList;

/**
 * @title Map
 * 
 * @description Handles the map image, boundaries, exit and creation of enemies.
 * 
 * @author Emmett Rogers
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Map {

	private double mapCenterX;
	private double mapCenterY;
	private int level;
	private String currentMapFile;
	private String level1MapFile = "images/map/homeMap.png";
	private String level2MapFile = "images/map/townMap.png";
	private String level3MapFile = "images/map/dungeonMap.png";
	private String level4MapFile = "images/map/dungeonRoomMap.jpg";
	private ArrayList<MapObject> walls = new ArrayList<>();
	private MapObject exit;
	private MapObject exitArrow;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * Configures the map and characters for Level 1.
	 * 
	 * @param hero          The Hero character.
	 */
	public void setLevel1Map(Hero hero) {

		this.mapCenterX = 32.0;
		this.mapCenterY = 52.0;
		this.currentMapFile = this.level1MapFile;

		hero.setCurrentX(75.0);
		hero.setCurrentY(52.0);
		hero.setNewX(hero.getCurrentX());
		hero.setNewY(hero.getCurrentY());
		hero.setXMovementDistance(9.0);
		hero.setYMovementDistance(9.0);
		hero.setDirectionFacing("left");

		ArrayList<Attack> attackOptions = new ArrayList<>();
		attackOptions.add(new Attack("Bite", 2, 1, 0.3333));

		Enemy wolf = new Enemy("Wolfy", 5, 5, 100, 100, attackOptions, 5, 12.0, 43.0,
				"images/wolf/wolfIdleRightImages/wolf_Idle_Right_", "images/wolf/wolfIdleLeftImages/wolf_Idle_Left_",
				"images/wolf/wolfMoveRightImages/wolf_Move_Right_", "images/wolf/wolfMoveLeftImages/wolf_Move_Left_",
				"right");
		enemies.add(wolf);

		// Left edge of the map
		for (double y = 52.0; y <= 88.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(-6.0, y);
			walls.add(wall);
		}
		// Left side of the path in the bottom left
		for (double y = 7.0; y <= 43.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(3.0, y);
			walls.add(wall);
		}
		// Right side of the path in the bottom left
		for (double y = 16.0; y <= 43.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(21.0, y);
			walls.add(wall);
		}
		// The fence in the top left
		for (double y = 61.0; y <= 88.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(30.0, y);
			walls.add(wall);
		}
		// Right edge of the map
		for (double y = 7.0; y <= 52.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(102.0, y);
			walls.add(wall);
		}
		// Top left edge of the map
		for (double x = 3.0; x <= 21.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 97.0);
			walls.add(wall);
		}
		// The fence in the middle of the map
		for (double x = 39.0; x <= 48.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 52.0);
			walls.add(wall);
		}
		// The barn
		for (double x = 57.0; x <= 102.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 61.0);
			walls.add(wall);
		}
		// The fence at the bottom of the map
		for (double x = 21.0; x <= 93.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 7.0);
			walls.add(wall);
		}

		exit = new MapObject(12.0, -2.0);
		exitArrow = new MapObject((exit.getMapObjectX()), (exit.getMapObjectY() + (hero.getYMovementDistance() * 2)), 270.0, "images/map/exitArrow.png");
	}

	/**
	 * Configures the map and characters for Level 2.
	 * 
	 * @param hero          The Hero character.
	 */
	public void setLevel2Map(Hero hero) {

		this.mapCenterX = 8.0;
		this.mapCenterY = 20.0;
		this.currentMapFile = this.level2MapFile;

		hero.setCurrentX(92.0);
		hero.setCurrentY(26.0);
		hero.setNewX(hero.getCurrentX());
		hero.setNewY(hero.getCurrentY());
		hero.setXMovementDistance(12.0);
		hero.setYMovementDistance(14.0);
		hero.setDirectionFacing("left");

		ArrayList<Attack> attackOptions = new ArrayList<>();
		attackOptions.add(new Attack("Claw", 2, 1, 0.75));
		attackOptions.add(new Attack("Spit", 3, 4, 0.5));

		Enemy goblin1 = new Enemy("Green Goblin", 10, 10, 100, 100, attackOptions, 5, 56.0, 26.0,
				"images/goblin/goblinIdleRightImages/goblin_Idle_Right_",
				"images/goblin/goblinIdleLeftImages/goblin_Idle_Left_",
				"images/goblin/goblinMoveRightImages/goblin_Move_Right_",
				"images/goblin/goblinMoveLeftImages/goblin_Move_Left_", "right");
		Enemy goblin2 = new Enemy("Gobbler", 15, 15, 100, 100, attackOptions, 5, 32.0, 12.0,
				"images/goblin/goblinIdleRightImages/goblin_Idle_Right_",
				"images/goblin/goblinIdleLeftImages/goblin_Idle_Left_",
				"images/goblin/goblinMoveRightImages/goblin_Move_Right_",
				"images/goblin/goblinMoveLeftImages/goblin_Move_Left_", "right");
		enemies.add(goblin1);
		enemies.add(goblin2);

		// Left side of the Inn
		for (double x = 8.0; x <= 20.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 40);
			walls.add(wall);
		}
		// Top side of the bridge and the right side of the Inn
		for (double x = 44.0; x <= 92.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 40);
			walls.add(wall);
		}
		// Bottom of the map
		for (double x = 8.0; x <= 92.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, -2.0);
			walls.add(wall);
		}
		// Left side of the map
		for (double y = 12.0; y <= 26.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(-4.0, y);
			walls.add(wall);
		}
		MapObject water = new MapObject(92.0, 12.0);
		walls.add(water);
		MapObject entrance = new MapObject(104.0, 26.0);
		walls.add(entrance);

		exit = new MapObject(32.0, 40.0);
		exitArrow = new MapObject(exit.getMapObjectX(), (exit.getMapObjectY() - hero.getYMovementDistance()), 90.0, "images/map/exitArrow.png");
	}

	/**
	 * Configures the map and characters for Level 3.
	 * 
	 * @param hero          The Hero character.
	 */
	public void setLevel3Map(Hero hero) {

		this.mapCenterX = 48.0;
		this.mapCenterY = 48.0;
		this.currentMapFile = this.level3MapFile;

		hero.setCurrentX(6.0);
		hero.setCurrentY(52.0);
		hero.setNewX(hero.getCurrentX());
		hero.setNewY(hero.getCurrentY());
		hero.setXMovementDistance(12.0);
		hero.setYMovementDistance(13.0);
		hero.setDirectionFacing("right");

		ArrayList<Attack> attackOptions = new ArrayList<Attack>();
		attackOptions.add(new Attack("Punch", 2, 1, 0.75));
		attackOptions.add(new Attack("Kick", 5, 4, 0.3333));
		attackOptions.add(new Attack("Club", 10, 10, 0.25));
		Enemy troll1 = new Enemy("Larry", 25, 25, 100, 100, attackOptions, 5, 78.0, 39.0,
				"images/troll/trollIdleRightImages/troll_Idle_Right_",
				"images/troll/trollIdleLeftImages/troll_Idle_Left_",
				"images/troll/trollMoveRightImages/troll_Move_Right_",
				"images/troll/trollMoveLeftImages/troll_Move_Left_", "left");
		Enemy troll2 = new Enemy("Moe", 25, 25, 100, 100, attackOptions, 5, 18.0, 26.0,
				"images/troll/trollIdleRightImages/troll_Idle_Right_",
				"images/troll/trollIdleLeftImages/troll_Idle_Left_",
				"images/troll/trollMoveRightImages/troll_Move_Right_",
				"images/troll/trollMoveLeftImages/troll_Move_Left_", "right");
		Enemy troll3 = new Enemy("Curly", 25, 25, 100, 100, attackOptions, 5, 78.0, 78.0,
				"images/troll/trollIdleRightImages/troll_Idle_Right_",
				"images/troll/trollIdleLeftImages/troll_Idle_Left_",
				"images/troll/trollMoveRightImages/troll_Move_Right_",
				"images/troll/trollMoveLeftImages/troll_Move_Left_", "left");
		enemies.add(troll1);
		enemies.add(troll2);
		enemies.add(troll3);

		// Left side of the map
		for (double y = 52.0; y <= 96.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(-6.0, y);
			walls.add(wall);
		}
		// Top of the map
		for (double x = 6.0; x <= 96.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 91.0);
			walls.add(wall);
		}
		// Bottom of the map
		for (double x = 6.0; x <= 96.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 13.0);
			walls.add(wall);
		}
		// Bottom left corner wall
		for (double y = 0.0; y <= 39.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(6.0, y);
			walls.add(wall);
		}
		// Right side of the map
		for (double y = 39.0; y <= 96.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(90.0, y);
			walls.add(wall);
		}
		// Top wall that sticks out
		for (double x = 18.0; x <= 42.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 78.0);
			walls.add(wall);
		}
		// Right side wall that sticks out
		for (double x = 66.0; x <= 78.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 52);
			walls.add(wall);
		}
		MapObject rightBlock = new MapObject(66.0, 39.0);
		walls.add(rightBlock);
		MapObject middleBlock = new MapObject(42.0, 52.0);
		walls.add(middleBlock);
		MapObject bottomBlock = new MapObject(30.0, 26.0);
		walls.add(bottomBlock);

		exit = new MapObject(102.0, 26.0);
		exitArrow = new MapObject((exit.getMapObjectX() - hero.getXMovementDistance()), (exit.getMapObjectY() - 6.0), 0.0, "images/map/exitArrow.png");
	}

	/**
	 * Configures the map and characters for Level 4.
	 * 
	 * @param hero          The Hero character.
	 */
	public void setLevel4Map(Hero hero) {

		this.mapCenterX = 42.0;
		this.mapCenterY = 46.0;
		this.currentMapFile = this.level4MapFile;

		hero.setCurrentX(8.0);
		hero.setCurrentY(39.0);
		hero.setNewX(hero.getCurrentX());
		hero.setNewY(hero.getCurrentY());
		hero.setXMovementDistance(13.0);
		hero.setYMovementDistance(11.0);
		hero.setDirectionFacing("right");

		ArrayList<Attack> attackOptions = new ArrayList<Attack>();
		attackOptions.add(new Attack("Pebble", 5, 4, 0.3333));
		attackOptions.add(new Attack("Smash", 25, 25, 0.25));
		attackOptions.add(new Attack("Earthquake", 50, 50, 0.01));

		Enemy golem = new Enemy("Golem", 50, 50, 100, 100, attackOptions, 5, 73.0, 61.0,
				"images/golem/golemIdleRightImages/golem_Idle_Right_",
				"images/golem/golemIdleLeftImages/golem_Idle_Left_",
				"images/golem/golemMoveRightImages/golem_Move_Right_",
				"images/golem/golemMoveLeftImages/golem_Move_Left_", "left");
		enemies.add(golem);

		// Left edge of the map above the entrance
		for (double y = 50.0; y <= 72.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(8.0, y);
			walls.add(wall);
		}
		// Bottom right wall
		for (double y = 28.0; y <= 39.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(73.0, y);
			walls.add(wall);
		}
		// Right edge of the map
		for (double y = 50.0; y <= 72.0; y += hero.getYMovementDistance()) {

			MapObject wall = new MapObject(86.0, y);
			walls.add(wall);
		}
		// Bottom edge of the map
		for (double x = 21.0; x <= 60.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 17);
			walls.add(wall);
		}
		// Table in the middle of the room.
		for (double x = 34.0; x <= 47.0; x += hero.getXMovementDistance()) {

			for (double y = 50.0; y <= 61.0; y += hero.getYMovementDistance()) {

				MapObject wall = new MapObject(x, y);
				walls.add(wall);
			}
		}
		// Top edge of the map
		for (double x = 21.0; x <= 73.0; x += hero.getXMovementDistance()) {

			MapObject wall = new MapObject(x, 83);
			walls.add(wall);
		}
		MapObject entrance = new MapObject(-5.0, 39.0);
		walls.add(entrance);
		MapObject bottomLeft = new MapObject(8.0, 28.0);
		walls.add(bottomLeft);

		exit = new MapObject(73.0, 72.0);
		exitArrow = new MapObject((exit.getMapObjectX() - hero.getXMovementDistance()), (exit.getMapObjectY() - 6.0), 0.0, "images/map/exitArrow.png");
	}

	/**
	 * Remove the exit Wall object from the list of walls so the hero can pass
	 * through it.
	 */
	public void unlockExit() {

		walls.remove(exit);
	}

	/**
	 * Get the current level of this map.
	 * @return the level of this map
	 */
	public int getLevel() {

		return level;
	}

	/**
	 * Set the level number and run the corresponding setLevelNMap method.
	 * 
	 * @param levelNumber   The current level number.
	 * @param hero          The Hero character.
	 * @param attackOptions The character attack options.
	 */
	public void setLevel(int levelNumber, Hero hero) {

		this.level = levelNumber;

		walls.clear();
		enemies.clear();

		if (this.level == 1) {

			setLevel1Map(hero);
		} else if (this.level == 2) {

			setLevel2Map(hero);
		} else if (this.level == 3) {

			setLevel3Map(hero);
		} else if (this.level == 4) {

			setLevel4Map(hero);
		}
	}

	/**
	 * Given the Hero's X and Y coordinates, determine whether or not it has reached
	 * the level's exit.
	 * 
	 * @param hero The Hero character.
	 * @return True or False depending on whether or not the hero character has
	 *         reached the exit.
	 */
	public boolean heroHasExitedMap(Hero hero) {

		boolean hasExitedMap = false;
		if (hero.getCurrentY() == exit.getMapObjectY() && hero.getCurrentX() == exit.getMapObjectX()) {

			hasExitedMap = true;
		}
		return hasExitedMap;
	}

	/**
	 * Draw the current map file to the screen.
	 */
	public void drawMap() {

		PennDraw.picture(mapCenterX, mapCenterY, currentMapFile);
	}

	/**
	 * Get the current image filepath of this map.
	 * @return image filepath of map
	 */
	public String getCurrentMapFile() {

		return currentMapFile;
	}

	/**
	 * Set the image filepath of this map
	 * @param mapFile new filepath to display
	 */
	public void setCurrentMapFile(String mapFile) {

		currentMapFile = mapFile;
	}

	/**
	 * Get the horizontal center of this map in pixels
	 * @return horizontal center of map in pixels
	 */
	public double getMapCenterX() {

		return mapCenterX;
	}

	/**
	 * Get the vertical center of this map in pixels
	 * @return vertical center of map in pixels
	 */
	public double getMapCenterY() {

		return mapCenterY;
	}

	/**
	 * Get the walls of this map.
	 * @return the walls of this map
	 */
	public ArrayList<MapObject> getWalls() {

		return walls;
	}

	/**
	 * Get the exit of this map
	 * @return the exit of this map
	 */
	public MapObject getExit() {

		return exit;
	}
	
	/**
	 * Get the arrow indicating where the exit is of this map.
	 * @return the arrow indicating where the exit is
	 */
	public MapObject getExitArrow() {
		
		return exitArrow;
	}

	/**
	 * Get the enemies of this map.
	 * @return the list of enemies of this map
	 */
	public ArrayList<Enemy> getEnemies() {

		return enemies;
	}
}