import java.util.Objects;

/**
 * @title Turn
 * 
 * @description Handles processing duel actions and reverting them (i.e. undoing
 *              them). Represents the actions completed, including attacker,
 *              recipient, attack, if the attack was attempted and successful,
 *              damage dealt, energy used and result.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Turn {
    private final int id;
    private Person attacker;
    private Person recipient;
    private Attack attack;
    private boolean attackWasAttempted;
    private boolean attackWasSuccessful;
    //must be 0 or more
    private int damageDealtToRecipient;
    //negative if attacker restored energy instead of attacking
    private int energyUsedByAttacker;
    private String resultString;

    private static int lastAssignedId = 0;

    /**
     * Constructor with attacker, attack, recipient.
     * @param attacker the person attempting the attack
     * @param attack the attack to attempt
     * @param recipient the recipient of the attack
     */
    public Turn(Person attacker, Attack attack, Person recipient) {
        lastAssignedId++;
        this.id = lastAssignedId;
        this.attacker = attacker;
        this.recipient = recipient;
        this.attack = attack;
        this.attackWasAttempted = false;
        this.attackWasSuccessful = false;
        this.damageDealtToRecipient = 0;
        this.energyUsedByAttacker = 0;
        this.resultString = "";
    }

    /**
     * Get unique id of this turn
     * @return the id of this turn
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the attacking person of this turn.
     * @return the attacker of this turn
     */
    public Person getAttacker() {
        return this.attacker;
    }

    /**
     * Get the recipient person of this turn.
     * @return the recipient of this turn
     */
    public Person getRecipient() {
        return this.recipient;
    }

    /**
     * Get the attack that was planned.
     * Null if no attack was planned.
     * @return
     */
    public Attack getAttack() {
        return this.attack;
    }

    /**
     * Get if the attack was attempted by the attacker.
     * @return if the attack was attempted by attacker
     */
    public boolean getAttackWasAttempted() {
        return this.attackWasAttempted;
    }

    /**
     * Get if the attack hit the recipient.
     * @return if the attack hit the recipient
     */
    public boolean getAttackWasSuccessful() {
        return this.attackWasSuccessful;
    }

    /**
     * Get damage dealt to recipient.
     * @return the amount of damage dealt to recipient
     */
    public int getDamageDealtToRecipient() {
        return this.damageDealtToRecipient;
    }

    /**
     * Get energy used by attacker, negative if attacker restored energy.
     * @return the amount of energy used by attacker, negative if restored
     */
    public int getEnergyUsedByAttacker() {
        return this.energyUsedByAttacker;
    }

    /**
     * Get the result of the turn.
     * Blank string until it has been processed.
     * @return string representation of the result of the turn after it has happened
     */
    public String getResultString() {
        return this.resultString;
    }    

    /**
     * Process the turn:
     * restore if attackToAttempt is null
     * otherwise attempt attack
     * In both cases save the details of the turn.
     * @return
     */
    public String process() {
        //if an empty or null attack was given on construction, attacker restores
        //otherwise attacker attacks
        if(attack == null) { //restore energy
            resultString = processRestore(attacker);
        } else { //attack
            resultString = processAttack(attacker, attack, recipient);
        }

        return resultString;
    }

    /**
     * Given an attacker, attack, and recipient,
     * attempt the attack and save details of the turn.
     * @param attacker the person attempting the attack
     * @param attack the attack they are attempting
     * @param recipient the recipient of the attack
     * @return string with information on the attacker, attack, and if it hit
     */
    private String processAttack(Person attacker, Attack attackToAttempt, Person recipient) {
        //save current stats before attack
        int recipientHealthBeforeAttack = recipient.getHealth();
        int attackerEnergyBeforeAttack = attacker.getEnergy();

        //attempt attack
        boolean canAttemptAttack = attacker.isAvailableAttack(attack);
        boolean attackHit = attacker.attack(attackToAttempt, recipient);
        
        //save turn details
        setTurnDetails(canAttemptAttack, attackHit, recipientHealthBeforeAttack - recipient.getHealth(), attackerEnergyBeforeAttack - attacker.getEnergy());

        //create string for user to know what happened
        String attackString = createAttackStatusString(attacker, attackToAttempt, recipient, attackHit);
        
        return attackString;
    }

    /**
     * Given a person,
     * attempt to restore energy and save details of the turn.
     * @param person the person to restore energy
     * @return string with information on energy string
     */
    private String processRestore(Person person) {
        //save current stats before attack
        int attackerEnergyBeforeAttack = attacker.getEnergy();

        //restore energy
        int energyRestored = person.restoreEnergy();

        //save turn details
        //attack was not attempted, not successful and 0 damage was dealt
        //if energy was successfully restored, save energy used as negative value
        setTurnDetails(false, false, 0, -energyRestored);

        //create string for user to know what happened
        String restoreString = createEnergyStatusString(person, energyRestored);
        
        return restoreString;
    }

    /**
     * Revert attacker and recipient states back to before this turn was processed.
     */
    public String revert() {
        //attacker attempted attack
        //add energy used back to attacker energy
        if(attackWasAttempted) {
            attacker.addEnergy(energyUsedByAttacker);
        }

        //attacker hit recipient
        //add damage dealt back to recipient health
        if(attackWasSuccessful) {
            recipient.heal(damageDealtToRecipient);
        }

        //attacker restored energy
        //subtract energy restored from attacker
        if(energyUsedByAttacker < 0) {
            attacker.useEnergy(-energyUsedByAttacker);
        }

        return resultString;
    }

    /**
     * Set the details of the turn.
     * @param attackWasAttempted if the attack was attempted
     * @param attackWasSuccessful if the attack hit recipient
     * @param damageDealt amount of damage dealt to recipient
     * @param energyUsed amount of energy used by attacker, negative if energy was restored.
     */
    private void setTurnDetails(boolean attackWasAttempted, boolean attackWasSuccessful, int damageDealt, int energyUsed) {
        this.attackWasAttempted = attackWasAttempted;
        this.attackWasSuccessful = attackWasSuccessful;
        this.damageDealtToRecipient = damageDealt;
        this.energyUsedByAttacker = energyUsed;
    }


    /**
     * Make string to indicate who attacked, the status of the attack (hit or miss), and the name of the attack.
     * Useful for indicating to user what is going on.
     * @param attacker person initiating attack
     * @param attack the attack they used
     * @param recipient the recipient of the attack
     * @param attackWasSuccessful whether or not the attack was successful
     * @return string that inidicates what happened with an attack
     */
    private String createAttackStatusString(Person attacker, Attack attackAttempted, Person recipient, boolean attackWasSuccessful) {
        String attackStatus = "missed";
        if(attackWasSuccessful) {
            attackStatus = "hit";
        }
        String actionString = attacker.getName() + "'s " + attackAttempted.getName().toLowerCase() + " " + attackStatus + "!";

        return actionString;
    }

    /**
     * Make string to indicate who restored energy and how much they restored.
     * @param person the person restoring energy
     * @param amountEnergyRestored the amount of energy they restored
     * @return
     */
    private String createEnergyStatusString(Person person, int amountEnergyRestored) {
        return person.getName() + " restored " + amountEnergyRestored + " energy!";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Turn)) {
            return false;
        }
        Turn turn = (Turn) o;
        return id == turn.id && Objects.equals(attacker, turn.attacker) && Objects.equals(recipient, turn.recipient) && Objects.equals(attack, turn.attack) && attackWasAttempted == turn.attackWasAttempted && attackWasSuccessful == turn.attackWasSuccessful && damageDealtToRecipient == turn.damageDealtToRecipient && energyUsedByAttacker == turn.energyUsedByAttacker && Objects.equals(resultString, turn.resultString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attacker, recipient, attack, attackWasAttempted, attackWasSuccessful, damageDealtToRecipient, energyUsedByAttacker, resultString);
    }
    
    @Override
    public String toString() {
        return "Turn[" +
            " id='" + getId() + "'" +
            ", attacker='" + getAttacker().getName() + "'" +
            ", recipient='" + getRecipient().getName() + "'" +
            ", attack='" + getAttack() + "'" +
            ", attackWasAttempted='" + getAttackWasAttempted() + "'" +
            ", attackWasSuccessful='" + getAttackWasSuccessful() + "'" +
            ", damageDealtToRecipient='" + getDamageDealtToRecipient() + "'" +
            ", energyUsedByAttacker='" + getEnergyUsedByAttacker() + "'" +
            ", resultString='" + getResultString() + "'" +
            "]";
    }



}