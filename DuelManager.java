import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JButton;

/**
 * @title DuelManager
 * 
 * @description Handles duel gameplay - creating the duel, illustrator and timers - and attacks from human and non-human combatants in duel.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */


public class DuelManager implements Runnable {
    private Duel duel;
    private DuelIllustrator illustrator;
    private UserAttackHandler userAttackHandler;
    private Timer autoAttackTimer;
    private AutoAttackHandler autoAttackHandler;

    //slow enough that user can read actions of enemy
    //but fast enough that enemy turn moves smoothly
    private static final int timeItTakesForAutoAttack = 1500;

    /**
     * Constructor with two combatants.
     * @param leftCombatant the left combatant in this duel
     * @param rightcombatant the right combatant in this duel
     */
    public DuelManager(Person leftCombatant, Person rightCombatant) {
        //create duel to represent data
        duel = new Duel(leftCombatant, rightCombatant);  
       
        //create illustrator to visualize output
        //output will not be made visible until showFrame is called
        illustrator = new DuelIllustrator(duel);

        //create handler for input
        //listens to user click on buttons in output
        userAttackHandler = new UserAttackHandler();
        addListenerToButtons(userAttackHandler, illustrator.getUserInputButtons());

        //create handler and timer for automatic attack
        //allows ai attack to happen without user input at a rate that user can read
        autoAttackHandler = new AutoAttackHandler();
        autoAttackTimer = new Timer(timeItTakesForAutoAttack, autoAttackHandler);
    }

    /**
     * Get attack id from button, process turn, update output and ensure buttons in output have listeners.
     * Tied to when user clicks attackButton to select their attack.
     */
    public class UserAttackHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //update data
            int actionId = Integer.parseInt(event.getActionCommand());
            String actionString = duel.processTurn(actionId);

            //update output
            illustrator.updateLabels(actionString);

            //update input
            addListenerToButtons(userAttackHandler, illustrator.getUserInputButtons());  
        }
    }

    /**
     * Get attack randomly process turn, update output and ensure buttons in output have listeners.
     * Tied to when ai attacks.
     */
    public class AutoAttackHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //update data
            String actionString = duel.processTurn();

            //update output
            illustrator.updateLabels(actionString);

            //update input
            addListenerToButtons(userAttackHandler, illustrator.getUserInputButtons());
        }
    }

    /**
     * Add provided listener to the buttons.
     * @param listener the listener to add
     * @param buttons the buttons to have the listener
     */
    private void addListenerToButtons(ActionListener listener, JButton[] buttons) {
        //add action listeners to buttons to capture user interaction
        for(JButton button : buttons) {
            button.addActionListener(listener);
        }
    }

    /**
     * Run duel, asking for user input on attack when it's combatant's turn
     * and automatically processing attacks when it's ai's turn.
     * Deal with end when duel is over.
     */
    @Override
    public void run() {
        //show frame so it's visible
        illustrator.showFrame();

        //while Duel is being fought
        //if current Person is an ai - not a human player - start timer so that auto attack will be triggered
        //else stop the timer so that auto attack is shut off and user can choose attack
        while(duel.combatantsAreAllAlive()) {
            if(!duel.isPlayerTurn()) { //ai's turn
                autoAttackTimer.start();
            } else { //player's turn
                autoAttackTimer.stop();
            }
        }

        //stop timer so updates do not continue,
        //regardless of whose turn duel ended on
        autoAttackTimer.stop();

        //duel is over
        Person loser = duel.getLoser();
        Person winner = duel.getWinner();
        //if loser is an enemy and winner is a hero
        //add xp to the hero
        if(loser instanceof Enemy && winner instanceof Hero) { //ai died
            Enemy loserEnemy = (Enemy) loser;
            Hero winnerHero = (Hero) winner;
            winnerHero.addXp(loserEnemy.getStoredXp());
        }

        //close frame before finishing
        illustrator.removeFrame();
    }

    @Override
    public String toString() {
        return "{" +
            " duel='" + duel + "'" +
            ", illustrator='" + illustrator + "'" +
            ", userAttackHandler='" + userAttackHandler + "'" +
            ", autoAttackTimer='" + autoAttackTimer + "'" +
            ", autoAttackHandler='" + autoAttackHandler + "'" +
            "}";
    }


}