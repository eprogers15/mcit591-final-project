import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;


/**
 * @title Person
 * 
 * @description Represents any character entity in the game and handles their abilities to move and attack.
 * 
 * @author Emmett Rogers
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public abstract class Person {

	private final int id;
	private String name;
	private int health;
	private int maxHealth;
	private int energy;
	private int maxEnergy;
	private ArrayList<Attack> attackOptions;
	private double personHeight = 64.0;
	private double personWidth = 64.0;
	private String[] idleRightImages = new String[4];
	private String[] idleLeftImages = new String[4];
	private String[] moveRightImages = new String[4];
	private String[] moveLeftImages = new String[4];
	private double currentX;
	private double currentY;
	private double newX;
	private double newY;
	private double moveSpeed = 1.0;
	private double xMovementDistance;
	private double yMovementDistance;
	private String directionFacing;

	private static int lastAssignedId = 0;

    /**
     * Constructor.
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
	public Person(String name, int health, int maxHealth, int energy, int maxEnergy, ArrayList<Attack> attackOptions, double x, double y,
			String idleRightImagesLocation, String idleLeftImagesLocation, String moveRightImagesLocation,
			String moveLeftImagesLocation, String directionFacing) {

		if(health > maxHealth) { throw new IllegalArgumentException("Starting health above maxHealth: " + maxHealth);}
		if(health < 0) { throw new IllegalArgumentException("Starting health below 0.");}
		if(energy > maxEnergy) { throw new IllegalArgumentException("Starting energy above maxEnergy: " + maxEnergy);}
		if(energy < 0) { throw new IllegalArgumentException("Starting energy below 0.");}
						
		lastAssignedId++;
		this.id = lastAssignedId;
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.attackOptions = attackOptions;
        Collections.sort(attackOptions);
		this.currentX = x;
		this.currentY = y;
		this.newX = x;
		this.newY = y;
		this.directionFacing = directionFacing;

		for (int i = 0; i < idleRightImages.length; i++) {

			idleRightImages[i] = idleRightImagesLocation + (i + 1) + ".png";
		}

		for (int i = 0; i < idleLeftImages.length; i++) {

			idleLeftImages[i] = idleLeftImagesLocation + (i + 1) + ".png";
		}

		for (int i = 0; i < moveRightImages.length; i++) {

			moveRightImages[i] = moveRightImagesLocation + (i + 1) + ".png";
		}

		for (int i = 0; i < moveLeftImages.length; i++) {

			moveLeftImages[i] = moveLeftImagesLocation + (i + 1) + ".png";
		}
    }

    /**
     * Get the id of this person - a set of unique digits
     * @return the id of this person
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the name of this person - is not unique
     * @return the name of this person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the health of this person
     * @return the health of this person
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get the max health level of this person
     * @return the max health of this person
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Get the energy of this person
     * @return the energy of this person
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Get the max energy level of this person
     * @return the max energy of this person
     */
    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    /**
     * Get the attacks that this person can access.
     * This includes any attack they have learned regardless of energy levels.
     * @return the attacks of this person
     */
    public ArrayList<Attack> getAttackOptions() {
        return this.attackOptions;
	}
    
    /**
     * Get the height of this person in number of pixels.
     * @return the height of this person
     */
    public double getPersonHeight() {

		return personHeight;
	}

    /**
     * Get the width of tihs person in number of pixels.
     * @return the width of this person
     */
	public double getPersonWidth() {

		return personWidth;
	}

    /**
     * Get the movement speed of this person.
     * @return the speed of this person
     */
	public double getMoveSpeed() {

		return moveSpeed;
	}

    /**
     * Get the image filepaths of this person when idling and facing right.
     * @return image filepaths
     */
	public String[] getIdleRightImages() {

		return idleRightImages;
	}

    /**
     * Get the image filepaths of this person when idling and facing left.
     * @return image filepaths
     */
	public String[] getIdleLeftImages() {

		return idleLeftImages;
	}

    /**
     * Get the image filepaths of this person when moving and facing right.
     * @return image filepaths
     */
	public String[] getMoveRightImages() {

		return moveRightImages;
	}

    /**
     * Get the image filepaths of htis person when moving and facing left.
     * @return image filepaths
     */
	public String[] getMoveLeftImages() {

		return moveLeftImages;
	}

    /**
     * Get the current x position of this person in pixels.
     * @return the current x position
     */
	public double getCurrentX() {

		return currentX;
	}

    /**
     * Set the current x position of this person in pixels.
     * @param x the position to set currentX to
     */
	public void setCurrentX(double x) {

		this.currentX = x;
	}

    /**
     * Get the current y position of this person in pixels.
     * @return the current y position
     */
	public double getCurrentY() {

		return currentY;
	}

    /**
     * Set the current y position of this person in pixels.
     * @param y the position to set currentY to
     */
	public void setCurrentY(double y) {

		this.currentY = y;
	}

    /**
     * Get the new x position of this person in pixels.
     * @return the new position
     */
	public double getNewX() {

		return newX;
	}

    /**
     * Set the new x position of this person in pxels
     * @param x the positon to set newX to
     */
	public void setNewX(double x) {

		this.newX = x;
	}

    /**
     * Get the new y position of this person in pixels.
     * @return the new y position
     */
	public double getNewY() {

		return newY;
	}

    /**
     * Set the new y position of this person in pixels
     * @param y the position to set newY to
     */
	public void setNewY(double y) {

		this.newY = y;
	}

    /**
     * Get the movement distance on x-axis of this person in pixels
     * @return the movement distance this person makes on x axis
     */
	public double getXMovementDistance() {

		return xMovementDistance;
	}

    /**
     * Set the movement distance on x-axis of this person in pixels
     * @param newXMovementDistance the movement distance to set to
     */
	public void setXMovementDistance(double newXMovementDistance) {

		this.xMovementDistance = newXMovementDistance;
	}

    /**
     * Get the movement distance on y-axis of this person in pixels
     * @return the movement distance this person makes on y axis
     */
	public double getYMovementDistance() {

		return yMovementDistance;
	}

    /**
     * Set the movement distance on y-axis of this person in pixels
     * @param newYMovementDistance the movement distance to set to
     */
	public void setYMovementDistance(double newYMovementDistance) {

		this.yMovementDistance = newYMovementDistance;
	}

    /**
     * Get the direction this person is facing.
     * @return the direction this person is facing
     */
	public String getDirectionFacing() {

		return directionFacing;
	}

    /**
     * Set the direction this person is facing.
     * @param directionFacing the new direction this person is facing
     */
	public void setDirectionFacing(String directionFacing) {

		this.directionFacing = directionFacing;
	}

	/**
	 * Given a direction, move the person's X or Y coordinates.
	 * 
	 * @param map           The Map object.
	 * @param moveDirection The next move direction of the character.
	 * @return True or False depending on whether or not the character is able to
	 *         make this move.
	 */
	public boolean move(Map map, String moveDirection) {

		boolean moveStatus = false;
		if (moveDirection.equals("up")) {

			if (!checkForWall(map, 0.0, yMovementDistance)) {

				setNewY(currentY + yMovementDistance);
				moveStatus = true;
			}
		} else if (moveDirection.equals("down")) {

			if (!checkForWall(map, 0.0, -yMovementDistance)) {

				setNewY(currentY - yMovementDistance);
				moveStatus = true;
			}
		} else if (moveDirection.equals("left")) {

			if (!checkForWall(map, -xMovementDistance, 0.0)) {

				setNewX(currentX - xMovementDistance);
				moveStatus = true;
			}
		} else if (moveDirection.equals("right")) {

			if (!checkForWall(map, xMovementDistance, 0.0)) {

				setNewX(currentX + xMovementDistance);
				moveStatus = true;
			}
		}
		return moveStatus;
	}

	/**
	 * Given the Hero's current X and Y coordinates, determine if there is a wall in
	 * the next movement direction.
	 * 
	 * @param map   The Map object.
	 * @param xMove The potential distance that the character will move on the X
	 *              axis.
	 * @param yMove The potential distance that the character will move on the Y
	 *              axis.
	 * @return if there is a wall in the next movement direction
	 */
	public boolean checkForWall(Map map, double xMove, double yMove) {

		boolean wallCheck = false;
		if (xMove != 0) {

			for (MapObject wall : map.getWalls()) {

				if (currentY == wall.getMapObjectY() && currentX + xMove == wall.getMapObjectX()) {

					wallCheck = true;
				}
			}
		} else if (yMove != 0) {

			for (MapObject wall : map.getWalls()) {

				if (currentX == wall.getMapObjectX() && currentY + yMove == wall.getMapObjectY()) {

					wallCheck = true;
				}
			}
		}
		return wallCheck;
	}

	/**
	 * Given a Person's current X and Y coordinates, determine if it has intersected
	 * with another Person and, if so, return that Person.
	 * 
	 * @param map The Map object.
	 * @return True or False depending on whether or not the character has
	 *         intersected with another Person object.
	 */
	public Enemy intersectWithPerson(Map map) {

		Enemy personIntersectedWith = null;
		for (Enemy enemy : map.getEnemies()) {

			if (currentY == enemy.getCurrentY() && currentX == enemy.getCurrentX()) {

				personIntersectedWith = enemy;
			}
		}
		return personIntersectedWith;
	}

	/**
	 * Draw the idle image of the Person object that corresponds to the given index.
	 * 
	 * @param imageIndex The index number of the image to draw to the screen.
	 */
	public void drawIdle(int imageIndex) {

		if (directionFacing.equals("right")) {

			PennDraw.picture(currentX, currentY, idleRightImages[imageIndex], personHeight, personWidth);
		} else if (directionFacing.contentEquals("left")) {

			PennDraw.picture(currentX, currentY, idleLeftImages[imageIndex], personHeight, personWidth);
		}
	}

	/**
	 * Draw the move image of the Person object that corresponds to the given index.
	 * 
	 * @param imageIndex The index number of the image to draw to the screen.
	 */
	public void drawMoving(int imageIndex) {

		if (directionFacing.equals("right")) {

			PennDraw.picture(currentX, currentY, moveRightImages[imageIndex], personHeight, personWidth);
		} else if (directionFacing.contentEquals("left")) {

			PennDraw.picture(currentX, currentY, moveLeftImages[imageIndex], personHeight, personWidth);
		}
	}
    /**
     * Get the attacks that this person has available,
     * given this person's energy level.
     * To be considered available
     * energy level of person must be greater than or equal to energy required of attack.
     * @return list of attacks that are available to person
     */
    public ArrayList<Attack> getAvailableAttacks() {
        ArrayList<Attack> availableAttacks = new ArrayList<>();

        //for each attack in attackOptions
        //add this attack to availableAttacks
        //if current energy level is greater than or equal to energy required of attack
        for(Attack attack : this.attackOptions) {
            if(isAvailableAttack(attack)) {
                availableAttacks.add(attack);
            }
        }

        return availableAttacks;
    }

    /**
     * Determine if the attack is available
     * based on if the energy it takes to perform attack is less than or equal to current energy level of this person.
     * @param attack the attack to evaluate
     * @return if the attack is available
     */
    public boolean isAvailableAttack(Attack attack) {
        return attack.getEnergyRequired() <= energy;
    }

    
    /**
     * Given an id, find the attack of this person from attackOptions that matches this id.
     * If no matches, returns null.
     * @param id unique identifier of attack to find
     * @return the attack of this person with matching id, null if not found
     */
    public Attack getAttackById(int id) {
        Attack matchingAttack = null;

        //for each attack
        //check if id of attack matches provided id
        //if so set matchingAttack to attack
        for(Attack attack : this.attackOptions) {
            if(attack.getId() == id) {
                matchingAttack = attack;
            }
        }

        return matchingAttack;
    }

    /**
     * Add attack to list of available attackOptions.
     * Will be inserted in sorted position of attackOptions.
     * @param attack attack that is being added
     */
    public void learnAttack(Attack attack) {
        this.attackOptions.add(attack);
        Collections.sort(attackOptions);
    }

    /**
     * Restore energy at a random rate and add it to current energy level.
     * @return the amount of energy restored
     */
    public int restoreEnergy() {
        double percent = Math.random();
        int amountToAdd = 0;

        if(percent < .05) { //5% chance of 100
            amountToAdd = 100;
        } else if (percent < .25) { // 20% chance of 50
            amountToAdd = 50;
        } else if (percent < .75) { // 50% chance of 25
            amountToAdd = 25;
        } else if (percent < .95) { // 5% chance of 10
            amountToAdd = 10;
        }
        
        int amountActuallyAdded = addEnergy(amountToAdd);
        return amountActuallyAdded;
    }

    /**
     * Given a positive energyAmount,
     * add it to current amount of energy in this person.
     * New energy cannot exceed maxEnergy.
     * @param energyAmount the amount to add to current energy level
     * @return the amount of energy actually added, given limit of maxEnergy level
     */
    public int addEnergy(int energyAmount) {
        if(energyAmount < 0) { throw new IllegalArgumentException("energyAmount below 0: " + energyAmount);}
        //if adding the energyAmount to current energy level is above maxEnergy
        //set energyAmount to amount that will fill energy to maxEnergy
        if(energy + energyAmount > maxEnergy) {
            energyAmount = maxEnergy - energy;
        }
        energy += energyAmount;

        return energyAmount;
    }

    /**
     * Given a positive energyAmount,
     * subtract it from the current amount of energy in this person.
     * Energy cannot go below 0.
     * @param energyAmount the amount to subract from current energy level
     */
    public void useEnergy(int energyAmount) {
        if(energyAmount < 0) { throw new IllegalArgumentException("energyAmount below 0: " + energyAmount);}
        this.energy -= energyAmount;
        if(this.energy < 0) this.energy = 0;
    }

     /**
     * Given a positive healthAmount,
     * add it to current amount of health in this person.
     * New health cannot exceed maxHealth.
     * @param healthAmount the amount to add to current health level
     * @return the amount of health actually added, given limit of maxHealth level
     */
    public int heal(int healthAmount) {
        if(healthAmount < 0) { { throw new IllegalArgumentException("healthAmount below 0: " + healthAmount);}}
        //if adding the healthAmount to current energy level is above maxEnergy
        //set healthAmount to amount that will fill energy to maxEnergy
        if(health + healthAmount > maxHealth) {
            healthAmount = maxHealth - health;
        }
        health += healthAmount;

        return healthAmount;
    }

    /**
     * Given a positive damageAmount,
     * subtract it from current amount of health in this person.
     * Health cannot go below 0.
     * @param damageAmount the amount to subtract from current health level
     * @return the amount of damage actually sustained
     */
    public int takeDamage(int damageAmount) {
        if(damageAmount < 0) { throw new IllegalArgumentException("damageAmount below 0: " + damageAmount);}
        if(this.health - damageAmount < 0) {
            damageAmount = this.health;
        }
        this.health -= damageAmount;
        return damageAmount;
    }

    /**
     * Determine if this person is alive,
     * as determined by if health is greater than 0.
     * @return if health is greater than 0
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Get a random attack that this person can use given current energy level.
     * Returns null if no attacks are available.
     * @return
     */
    public Attack getRandomAvailableAttack() {
        Attack randomAttack = null;
        int numAttacksAvailable = getAvailableAttacks().size();
        if(numAttacksAvailable > 0) {
            int attackSelection = (int) (Math.floor(Math.random() * numAttacksAvailable));
            randomAttack = getAvailableAttacks().get(attackSelection);
        }

        return randomAttack;
    }

    /**
     * Given an attack and personToAttack,
     * attempt to attack opponent.
     * Attack will deduct energy level of this person if it succeeds or fails.
     * Attack will inflict damage of personToAttack if it succeeds only.
     * @param attack the attack of this person
     * @param personToAttack the person being attacked
     * @return if the attack was a success
     */
    public boolean attack(Attack attack, Person personToAttack) {
        //if attack is not available to this person
        //attack is not a success
        //do not attempt attack
        if(!isAvailableAttack(attack)) {
            return false;
        }

        //remove energy required to attempt attack
        this.useEnergy(attack.getEnergyRequired());

        //determine if attack is successful
        boolean attackIsSuccess = attack.attempt();
        if(!attackIsSuccess) { //attack failed so do nothing
            return false;
        } else { //attack succeeded so deal damage
            personToAttack.takeDamage(attack.getAttackDamage());
            return true;
        }
    }	
	
	/**
     * Transform available attacks into map of attack id to it's description.
     * Always include the option to restore energy as the first option.
     * @return
     */
    public HashMap<Integer, String> getAvailableActionsAsMap() {
        HashMap<Integer, String> attackIdToDescription  = mapIdToDescription(this.getAvailableAttacks());
        //add restore energy as possible action
        //if energy is below maxEnergy
        if(energy < maxEnergy) {
            attackIdToDescription.put(0, "Restore energy");
        }
        
        return attackIdToDescription;
    }

    /**
     * Given a list of attacks, transform them into a map of attack id to attack description.
     * @param attacks the attacks to transform
     * @return the map of attack ids to attack descriptions.
     */
    private HashMap<Integer, String> mapIdToDescription(ArrayList<Attack> attacks) {
        HashMap<Integer, String> attackIdToDescription  = new HashMap<>();

        for(Attack attack : attacks) {
            attackIdToDescription.put(attack.getId(), attack.getDescription());
        }

        return attackIdToDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name) && health == person.health && energy == person.energy && Objects.equals(attackOptions, person.attackOptions) && personHeight == person.personHeight && personWidth == person.personWidth && Objects.equals(idleRightImages, person.idleRightImages) && Objects.equals(idleLeftImages, person.idleLeftImages) && Objects.equals(moveRightImages, person.moveRightImages) && Objects.equals(moveLeftImages, person.moveLeftImages) && currentX == person.currentX && currentY == person.currentY && newX == person.newX && newY == person.newY && moveSpeed == person.moveSpeed && xMovementDistance == person.xMovementDistance && yMovementDistance == person.yMovementDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, health, energy, attackOptions, personHeight, personWidth, idleRightImages, idleLeftImages, moveRightImages, moveLeftImages, currentX, currentY, newX, newY, moveSpeed, xMovementDistance, yMovementDistance);
    }


    @Override
    public String toString() {
        return "Person[" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", health='" + getHealth() + "'" +
            ", maxHealth='" + getMaxHealth() + "'" +
            ", energy='" + getEnergy() + "'" +
            ", maxEnergy='" + getMaxEnergy() + "'" +
            ", attackOptions='" + getAttackOptions() + "'" +
            "]";
    }

    
}