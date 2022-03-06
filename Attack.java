import java.util.Objects;

/**
 * @title Attack
 * 
 * @description Represents an attack in battle that requires attack damage, energy required to perform attack and a success rate.
 * Handles the attempt of itself, determining if it was successful based on successRate.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Attack implements Comparable<Attack> {
    private final int id;
    private String name;
    private int attackDamage;
    private int energyRequired;
    private double successRate;

    private static int lastAssignedId = 0;

    /**
     * Constructor
     * @param name the name of this Attack
     * @param attackDamage the amount of damage this Attack inflicts if successful
     * @param energyRequired the amount of energy this Attack requires to attempt
     * @param sucessRate the chance this Attack is performed successfully
     */
    public Attack(String name, int attackDamage, int energyRequired, double successRate) {
        if(attackDamage < 0) { throw new IllegalArgumentException("attackDamage below 0: " + attackDamage);}
        if(energyRequired < 0) { throw new IllegalArgumentException("energyRequired below 0: " + energyRequired);}
        if(successRate < 0.0 || successRate > 1.0) { throw new IllegalArgumentException("successRate out of range 0.0 - 1.0, inclusive: " + successRate);}
        lastAssignedId++;
        id = lastAssignedId;
        this.name = name;
        this.attackDamage = attackDamage;
        this.energyRequired = energyRequired;
        this.successRate = successRate;
    }

    /**
     * Get the name of this attack.
     * @return the name of this attack
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the amount of damage this attack does if successful.
     * @return the amount of damage of this attack
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * Get the amount of energy this attack requires to attempt
     * @return the amount of energy this attack requires
     */
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    /**
     * Get the chance this attack successfully works.
     * @return the chance this attack will work
     */
    public double getSuccessRate() {
        return this.successRate;
    }

    /**
     * Get the unique id of this attack.
     * @return the id of this attack
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the description of this attack to display.
     * @return the description of this attack
     */
    public String getDescription() {
        return this.name + ": " + this.attackDamage + " dmg, " 
        + this.energyRequired + " energy, " + String.format("%.1f", this.successRate * 100) + "% hit";
    }

    /**
     * Attempt attack, which is successful if a random value between 0 (inclusive) and 1 (exclusive) is less than the success rate of attack.
     * @param attack the attack to attempt
     * @return if attack is successful
     */
    public boolean attempt() {
        return Math.random() < this.getSuccessRate();
    }

    /**
     * {@inheritDoc}
     * Compare attacks by difference in energy - lower energy required coming first.
     * If energy required is equal, compare by difference in damage - lower damage coming first
     */
    @Override
    public int compareTo(Attack otherAttack) {
        int energyDifference = this.getEnergyRequired() - otherAttack.getEnergyRequired();
        if(energyDifference == 0) {
            int damageDifference = this.getAttackDamage() - otherAttack.getAttackDamage();
            return damageDifference;
        } else {
            return energyDifference;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attack)) {
            return false;
        }
        Attack attack = (Attack) o;
        return id == attack.id && Objects.equals(name, attack.name) && attackDamage == attack.attackDamage && energyRequired == attack.energyRequired && successRate == attack.successRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attackDamage, energyRequired, successRate);
    }

    @Override
    public String toString() {
        return "Attack[" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", attackDamage='" + getAttackDamage() + "'" +
            ", energyRequired='" + getEnergyRequired() + "'" +
            ", successRate='" + getSuccessRate() + "'" +
            "]";
    }

}