import java.util.ArrayList;
import java.util.Objects;

/**
 * @title Duel
 * 
 * @description Models the data of a duel, including the two combatants, whose turn it is, saved details from previous turns and the winner and loser.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class Duel {
    private Person leftCombatant;
    private Person rightCombatant;
    //determines which Person's turn it is
    // 0 is leftCombatant
    // 1 is rightCombatant
    private int turnIndex;
    private ArrayList<Turn> turns;
    private Person winner;
    private Person loser;

    /**
     * Constructor with two combatants provided - leftCombatant starts.
     * @param leftCombatant the first person in duel
     * @param rightCombatant the second person in duel
     */
    public Duel(Person leftCombatant, Person rightCombatant) {
        this.leftCombatant = leftCombatant;
        this.rightCombatant = rightCombatant;
        //the left combatant starts
        this.turnIndex = 0;
        this.turns = new ArrayList<Turn>();
        this.winner = null;
        this.loser = null;
    }

    /**
     * Get the left combatant
     * @return the left combatant 
     */
    public Person getLeftCombatant() {
        return this.leftCombatant;
    }

    /**
     * Get the right combatant
     * @return the right combatant
     */
    public Person getRightCombatant() {
        return this.rightCombatant;
    }

    /**
     * Get the winner of the duel
     * @return the person who won duel
     */
    public Person getWinner() {
        return this.winner;
    }

    /**
     * Get the loser of the duel
     * @return the person who lost the duel
     */
    public Person getLoser() {
        return this.loser;
    }

    /**
     * Get the number of turns taken in this duel so far.
     * @return the number of turns taken, minimum 0
     */
    public int getTotalNumberTurnsTaken() {
        return this.turns.size();
    }

    /**
     * Get the current person who is attacking.
     * @return the person who is attacking
     */
    public Person getCurrentAttackingPerson() {
        if(turnIndex == 0) {
            return leftCombatant;
        } else {
            return rightCombatant;
        }
    }

    /**
     * Get the current person who is being attacked.
     * @return the person who is being attacked
     */
    public Person getCurrentRecipient() {
        if(turnIndex == 1) {
            return leftCombatant;
        } else {
            return rightCombatant;
        }
    }

    /**
     * Determine if the current attacking person is a user - part of the Hero class.
     * @return if the current attacking person is a user
     */
    public boolean isPlayerTurn() {
        return getCurrentAttackingPerson() instanceof Hero;
    }

    /**
     * Determine if all combatants are still alive.
     * @return if all combatants are alive
     */
    public boolean combatantsAreAllAlive() {
        return leftCombatant.isAlive() && rightCombatant.isAlive();
    }

    /**
     * Process a turn in the duel:
     * create a turn, process it, and add it to turn history
     * update turn index
     * if one combatant has died, update winner and loser
     * @param attack the attack for the attacker to attempt
     * @return string representation of the result of the turn
     */
    public String processTurn(Attack attack) {
        //process attack or restore
        Person attacker = getCurrentAttackingPerson();
        Person recipient = getCurrentRecipient();
        Turn curTurn = new Turn(attacker, attack, recipient);
        String actionString = curTurn.process();
        turns.add(curTurn);
        
        //change whose turn it is
        updateTurnIndex();

        //if anyone died from the attack
        //set winner and loser of duel
        if(!combatantsAreAllAlive()) {
            winner = attacker;
            loser = recipient;
            actionString = getResultString();
        }

        return actionString;
    }
    
    /**
     * Get random attack for the current attacking person, then process turn.
     * @return string representation of the result of the turn
     */
    public String processTurn() {
        Person attacker = getCurrentAttackingPerson();
        Attack attack = attacker.getRandomAvailableAttack();
        return processTurn(attack);
    }

    /**
     * Parse attackId, finding the attack that matches that in attacker, then process turn.
     * @param attackId the id of the attack of the current attacking person
     * @return string representation of the result of the turn
     */
    public String processTurn(int attackId) {
        Person attacker = getCurrentAttackingPerson();
        Attack attack = attacker.getAttackById(attackId);
        return processTurn(attack);
    }

    /**
     * Update the turn index to 0 or 1,
     * 0 is leftCombatant's turn
     * 1 is rightCombatant's turn
     */
    private void updateTurnIndex() {
        int tempIndex = turnIndex;
        tempIndex++;
        //if tempIndex hits 2, set it back to 0
        if(tempIndex % 2 == 0) {
            tempIndex = 0;
        }
        turnIndex = tempIndex;
    }

    /**
     * Get result of battle in string form.
     * @return result of battle represented in a string.
     */
    public String getResultString() {
        return winner.getName() + " defeated " + loser.getName() + " in " + getTotalNumberTurnsTaken() + " turns.";
    }

    /**
     * Make string to indicate whose turn it is.
     * @return string that indicates whose turn it is
     */
    public String getTurnAsString() {
        String string = getCurrentAttackingPerson().getName();

        //if there is a winner set, it is no one's turn
        if(this.winner != null) {
            string =  "";
        }

        return string;
    }

    //TO DO: implement feature to revert turns
    /**
     * Get the most recent completed turn in this duel
     * @return the last turn in this duel
     */
    public Turn getLastTurn() {
        if(this.turns.size() > 0) {
            return turns.get(turns.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Revert to previous turn:
     * all effects of most recent turn should be reversed
     * @return the reult string of the turn before the most recent turn
     */
    public String revertPreviousTurn() {
        String actionString = revertTurns(1);
        return actionString;
    }

    /**
     * Revert the given number of turns:
     * roll back the effects of the turn - adding health and energy back
     * delete them from turns
     * update turnIndex
     * Cannot revert more than the number of turns in this battle.
     * This is irreversible - once a turn is reversed it cannot be re-reversed.
     * @param numTurnsToRevert the number of turns to revert
     * @return the result string of the last turn
     */
    public String revertTurns(int numTurnsToRevert) {
        int numTurnsProcessed = turns.size();
        if(numTurnsToRevert > numTurnsProcessed) { throw new IllegalArgumentException("numTurnsToRevert greater than numTurns in this duel: " + numTurnsToRevert + " > " + numTurnsProcessed); }
        
        String lastAction = turns.get(numTurnsProcessed - 1).getResultString();
        //for the number of turns to revert
        //revert the turn
        //save the string of the last action
        //delete it from turns
        //update the turn index - can use this because it oscillates between 0 and 1
        for(int i = 1; i <= numTurnsToRevert; i++) {
            Turn curTurn = turns.get(numTurnsProcessed - i);
            lastAction = curTurn.revert();
            turns.remove(numTurnsProcessed - i);
            updateTurnIndex();
        }

        return lastAction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Duel)) {
            return false;
        }
        Duel duel = (Duel) o;
        return Objects.equals(leftCombatant, duel.leftCombatant) && Objects.equals(rightCombatant, duel.rightCombatant) && turnIndex == duel.turnIndex && Objects.equals(turns, duel.turns) && Objects.equals(winner, duel.winner) && Objects.equals(loser, duel.loser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftCombatant, rightCombatant, turnIndex, turns, winner, loser);
    }

    @Override
    public String toString() {
        return "Duel[" +
            " leftCombatant='" + getLeftCombatant().getName() + " h: " + getLeftCombatant().getHealth() + ", e: " + getLeftCombatant().getEnergy() + "'" +
            ", rightCombatant='" + getRightCombatant().getName() + " h: " + getRightCombatant().getHealth() + ", e: " + getRightCombatant().getEnergy() + "'" +
            ", turnIndex='" + this.turnIndex + "'" +
            ", lastTurn ='" + getLastTurn() +"'" +
            ", winner='" + getWinner() + "'" +
            ", loser='" + getLoser()+ "'" +
            "]";
    }

}