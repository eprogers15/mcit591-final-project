
import java.awt.event.*;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.*;

/**
 * @title DuelIllustrator
 * 
 * @description Handles window, intial visualization and updated visualizations of a Duel using a DuelScene.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class DuelIllustrator {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final Color backgroundColor = Color.DARK_GRAY;
    private final JFrame frame;
    private final Duel duel;
    private final DuelScene scene;

    /**
     * Constructor if there is no JFrame present.
     * @param duel the duel to display
     */
    public DuelIllustrator(Duel duel) {
        this(new JFrame(), duel);
    }

    /**
     * Constructor if there is a JFrame.
     * Wipe JFrame before populating with information from duel.
     * @param frame frame to populate
     * @param duel the duel to display
     */
    public DuelIllustrator(JFrame frame, Duel duel) {
        this.frame = frame;
        this.duel = duel;
        this.scene = initializeScene();

        frame.getContentPane().removeAll();
        frame.getContentPane().add(scene);
        frame.getContentPane().setBackground(backgroundColor);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setEnabled(true);
        frame.repaint();
        frame.revalidate();
    }

    /**
     * Create a new DuelScene with information from the leftCombatant and rightCombatant.
     * @return the scene to be shown
     */
    private DuelScene initializeScene() {
        Person leftCombatant = duel.getLeftCombatant();
        Person rightCombatant = duel.getRightCombatant();
        DuelScene dScene = new DuelScene(duel.getTurnAsString(), "Duel!", leftCombatant.getName(), leftCombatant.getIdleRightImages()[0], leftCombatant.getHealth(), leftCombatant.getEnergy(), rightCombatant.getName(), rightCombatant.getIdleLeftImages()[0], rightCombatant.getHealth(), rightCombatant.getEnergy(), duel.isPlayerTurn(), duel.getCurrentAttackingPerson().getAvailableActionsAsMap());

        return dScene; 
    }

    /**
     * Update the labels in the scene with the actionText and update all other information from the duel.
     * @param actionText the text to display the most recent action
     */
    public void updateLabels(String actionText) {
        updateLabels(duel.getTurnAsString(), actionText, duel.getLeftCombatant().getHealth(), duel.getLeftCombatant().getEnergy(), duel.getRightCombatant().getHealth(), duel.getRightCombatant().getEnergy(), duel.isPlayerTurn(), duel.getCurrentAttackingPerson().getAvailableActionsAsMap());
    }

    /**
     * Update the labels in the scene with the following values and reset buttons.
     * @param turnText the text to display whose turn it is
     * @param actionText the text to display the most recent action
     * @param leftCombatantHealth the health of the left combatant
     * @param leftCombatantEnergy the energy of the left combatant
     * @param rightCombatantHealth the health of the right combatant
     * @param rightCombatantEnergy the energy of the right combatant
     * @param displayUserInputButtons if the user input buttons should be displayed
     * @param actionIdToDescription the unique ids mapped to the descriptions for the user input buttons of the combatant
     */
    public void updateLabels(String turnText, String actionText, int leftCombatantHealth, int leftCombatantEnergy, int rightCombatantHealth, int rightCombatantEnergy, boolean isCombatantTurn, HashMap<Integer, String> actionIdToString) {
        scene.updateLabels(turnText, actionText, leftCombatantHealth, leftCombatantEnergy, rightCombatantHealth, rightCombatantEnergy, isCombatantTurn, actionIdToString);
    }

    /**
     * Get the user input buttons from the scene.
     * @return the current user input buttons in the scene
     */
    public JButton[] getUserInputButtons() {
        return scene.getUserInputButtons();
    }

    /**
     * Make the frame visible.
     */
    public void showFrame() {
        frame.setVisible(true);
    }

    /**
     * Close the frame.
     */
    public void removeFrame() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public String toString() {
        return "{" +
            " frame='" + frame + "'" +
            ", duel='" + duel + "'" +
            ", scene='" + scene + "'" +
            "}";
    }

}