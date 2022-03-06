import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DuelTest {

    private Duel createDuel() {
        String name = "test";
	    int health = 50;
	    int maxHealth = 100;
	    int energy = 15;
	    int maxEnergy = 50;
	    ArrayList<Attack> attacks = new ArrayList<Attack>();
        attacks.add(new Attack("testAttack", 5, 5, 0.5));
        double x = 6.0;
	    double y = 52.0;
        String directionFacing = "Left";
        Hero p1 = new Hero(name, health, maxHealth, energy, maxEnergy, attacks, x, y, directionFacing);
        Hero p2 = new Hero(name, health, maxHealth, energy, maxEnergy, attacks, x, y, directionFacing);
        return new Duel(p1, p2);
    }
    
    @Test
    public void testSavedNewTurn() {
        //create duel with no turns
        Duel duel = createDuel();

        assertEquals(0, duel.getTotalNumberTurnsTaken());
        duel.processTurn();
        assertEquals(1, duel.getTotalNumberTurnsTaken());
    }

    @Test
    public void testRevertPreviousTurn() {
        //create duel with no turns
        Duel duel = createDuel();

        //process one turn and then revert it
        assertEquals(0, duel.getTotalNumberTurnsTaken());
        duel.processTurn();
        assertEquals(1, duel.getTotalNumberTurnsTaken());
        duel.revertPreviousTurn();
        assertEquals(0, duel.getTotalNumberTurnsTaken());
    }

    @Test
    public void testRevertMultipleTurns() {
        //create duel with no turns
        Duel duel = createDuel();

        //process multiple turns and then revert them
        assertEquals(0, duel.getTotalNumberTurnsTaken());
        duel.processTurn();
        duel.processTurn();
        assertEquals(2, duel.getTotalNumberTurnsTaken());
        duel.revertTurns(2);
        assertEquals(0, duel.getTotalNumberTurnsTaken());
    }

    @Test
    public void testWinnerAndLoserSetWhenDuelIsFinished() {
        //create duel with no winner or loser
        Duel duel = createDuel();

        //process turns until one combatant is dead
        assertNull(duel.getWinner());
        assertNull(duel.getLoser());
        while(duel.combatantsAreAllAlive()) {
            duel.processTurn();
        }
        assertNotNull(duel.getWinner());
        assertNotNull(duel.getLoser());
	}
}