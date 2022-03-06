import java.util.ArrayList;

/**
 * @title Hero
 * 
 * @description Respresents the player controlled persons, including their xp storage, and drawing their health.
 * 
 * @author Emmett Rogers
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Hero extends Person {
	//amount of xp accrued from gameplay, cannot go down
	private int xpAccrued;
	//amount of xp currently accessible
	private int xpCurrent;

	/**
     * Constructor to create any hero.
     * @param name the name of this person
     * @param health the starting health level of this person
     * @param maxHealth the max health this person can have
     * @param energy the starting energy level of this person
     * @param maxEnergy the max energy this person can have
     * @param attackOptions the attacks available to this person
     * @param x the current x of this person
     * @param y the current y of this person
     * @param idleRightImagesLocation the images of this person when idling and facing right
     * @param idleLeftImagesLocation the images of this person when idling and facing left
     * @param moveRightImagesLocation the images of this person when moving and facing right
     * @param moveLeftImagesLocation the image of this person when moving and facing left
     * @param directionFacing the current direction this person is facing
     */
	public Hero(String name, int health, int maxHealth, int energy, int maxEnergy, ArrayList<Attack> attackOptions, double x, double y,
			String idleRightImagesLocation, String idleLeftImagesLocation, String moveRightImagesLocation,
			String moveLeftImagesLocation, String directionFacing) {

		super(name, health, maxHealth, energy, maxEnergy, attackOptions, x, y, idleRightImagesLocation, idleLeftImagesLocation, moveRightImagesLocation,
		moveLeftImagesLocation, directionFacing);

		this.xpAccrued = 0;
		this.xpCurrent = 0;
	
	}

	/**
	 * Constructor for generic hero with knight images.
	 * @param name the name of this hero
     * @param health the starting health level of this hero
     * @param startingMaxHealth the max health this hero can have
     * @param energy the starting energy level of this hero
     * @param startingMaxEnergy the max energy this hero can have
     * @param attackOptions the attacks available to this hero
     * @param x the current x of this hero
     * @param y the current y of this hero
     * @param directionFacing the current direction this hero is facing
	 */
	public Hero(String name, int health, int startingMaxHealth, int energy, int startingMaxEnergy, ArrayList<Attack> attackOptions, double x, double y, String directionFacing) {
		super(name, health, startingMaxHealth, energy, startingMaxEnergy, attackOptions, x, y, "images/knight/knightIdleRightImages/knight_Idle_Right_",
				"images/knight/knightIdleLeftImages/knight_Idle_Left_",
				"images/knight/knightMoveRightImages/knight_Move_Right_",
				"images/knight/knightMoveLeftImages/knight_Move_Left_", directionFacing);

		this.xpAccrued = 0;
		this.xpCurrent = 0;
	}

	/**
	 * Get the xp this hero has accrued over the course of the game.
	 * Historical record of all xp - never goes down.
	 * @return xp this hero has accrued
	 */
	public int getAccruedXp() {
		return this.xpAccrued;
	}

	/**
	 * Get the xp this hero currently has.
	 * Any xp spent will not be included.
	 * @return xp this hero currently has
	 */
	public int getCurrentXp() {
		return this.xpCurrent;
	}

	/**
	 * Add the given amount of xp to Hero's xp.
	 * Amount must be positive.
	 * @param amount amount of xp to add
	 */
	public void addXp(int amount) {
		if(amount < 0) { throw new IllegalArgumentException("amount must be positive: " + amount);}
		this.xpAccrued += amount;
		this.xpCurrent += amount;
	}

	/**
	 * Subtact the given amount of xp from Hero's xpCurrent.
	 * Amount must be positive.
	 * Does not effect xpAccrued.
	 * @param amount
	 */
	public void spendXp(int amount) {
		if(amount < 0) { throw new IllegalArgumentException("amount must be positive: " + amount);}
		if(amount > xpCurrent) { throw new IllegalArgumentException("amount must be less than current xp: " + amount + " > " +xpCurrent);}
		this.xpCurrent -= amount;
	}

	/**
	 * Draws the Hero's health to the top of the screen.
	 */
	public void drawHeroHealth() {

		PennDraw.setPenColor(PennDraw.BLACK);
		PennDraw.filledRectangle(48.0, 92.0, 48.0, 4.0);
		PennDraw.setPenColor(PennDraw.WHITE);
		PennDraw.setFont("Trebuchet MS", 16);
		PennDraw.setFontBold();
		PennDraw.text(16.0, 92.0, "Player 1: " + getName());
		PennDraw.text(86.0, 92.0, "Health: " + getHealth());
	}
}