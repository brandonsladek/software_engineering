package esof322.a4;

import javax.swing.JButton;
import javax.swing.JLabel;
import BreezySwing.*;

// Brandon Sladek

public class WarpDriveView extends GBFrame {

	private static final long serialVersionUID = -5302442983322403868L;
	
	private AdventureGameModelFacade model;
	private AdventureGameView view;
    private JButton[] itemButtons;
    
    JLabel welcomeLabel = addLabel("Choose a room to teleport to:", 1, 1, 1, 1);
    
    public WarpDriveView(AdventureGameModelFacade model, AdventureGameView view) {
    	
    	this.model = model;
    	this.view = view;

        itemButtons = new JButton[model.getNumRooms()];
        createButtonList(itemButtons);
    }
    
    public void buttonClicked(JButton buttonObj) {
        for (int i = 0; i < itemButtons.length; i++) {
            if (buttonObj == itemButtons[i])
                model.putPlayerInRoom(i);
        }

        view.displayCurrentInfo();
        this.dispose();
    }

    private void createButtonList(JButton[] buttons) {
    	
    	buttons[0] = addButton("Outside", 1, 1, 1, 1);
    	
        for (int i = 1; i < buttons.length; i++) {
            buttons[i] = addButton("Room " + i, (i+1), 1, 1, 1);
        }
    }
}
