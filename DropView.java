package esof322.a4;

import BreezySwing.*;
import javax.swing.*;

// Brandon Sladek

public class DropView extends GBFrame {

	private static final long serialVersionUID = 3860017528308232651L;
	
	AdventureGameModelFacade model;
	AdventureGameView view;
    JButton[] itemButtons;

    /** Note model-facade argument.*/
    public DropView(AdventureGameModelFacade model, AdventureGameView view) {

        this.model = model;
        this.view = view;

        itemButtons = new JButton[model.getPlayer().numItemsCarried()];
        createButtonList(itemButtons);

        viewArea.setText(model.getPlayer().showMyThings());
    }

    JLabel welcomeLabel = addLabel("Which item would you like to drop?",1,1,5,1);
    JLabel viewLable = addLabel ("Items: ",2,1,1,1);
    JTextArea viewArea = addTextArea("Start",3,1,4,3);
    JButton cancelButton = addButton("Cancel",7,1,1,1);

    /** Again, this method does not have a specific check for if "buttonObj == cancelButton" since all we would want
     * to do with the cancelButton is dispose of the dialog, which is what we do at the end of the buttonClicked method
     * regardless of which button was clicked. */
    public void buttonClicked(JButton buttonObj) {
        for (int i = 0; i < itemButtons.length; i++) {
            if (buttonObj == itemButtons[i])
                model.dropItem(i+1);
        }

        view.displayCurrentInfo();
        this.dispose();
    }

    /** Again, we implemented this method that takes in a JButton[] so that we could dynamically create buttons on our
     * Drop dialog since we wouldn't necessarily always have the same number of items to consider dropping. */
    private void createButtonList(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = addButton("Item " + (i+1),6,(i+1),1,1);
        }
    }


}
