package esof322.a4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import BreezySwing.*;

// Brandon Sladek

public class ChooseDifficultyView extends GBFrame {

	private static final long serialVersionUID = 9112987195090654043L;
	
	static AdventureGameModelFacade model;
	int level = -1;
	
	public ChooseDifficultyView() {}
	
	JLabel welcomeLabel = addLabel("Please choose a level:",1,1,5,1);
	JButton levelZeroButton = addButton("0 (Easy)", 3, 1, 3, 1);
	JButton levelOneButton = addButton("1 (Not Easy)", 4, 1, 3, 1);
	
	public void buttonClicked(JButton buttonObj) {
		
		if (level == -1) {
			if (buttonObj == levelZeroButton) {
				level = 0;
			}
			else if (buttonObj == levelOneButton) {
				level = 1;
			}
			
			model = new AdventureGameModelFacade(level, null);
			
			JFrame view = new AdventureGameView(model);
			view.setSize(800, 600);
			view.setVisible(true);
			
			this.dispose();
		}
	}

}
