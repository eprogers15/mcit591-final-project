import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class PersonTest {
	
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
	public void testUseEnergy() {
		//creates Hero with 15 energy
		Person p1 = createHero();
		p1.useEnergy(5);
		int expected = 10;
		assertEquals(expected, p1.getEnergy());
	}

	@Test
	public void testAddEnergyAboveMax() {
		//creates Hero with max 50 energy
		Person p1 = createHero();
		p1.addEnergy(95);
		int expected = 50;
		assertEquals(expected, p1.getEnergy());
	}

	@Test
	public void testTakeDamage() {
		//creates Hero with 50 health
		Person p1 = createHero();
		p1.takeDamage(35);
		int expected = 15;
		assertEquals(expected, p1.getHealth());
	}

	@Test
	public void testHeal() {
		//creates Hero with 50 health
		Person p1 = createHero();
		p1.heal(25);
		int expected = 75;
		assertEquals(expected, p1.getHealth());
	}

	@Test
	public void testHealAboveMax() {
		//creates Hero with max 100 health
		Person p1 = createHero();
		p1.heal(500);
		int expected = 100;
		assertEquals(expected, p1.getHealth());
	}
	
	@Test
	public void testDamageInflictedAboveHealth() {
		//creates Hero with 50 health
		Person p1 = createHero();
		p1.takeDamage(55);
		int expected = 0;
		boolean expectedIsAlive = false;
		assertEquals(expected, p1.getHealth());
		assertEquals(expectedIsAlive, p1.isAlive());
	}

	@Test
	public void testIsAlive() {
		//creates a Hero with 50 health
		Person p1 = createHero();
		p1.takeDamage(20);
		boolean expectedIsAlive = true;
		assertEquals(expectedIsAlive, p1.isAlive());
	}

	@Test
	public void testIsAttackAvailableAfterEnergyUsage() {
		//creats a Hero with 15 energy and adds two new attacks
		Person p1 = createHero();
		Attack lowEnergyAttack = new Attack("test", 5, 5, 0.10);
		Attack highEnergyAttack = new Attack("test", 7, 7, 0.01);
		p1.learnAttack(lowEnergyAttack);
		p1.learnAttack(highEnergyAttack);

		//both attacks are options
		//both attacks are available
		assertEquals(2, p1.getAttackOptions().size());
		assertEquals(2, p1.getAvailableAttacks().size());

		//use energy
		p1.useEnergy(10);

		//both attacks are still options
		//only lowEnergyAttack is available
		assertEquals(2, p1.getAttackOptions().size());
		assertEquals(1, p1.getAvailableAttacks().size());
		assertEquals(lowEnergyAttack, p1.getAvailableAttacks().get(0));
	}

	@Test
	public void testSpendXpOnCurrentAndAccruedXp() {
		//creats Hero with 0 xp
		Hero p1 = createHero();

		//adds xp
		p1.addXp(10);
		assertEquals(10, p1.getAccruedXp());
		assertEquals(10, p1.getCurrentXp());

		//spends xp
		p1.spendXp(5);

		//accrued xp never goes down
		//current xp goes down
		assertEquals(10, p1.getAccruedXp());
		assertEquals(5, p1.getCurrentXp());
	}
}

