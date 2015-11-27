package esof322.a4;

import BreezySwing.*;
import javax.swing.*;

// Brandon Sladek

/**
 * This class was not a part of the original source code. We added this class so that we could have a new dialog box
 * get opened up when the user clicked the "Grab an item" button. We injected the AdventureGameModelFacade into the
 * constructor of GrabView so we could work with the same model that the AdventureGameView works with.
 */
public class GrabView extends GBFrame {

	private static final long serialVersionUID = 3855132358587821286L;
	
	AdventureGameModelFacade model;
	AdventureGameView view;
    JButton[] itemButtons;

    /** Note model-facade argument.*/
    public GrabView(AdventureGameModelFacade model, AdventureGameView view) {

        this.model = model;
        this.view = view;

        itemButtons = new JButton[model.getPlayer().getLoc().getRoomContents().length];
        createButtonList(itemButtons);

        viewArea.setText(model.getPlayer().getLoc().getContentsDesc());
    }


    JLabel welcomeLabel = addLabel("Which item would you like to grab?", 1, 1, 1, 1);
    JLabel viewLable = addLabel("The room has: ", 2, 1, 1, 1);
    JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);
    JButton cancelButton = addButton("Cancel", 7, 2, 1, 1);


    /**
     * We did not add a case to check if the "buttonObj == cancelButton" because all we would have done inside the check
     * is dispose of the dialog box, which is what we do at the end of buttonClicked, regardless of which button is
     * clicked,(since there's no reason to keep the dialog open after a button has been clicked).*/
    public void buttonClicked(JButton buttonObj) {

        for (int i = 0; i < itemButtons.length; i++) {
            if (buttonObj == itemButtons[i])
                model.grabItem(model.getPlayer().getLoc().getRoomContents()[i]);
        }

        view.displayCurrentInfo();
        this.dispose();
    }

    /**
     * This method was completely new as well. We made a JButton[] and looped through the array in this method so we
     * could initialize each JButton dynamically depending on the number of items in the room (the number of items that
     * could be grabbed from the room).
     */
    private void createButtonList(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = addButton("Item " + (i + 1), 6, (i + 1), 1, 1);
        }
    }

}
