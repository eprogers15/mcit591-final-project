import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TurnTest {

    private Turn createAttackTurn() {
        Hero p1 = createHero();
        Hero p2 = createHero();
        Attack attack = new Attack("test", 5, 5, 1);
        p1.learnAttack(attack);
        return new Turn(p1, attack, p2);
    }

    private Turn createRestoreTurn() {
        Hero p1 = createHero();
        Hero p2 = createHero();
        return new Turn(p1, null, p2);
    }

    private Hero createHero() {
		String name = "test";
		int health = 50;
		int maxHealth = 100;
		int energy = 15;
		int maxEnergy = 50;
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		double x = 6.0;
		double y = 52.0;
		String directionFacing = "Left";
		return new Hero(name, health, maxHealth, energy, maxEnergy, attacks, x, y, directionFacing);
	}
    
    @Test
    public void testResultStringSetAfterAttackProcessed() {
        Turn turn = createAttackTurn();
        assertEquals("", turn.getResultString());
        turn.process();
        assertNotEquals("", turn.getResultString());
    }

    @Test
    public void testProcessAttackHasEffectsOnAttackerEnergyAndRecipientHealth() {
        Turn turn = createAttackTurn();
        int p1Energy = turn.getAttacker().getEnergy();
        int p2Health = turn.getRecipient().getHealth();
        int energyUsed = turn.getAttack().getEnergyRequired();
        int damageInflicted = turn.getAttack().getAttackDamage();

        turn.process();

        //attack will always be attempted and always hit
        assertEquals(true, turn.getAttackWasAttempted());
        assertEquals(true, turn.getAttackWasSuccessful());
        assertEquals(true, turn.getDamageDealtToRecipient() > 0);
        assertEquals(p1Energy - energyUsed, turn.getAttacker().getEnergy());
        assertEquals(p2Health - damageInflicted, turn.getRecipient().getHealth());

    }

    @Test
    public void testRevertAttackPutsAttackerEnergyBackAndRecipientHealthBack() {
        Turn turn = createAttackTurn();
        int attackerEnergyPreAttack = turn.getAttacker().getEnergy();
        int recipientHealthPreAttack = turn.getRecipient().getHealth();
       
        turn.process();
        turn.revert();

        //attack will always be attempted and always hit
        assertEquals(true, turn.getAttackWasAttempted());
        assertEquals(true, turn.getAttackWasSuccessful());
        assertEquals(true, turn.getDamageDealtToRecipient() > 0);
        assertEquals(attackerEnergyPreAttack, turn.getAttacker().getEnergy());
        assertEquals(recipientHealthPreAttack, turn.getRecipient().getHealth());
    }

    @Test
    public void testResultStringSetAfterRestoreProcessed() {
        Turn turn = createRestoreTurn();
        assertEquals("", turn.getResultString());
        turn.process();
        assertNotEquals("", turn.getResultString());
    }

    @Test 
    public void testProcessRestoreHasEffectOnAttackerEnergyAndNoEffectOnRecipientHealth() {
        Turn turn = createRestoreTurn();
        int attackerEnergyPreRestore = turn.getAttacker().getEnergy();
        int recipientHealthPreRestore = turn.getRecipient().getHealth();
       
        turn.process();

        //restore will always be performed
        assertEquals(false, turn.getAttackWasAttempted());
        assertEquals(false, turn.getAttackWasSuccessful());
        assertEquals(true, attackerEnergyPreRestore <= turn.getAttacker().getEnergy());
        assertEquals(true, recipientHealthPreRestore == turn.getRecipient().getHealth());
    }

    @Test
    void testRevertRestorePutsAttackerEnergyBackAndNoEffectOnRecipientHealth() {
        Turn turn = createRestoreTurn();
        int attackerEnergyPreRestore = turn.getAttacker().getEnergy();
        int recipientHealthPreRestore = turn.getRecipient().getHealth();
       
        turn.process();
        turn.revert();

        //restore will always be processed and reversed
        assertEquals(false, turn.getAttackWasAttempted());
        assertEquals(false, turn.getAttackWasSuccessful());
        assertEquals(attackerEnergyPreRestore, turn.getAttacker().getEnergy());
        assertEquals(recipientHealthPreRestore, turn.getRecipient().getHealth());
    }
}