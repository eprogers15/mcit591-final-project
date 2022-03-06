/**
 * @title GameRunner
 * 
 * @description Initiates game starting.
 * 
 * @author Emmett Rogers
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class GameRunner {

	/**
	 * The main method that initiates the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Gameplay gameplay = new Gameplay();
		gameplay.playGame();
	}
}