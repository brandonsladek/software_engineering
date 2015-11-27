package esof322.a4;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import BreezySwing.*;

// Brandon Sladek

public class LoadGameView extends GBFrame {
	
	private static final long serialVersionUID = -5779166642099695934L;
	
	final JFileChooser fileChooser = new JFileChooser();
	AdventureGameModelFacade model;
	int returnValue;
	File file;
	
	JLabel welcomeLabel = addLabel("Choose a file", 1, 1, 2, 1);
	
	public LoadGameView() {
		returnValue = fileChooser.showOpenDialog(LoadGameView.this);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			file = fileChooser.getSelectedFile();
			model = new AdventureGameModelFacade(-1, file);
			
			JFrame view = new AdventureGameView(model);
			view.setSize(800, 600);
			view.setVisible(true);
		}
	}
	
}