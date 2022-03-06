import java.util.ArrayList;

/**
 * @title Enemy
 * 
 * @description Represents the non-player persons, including the xp they store.
 * 
 * @author Emmett Rogers
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Enemy extends Person {
	//amount of xp to drop when defeated
	private int xpStored;

	/**
	 * Constructor for generic enemy.
     * @param name the name of this enemy
     * @param health the starting health level of this enemy
     * @param maxHealth the max health this enemy can have
     * @param energy the starting energy level of this enemy
     * @param maxEnergy the max energy this enemy can have
     * @param attackOptions the attacks available to this enemy
	 * @param xp the amount of xp this enemy drops when defeated
     * @param x the current x of this enemy
     * @param y the current y of this enemy
     * @param idleRightImagesLocation the images of this enemy when idling and facing right
     * @param idleLeftImagesLocation the images of this enemy when idling and facing left
     * @param moveRightImagesLocation the images of this enemy when moving and facing right
     * @param moveLeftImagesLocation the image of this enemy when moving and facing left
     * @param directionFacing the current direction this enemy is facing
	 */
	public Enemy(String name, int health, int maxHealth, int energy, int maxEnergy, ArrayList<Attack> attackOptions, int xp, double x,
			double y, String idleRightImages, String idleLeftImages, String moveRightImages, String moveLeftImages,
			String directionFacing) {

		super(name, health, maxHealth, energy, maxEnergy, attackOptions, x, y, idleRightImages, idleLeftImages, moveRightImages,
				moveLeftImages, directionFacing);

		this.xpStored = xp;
	}

	/**
     * Get experience points (xp) that this enemy stores.
     * @return the number of xp this enemy stores
     */
	public int getStoredXp() {

		return this.xpStored;
	}
}