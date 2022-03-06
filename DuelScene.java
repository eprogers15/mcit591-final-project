import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

/**
 * @title DuelScene
 * 
 * @description Creates scene to display duel information - including person images, statuses, turns, and action buttons.
 * 
 * @author Eric Shapiro
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class DuelScene extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Font TITLE_FONT = new Font("Tibetan Machine Uni", Font.PLAIN, 26);
    private static final Font SUBTITLE_FONT = new Font("Tibetan Machine Uni", Font.PLAIN, 18);
    private static final Font ATTACK_FONT = new Font("Times New Roman", Font.PLAIN, 18);
    private static final Color BG_COLOR_PRIMARY = Color.DARK_GRAY;
    private static final Color TEXT_COLOR_PRIMARY = Color.WHITE;
    private static final int TEXT_HALIGNMENT_PRIMARY = SwingConstants.CENTER;
    private static final int TEXT_VALIGNMENT_PRIMARY = SwingConstants.CENTER;
    private static final int IMAGE_HEIGHT = 90;
    private static final int TEXT_FOR_IMAGE_HALIGNMENT = SwingConstants.CENTER;
    private static final int TEXT_FOR_IMAGE_VALIGNMENT = SwingConstants.TOP;
    private static final boolean BUTTON_IS_FOCUSED = false;

    private JPanel statusPanel, leftCombatantPanel, leftCombatantInfoPanel, userInputPanel, rightCombatantPanel, rightCombatantInfoPanel;
    private JLabel turnLabel, actionLabel;
    private JLabel leftCombatantImageLabel;
    private JLabel leftCombatantHealthLabel, leftCombatantEnergyLabel;
    private JButton[] userInputButtons;
    private JLabel rightCombatantImageLabel;
    private JLabel rightCombatantHealthLabel, rightCombatantEnergyLabel;

    private final GridBagLayout layoutManager = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final double[] statusPanelConstraintSpecifications = new double[] {1.0, 0.0, 1.0, 0.5, 0.5}; //row 1, col 2
    private final double[] leftCombatantPanelConstraintSpecifications = new double[]  {0.0, 1.0, 1, 0.5, 0.5}; //row 2, col1
    private final double[] rightCombatantPanelConstraintSpecifications = new double[] {2.0, 1.0, 1.0, 0.5, 0.5}; //row 2, col 3
    private final double[] leftCombatantInfoPanelConstraintSpecifications = new double[] {0, 2, 1, 0.5, 0.5}; //row 3, col 1
    private final double[]  userInputPanelConstraintSpecifications = new double[] {1.0, 2.0, 1.0, 1.0, 1.0}; //row 3, col 2
    private final double[] rightCombatantInfoPanelConstraintSpecifications = new double[] {2.0, 2.0, 1.0, 0.5, 0.5}; //row 3, col 3
    
    /**
     * Constructor with placeholders.
     */
    public DuelScene() {
        this("PLAYER NAME", "ACTION UPDATE GOES HERE", "L PLAYER NAME", "L PLAYER IMAGE", 0, 0, "R PLAYER NAME", "R PLAYER IMAGE", 0, 0, false, new HashMap<Integer, String>());
    }

    /**
     * Constructor to set initial scene.
     * @param turnText the text to display whose turn it is
     * @param actionText the text to display the most recent action
     * @param leftCombatantName the name of the left combatant
     * @param leftCombatantImageFilepath the filepath of image of the left combatant
     * @param leftCombatantHealth the health of the left combatant
     * @param leftCombatantEnergy the energy of the left combatant
     * @param rightCombatantName the name of the right combatant
     * @param rightCombatantImageFilepath the filepath of image of the right combatant
     * @param rightCombatantHealth the health of the right combatant
     * @param rightCombatantEnergy the energy of the right combatant
     * @param displayUserInputButtons if the user input buttons should be displayed
     * @param actionIdToDescription the unique ids mapped to the descriptions for the user input buttons of the first combatant
     */
    public DuelScene(String turnText, String actionText, String leftCombatantName, String leftCombatantImageFilepath, int leftCombatantHealth, int leftCombatantEnergy, String rightCombatantName, String rightCombatantImageFilepath, int rightCombatantHealth, int rightCombatantEnergy, boolean displayUserInputButtons, HashMap<Integer, String> buttonIdToText) {
        //create all panels and labels and add labels to panels
        initializePanels(turnText, actionText, leftCombatantName, leftCombatantImageFilepath, leftCombatantHealth, leftCombatantEnergy, rightCombatantName, rightCombatantImageFilepath, rightCombatantHealth, rightCombatantEnergy, displayUserInputButtons, buttonIdToText);

        //set color  
        setBackground(BG_COLOR_PRIMARY);

        //set grid bag layout for this scene
        setLayout(layoutManager);

        //add all panels to this DuelScene with layout constraints specific for each panel
        JPanel[] panelsToAdd = new JPanel[] {statusPanel, leftCombatantPanel, rightCombatantPanel, leftCombatantInfoPanel, userInputPanel, rightCombatantInfoPanel};
        double[][] constraintSpecifications = new double[][]{statusPanelConstraintSpecifications, leftCombatantPanelConstraintSpecifications, rightCombatantPanelConstraintSpecifications, leftCombatantInfoPanelConstraintSpecifications, userInputPanelConstraintSpecifications, rightCombatantInfoPanelConstraintSpecifications};
        populateSceneWithPanelsInGridBagLayout(panelsToAdd, constraints, constraintSpecifications);
    }

     /**
     * Intialize all panels with labels and buttons given paramter values.
     * @param turnText the text to display whose turn it is
     * @param actionText the text to display the most recent action
     * @param leftCombatantName the name of the left combatant
     * @param leftCombatantImageFilepath the filepath of image of the left combatant
     * @param leftCombatantHealth the health of the left combatant
     * @param leftCombatantEnergy the energy of the left combatant
     * @param rightCombatantName the name of the right combatant
     * @param rightCombatantImageFilepath the filepath of image of the right combatant
     * @param rightCombatantHealth the health of the right combatant
     * @param rightCombatantEnergy the energy of the right combatant
     * @param displayUserInputButtons if the user input buttons should be displayed
     * @param actionIdToDescription the unique ids mapped to the descriptions for the user input buttons of the first combatant
     */
    private void initializePanels(String turnText, String actionText, String leftCombatantName, String leftCombatantImageFilepath, int leftCombatantHealth, int leftCombatantEnergy, String rightCombatantName, String rightCombatantImageFilepath, int rightCombatantHealth, int rightCombatantEnergy, boolean displayUserInputButtons, HashMap<Integer, String> buttonIdToText) {
        //statusPanel contains current turn and previous action
        turnLabel = createLabel("TURN: " + turnText, TITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        actionLabel = createLabel(actionText, SUBTITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        JLabel[] tempStatusLabels = new JLabel[] {turnLabel, actionLabel};
        statusPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new GridLayout(tempStatusLabels.length,1)), tempStatusLabels);

        //leftCombatantPanel contains left combatant's image and name
        leftCombatantImageLabel = createLabel(leftCombatantImageFilepath, IMAGE_HEIGHT, leftCombatantName, SUBTITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_FOR_IMAGE_HALIGNMENT, TEXT_FOR_IMAGE_VALIGNMENT);
        JLabel[] tempLeftCombatantImageLabels = new JLabel[]{leftCombatantImageLabel};
        leftCombatantPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new BoxLayout(leftCombatantPanel, BoxLayout.Y_AXIS)), tempLeftCombatantImageLabels);

        //leftCombatantInfoPanel contains left combatant's health and energy
        leftCombatantHealthLabel = createLabel("Health: " + leftCombatantHealth, TITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        leftCombatantEnergyLabel = createLabel("Energy: " + leftCombatantEnergy, TITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        JLabel[] tempLeftCombatantInfoLabels = new JLabel[] {leftCombatantHealthLabel, leftCombatantEnergyLabel};
        leftCombatantInfoPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new GridLayout(tempLeftCombatantInfoLabels.length,1)), tempLeftCombatantInfoLabels);

        //userInputPanel contains buttons for user to select action
        userInputButtons = createButtonsWithVaryingTextAndUniformAppearance(buttonIdToText, ATTACK_FONT, TEXT_COLOR_PRIMARY, BG_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, BUTTON_IS_FOCUSED);
        enableUserInputButtons(displayUserInputButtons);
        userInputPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new GridLayout(userInputButtons.length, 1)), userInputButtons);

        //rightCombatantPanel contains right combatant's image and name
        rightCombatantImageLabel = createLabel(rightCombatantImageFilepath, IMAGE_HEIGHT, rightCombatantName, SUBTITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_FOR_IMAGE_HALIGNMENT, TEXT_FOR_IMAGE_VALIGNMENT);
        JLabel[] tempRightCombatantImageLabels = new JLabel[]{rightCombatantImageLabel};
        rightCombatantPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new BoxLayout(rightCombatantPanel, BoxLayout.Y_AXIS)), tempRightCombatantImageLabels);

        //rightCombatantInfoPanel contains right combatant's health and energy
        rightCombatantHealthLabel = createLabel("Health: " + rightCombatantHealth, TITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        rightCombatantEnergyLabel = createLabel("Energy: " + rightCombatantEnergy, TITLE_FONT, TEXT_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, TEXT_VALIGNMENT_PRIMARY);
        JLabel[] tempRightCombatantInfoLabels = new JLabel[] {rightCombatantHealthLabel, rightCombatantEnergyLabel};
        rightCombatantInfoPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new GridLayout(tempRightCombatantInfoLabels.length, 1)), tempRightCombatantInfoLabels) ;   
    }

    /**
     * Update the labels with new text and reset buttons if they are being displayed, otherwise just disable current buttons.
     * @param turnText the text to display whose turn it is
     * @param actionText the text to display the most recent action
     * @param leftCombatantHealth the health of the left combatant
     * @param leftCombatantEnergy the energy of the left combatant
     * @param rightCombatantHealth the health of the right combatant
     * @param rightCombatantEnergy the energy of the right combatant
     * @param displayUserInputButtons if the user input buttons should be displayed
     * @param actionIdToDescription the unique ids mapped to the descriptions for the user input buttons of the combatant
     */
    public void updateLabels(String turnText, String actionText, int leftCombatantHealth, int leftCombatantEnergy, int rightCombatantHealth, int rightCombatantEnergy, boolean displayUserInputButtons, HashMap<Integer, String> buttonIdToDescription) {
        //set turn text to indicate whose turn it is
        turnLabel.setText("TURN: " + turnText);

        //set action text so user knows what happened
        actionLabel.setText(actionText);

        //set health and energy for left combatant and right combatant
        leftCombatantHealthLabel.setText("Health: " + leftCombatantHealth);
        leftCombatantEnergyLabel.setText("Energy: " + leftCombatantEnergy);
        rightCombatantHealthLabel.setText("Health: " + rightCombatantHealth);
        rightCombatantEnergyLabel.setText("Energy: " + rightCombatantEnergy);

        //update buttons if they should be displayed
        if(displayUserInputButtons) {
            //old buttons are removed
            remove(userInputPanel);

            //create the new buttons based on actions provided
            userInputButtons = createButtonsWithVaryingTextAndUniformAppearance(buttonIdToDescription, ATTACK_FONT, TEXT_COLOR_PRIMARY, BG_COLOR_PRIMARY, TEXT_HALIGNMENT_PRIMARY, BUTTON_IS_FOCUSED);
            
            //populate panel with new buttons
            userInputPanel = populatePanelWithComponents(createPanel(BG_COLOR_PRIMARY, new GridLayout(userInputButtons.length,1)), userInputButtons);
            
            //populate scene with new panel
            populateSceneWithPanelsInGridBagLayout(new JPanel[]{userInputPanel}, constraints, new double[][]{userInputPanelConstraintSpecifications});
        }
        
        //enable or disable buttons
        enableUserInputButtons(displayUserInputButtons);
    }

     /**
    * Get the current user input buttons.
    * @return the userInputButtons currently set
    */
    public JButton[] getUserInputButtons() {
        return this.userInputButtons;
    }


    /**
     * Enable/disable the userInputButtons.
     * @param enable if the userInputButtons should be made visible
     */
    private void enableUserInputButtons(boolean enable) {
        //for each button enable only if should be enabled
        for(JButton button : userInputButtons ) {
            button.setEnabled(enable);
        }
    }

    /**
     * Create a JPanel with given bounds, backgroundColor and GridLayout
     * @param backgroundColor the backgroundColor of the new panel
     * @param layout the gridlayout of the new panel
     * @return a JPanel set with the parameter values
     */
    private JPanel createPanel(Color backgroundColor, LayoutManager layout) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);

        //if layout mgr is BoxLayout use a different setup
        if(layout.getClass().getName().equals("javax.swing.BoxLayout")) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        } else {
            panel.setLayout(layout);
        }

        return panel;
    }

    /**
     * Create a JLabel with given text, font and foreground color
     * @param text the text of the new label
     * @param font the font of the new label
     * @param textColor the foreground color of the new label
     * @param horizontalTextPosition the position of the label on the x-axis
     * @param verticalTextPosition the position of the label on the y-axis
     * @return a JLabel set with parameter values
     */
    private JLabel createLabel(String text, Font font, Color textColor, int horizontalTextPosition, int verticalTextPosition) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setFont(font);
        label.setForeground(textColor);
        label.setHorizontalAlignment(horizontalTextPosition);
        label.setVerticalAlignment(verticalTextPosition);
    
        return label;
    }

     /**
     * Create a JLabel with given image, scaled to the height, and subtitle text for the image, including font, color and text position of the subtitle.
     * @param filenameOfImage the filename of the image for the new label
     * @param heightOfImageToScaleTo the height that the image scales to
     * @param textForImage the text subtitle of the image
     * @param textFont the font of the text subtitle
     * @param textColor the color of the text subtitle
     * @param horizontalTextPosition the horizontal text position of the text subtitle in relation to the image
     * @param verticalTextPosition the vertical text position of the text subititle in relation to the image
     * @return a JLabel set with parameter values
     */
    private JLabel createLabel(String pathToImage, int heightOfImageToScaleTo, String textForImage, Font textFont, Color textColor, int horizontalTextPosition, int verticalTextPosition) {
        ImageIcon rawImage = new ImageIcon(pathToImage);
        ImageIcon scaledImage = new ImageIcon(scaleImage(rawImage.getImage(), -1, heightOfImageToScaleTo));
        JLabel label = new JLabel(textForImage, scaledImage, SwingConstants.CENTER);
        label.setFont(textFont);
        label.setForeground(textColor);
        label.setHorizontalTextPosition(horizontalTextPosition);
        label.setVerticalTextPosition(verticalTextPosition);

        return label;
    }

    /**
     * Create an array of buttons with text and action command customized for each button, and provided font, text color, background color, horizontal alignment, focus for all buttons.
     * @param buttonIdToText map of button id for action command to text in button for each button
     * @param font font for all buttons
     * @param textColor text color for all buttons
     * @param backgroundColor background color for all buttons
     * @param horizontalAlignment horizontal alignment of text for all buttons
     * @param focusPainted focus of text for all buttons
     * @return an array of JButtons set with unique text and action command plus paramter values
     */
    private JButton[] createButtonsWithVaryingTextAndUniformAppearance(HashMap<Integer, String> buttonIdToText, Font font, Color textColor, Color backgroundColor, int horizontalAlignment, boolean focusPainted) {
        JButton[] buttons = new JButton[buttonIdToText.size()];
        int i = 0;
        for(Integer id : buttonIdToText.keySet()) {
            String curText = buttonIdToText.get(id);
            buttons[i] = createButton(curText, font, textColor, backgroundColor, horizontalAlignment, focusPainted);
            buttons[i].setActionCommand("" + id);
            i++;
        }

        return buttons;
    }

    /**
     * Create a single button with given text, font, text color, background color, horizontal alignment and focus.
     * @param text text for the button
     * @param font font for the button
     * @param textColor text color for the button
     * @param backgroundColor backgorund color for the button
     * @param horizontalAlignment horizontal alignment for the button
     * @param focusPainted focus of text for the button
     * @return a JButton set with parameter values
     */
    private JButton createButton(String text, Font font, Color textColor, Color backgroundColor, int horizontalAlignment, boolean focusPainted) {
        JButton userButton = new JButton(text);
        userButton.setBackground(backgroundColor);
        userButton.setForeground(textColor);
        userButton.setFont(font);
        userButton.setHorizontalAlignment(horizontalAlignment);
        userButton.setFocusPainted(focusPainted);
        //added so that it works on Mac
        userButton.setBorderPainted(false);

        return userButton;
    }

    /**
     * Scale an image to a given width and height.
     * @param image the image to scale
     * @param w the width to scale to
     * @param h the height to scale to
     * @return the scaled image
     */
    private Image scaleImage(Image image, int w, int h) {
        return image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }

    /**
     * Add the given components in list to the panel in order.
     * @param panel the component to add to
     * @param labelsToAdd the components to add
     * @return
     */
    private JPanel populatePanelWithComponents(JPanel panel, JComponent[] componentsToAdd) {
        //for each component
        for(JComponent component : componentsToAdd) {
            panel.add(component);
        }
        return panel;
    }

    /**
     * Add the panels to this scene with given constraint specifications
     * @param panelsToAdd the panels to add to the scene
     * @param constraints the constraints for this scene
     * @param constraintSpecifications the constraint specifications corresponding each panel
     */
    private void populateSceneWithPanelsInGridBagLayout(JPanel[] panelsToAdd, GridBagConstraints constraints, double[][] constraintSpecifications) {
        for(int i = 0; i < panelsToAdd.length; i++) {
            JPanel curPanel = panelsToAdd[i];
            double[] curConstraintSpecifications = constraintSpecifications[i];
            this.add(curPanel, updateConstraints(constraints, curConstraintSpecifications[0], curConstraintSpecifications[1], curConstraintSpecifications[2], curConstraintSpecifications[3], curConstraintSpecifications[4]));
        }
    }

    /**
     * Update the constraints on a grid bag layout, including gridx, gridy, gridwidth, weightx, weighty.
     * @param c the contraints to update
     * @param gridx the new gridx
     * @param gridy the new gridy
     * @param gridwidth the new gridwidth
     * @param weightx the new weightx
     * @param weighty the new weighty
     * @return the updated contraints
     */
    private GridBagConstraints updateConstraints(GridBagConstraints c, double gridx, double gridy, double gridwidth, double weightx, double weighty) {
        c.gridx = (int) gridx;
        c.gridy = (int) gridy;
        c.gridwidth = (int) gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;

        return c;
    }

    @Override
    public String toString() {
        return "{" +
            " statusPanel='" + statusPanel + "'" +
            ", leftCombatantPanel='" + leftCombatantPanel + "'" +
            ", leftCombatantInfoPanel='" + leftCombatantInfoPanel + "'" +
            ", userInputPanel='" + userInputPanel + "'" +
            ", rightCombatantPanel='" + rightCombatantPanel + "'" +
            ", rightCombatantInfoPanel='" + rightCombatantInfoPanel + "'" +
            ", turnLabel='" + turnLabel + "'" +
            ", actionLabel='" + actionLabel + "'" +
            ", leftCombatantImageLabel='" + leftCombatantImageLabel + "'" +
            ", leftCombatantHealthLabel='" + leftCombatantHealthLabel + "'" +
            ", leftCombatantEnergyLabel='" + leftCombatantEnergyLabel + "'" +
            ", userInputButtons='" + userInputButtons + "'" +
            ", rightCombatantImageLabel='" + rightCombatantImageLabel + "'" +
            ", rightCombatantHealthLabel='" + rightCombatantHealthLabel + "'" +
            ", rightCombatantEnergyLabel='" + rightCombatantEnergyLabel + "'" +
            ", layoutManager='" + layoutManager + "'" +
            ", constraints='" + constraints + "'" +
            ", statusPanelConstraintSpecifications='" + statusPanelConstraintSpecifications + "'" +
            ", leftCombatantPanelConstraintSpecifications='" + leftCombatantPanelConstraintSpecifications + "'" +
            ", rightCombatantPanelConstraintSpecifications='" + rightCombatantPanelConstraintSpecifications + "'" +
            ", leftCombatantInfoPanelConstraintSpecifications='" + leftCombatantInfoPanelConstraintSpecifications + "'" +
            ", userInputPanelConstraintSpecifications='" + userInputPanelConstraintSpecifications + "'" +
            ", rightCombatantInfoPanelConstraintSpecifications='" + rightCombatantInfoPanelConstraintSpecifications + "'" +
            "}";
    }


}