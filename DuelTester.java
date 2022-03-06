import java.util.ArrayList;

/**
 * @title DuelTester
 * 
 * @description Tests DuelManager and Duel functionality isolated away from the full game.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */
public class DuelTester {
    public static void main(String[] args) {
        // create example data
		ArrayList<Attack> attackOptions = new ArrayList<Attack>();
		attackOptions.add(new Attack("Swipe", 2, 1, 0.75));
		attackOptions.add(new Attack("Punch", 5, 4, 0.3333));
		attackOptions.add(new Attack("Kick", 10, 10, 0.11111));
		attackOptions.add(new Attack("One-Punch", 100, 50, 0.001));

		Enemy troll1 = new Enemy("Larry", 10, 10, 100, 100, attackOptions, 5, 78.0, 39.0,
				"images/troll/trollIdleRightImages/troll_Idle_Right_",
				"images/troll/trollIdleLeftImages/troll_Idle_Left_",
				"images/troll/trollMoveRightImages/troll_Move_Right_",
				"images/troll/trollMoveLeftImages/troll_Move_Left_", "left");

		Enemy goblin1 = new Enemy("Green Goblin", 10, 10, 100, 100, attackOptions, 5, 56.0, 26.0,
				"images/goblin/goblinIdleRightImages/goblin_Idle_Right_",
				"images/goblin/goblinIdleLeftImages/goblin_Idle_Left_",
				"images/goblin/goblinMoveRightImages/goblin_Move_Right_",
				"images/goblin/goblinMoveLeftImages/goblin_Move_Left_", "right");

		
		Enemy wolf = new Enemy("Wolfy", 10, 10, 100, 100, attackOptions, 5, 12.0, 43.0,
				"images/wolf/wolfIdleRightImages/wolf_Idle_Right_", "images/wolf/wolfIdleLeftImages/wolf_Idle_Left_",
				"images/wolf/wolfMoveRightImages/wolf_Move_Right_", "images/wolf/wolfMoveLeftImages/wolf_Move_Left_",
				"right");

		Hero knight = new Hero("Knighty", 100, 100, 100, 150, attackOptions, 75.0, 52.0, "left");

		//test processing and reverting turns on data (duel)
		Duel duelTest = new Duel(knight, troll1);
		System.out.println("Turn #" + duelTest.getTotalNumberTurnsTaken() + ": " + duelTest + "\n");
		duelTest.processTurn();
		System.out.println("Turn #" + duelTest.getTotalNumberTurnsTaken() + ": " + duelTest + "\n");
		duelTest.processTurn();
		System.out.println("Turn #" + duelTest.getTotalNumberTurnsTaken() + ": " + duelTest + "\n");
		duelTest.revertTurns(2);
		System.out.println("Turn #" + duelTest.getTotalNumberTurnsTaken() + ": " + duelTest + "\n");
			
		//test duel manager
        DuelManager dmTest = new DuelManager(knight, troll1);
		dmTest.run();
    }

}