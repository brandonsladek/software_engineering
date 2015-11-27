package esof322.a4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import BreezySwing.*;

// Brandon Sladek

public class NewOrLoadView extends GBFrame {
	
	private static final long serialVersionUID = 8329279878857412329L;
	
	JLabel welcomeLabel = addLabel("Please choose an option:", 1, 1, 3, 1);
	JButton newGameButton = addButton("New game", 2, 1, 1, 1);
	JButton loadGameButton = addButton("Load game", 3, 1, 1, 1);
	
	public void buttonClicked(JButton buttonObj) {
		
		if (buttonObj == newGameButton) {
			JFrame newGameView = new ChooseDifficultyView();
			newGameView.setSize(300, 200);
			newGameView.setVisible(true);
		}
		else if (buttonObj == loadGameButton) {
			// New up file selection view that then news up an AdventureGameView based on the chosen file
			@SuppressWarnings("unused")
			JFrame loadGameView = new LoadGameView();
		}
		
		this.dispose();
	}
	
	public static void main(String[] args) {
		JFrame newOrLoadView = new NewOrLoadView();
		newOrLoadView.setSize(300, 200);
		newOrLoadView.setVisible(true);
	}

}
