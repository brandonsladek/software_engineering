package esof322.a4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import BreezySwing.*;

// Brandon Sladek

public class AdventureGameView extends GBFrame {

	private static final long serialVersionUID = 30741850863908500L;

// Window objects --------------------------------------
   JLabel welcomeLabel = addLabel("Welcome to the Adventure Game (inspired by an old game called the Colossal Cave " +
                "Adventure). Java implementation Copyright (c) 1999-2012 by James M. Bieman",1,1,5,1);
   
   JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
   JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);

   JLabel carryingLable = addLabel("You are carrying: ", 6, 1, 1, 1);
   JTextArea carryingArea = addTextArea("Nothing", 7, 1, 4, 3);

   JLabel separator1 = addLabel("-----------------------------------------------------------------",10,1,4,1);

   JLabel choiceLabel = addLabel("Choose a direction, pick-up, or drop an item", 11, 1, 5, 1);

   JButton grabButton = addButton("Grab an item", 12, 5, 1, 1);
   JButton dropButton = addButton("Drop an item", 13, 5, 1, 1);
   JButton flashlightButton = addButton("Use Flashlight", 14, 5, 1, 1);
   JButton warpDriveButton = addButton("Use Warp Drive", 15, 5, 1, 1);
   JButton saveButton = addButton("Save", 16, 5, 1, 1);
   
   JButton northButton = addButton("North",12,2,1,1);
   JButton southButton = addButton("South",14,2,1,1);
   JButton eastButton = addButton("East",13,3,1,1);
   JButton westButton = addButton("West",13,1,1,1);
   JButton upButton = addButton("Up",12,3,1,1);
   JButton downButton = addButton("Down",14,3,1,1);
   
   //Added JFrame message box in order to keep user duly informed
   private JFrame messageBox = new JFrame();
   private MessageBox myBox;

   private AdventureGameModelFacade model;
   
   // Constructor-----------------------------------------------
   public AdventureGameView(AdventureGameModelFacade model) {

      setTitle ("Adventure Game");
      
      this.model = model;
      
      viewArea.setEditable (true);
      carryingArea.setEditable (true);
      
      displayCurrentInfo();
   }

    /** If buttonObj matches one of the expected button objects, "model.go<direction>" is fired off and some other logic
     * checks to see if the player ran into a wall and fires off a MessageBox dialog to alert the user if they did so.*/
   public void buttonClicked (JButton buttonObj) {

       String isWall;
       
      //Implemented pop up dialog in the event that a user happened upon a wall

      if (buttonObj == upButton) {
          isWall = model.goUp();
          checkIsWall(isWall);
      }
      else if (buttonObj == downButton) {
          isWall = model.goDown();
          checkIsWall(isWall);
      }
      else if (buttonObj == northButton) {
          isWall = model.goNorth();
          checkIsWall(isWall);
      }
      else if (buttonObj == southButton) {
          isWall = model.goSouth();
          checkIsWall(isWall);
      }
      else if (buttonObj == eastButton) {
          isWall = model.goEast();
          checkIsWall(isWall);
      }
      else if (buttonObj == westButton) {
          isWall = model.goWest();
          checkIsWall(isWall);
      }
      else if (buttonObj == grabButton) {
          grab();
      }
      else if (buttonObj == dropButton) {
          drop();
      }
      else if (buttonObj == saveButton) {
    	  save();
      }
      else if (buttonObj == flashlightButton) {
    	  useFlashlight();
      }
      else if (buttonObj == warpDriveButton) {
    	  useWarpDrive();
      }

      displayCurrentInfo();
  }

   // Private methods-------------------------------------------

   public void displayCurrentInfo() {

	 viewArea.setText(model.getView());
	 carryingArea.setText(model.getItems());

	 }

    /** This methods takes in a String (returned from "AdventureGameModelFacade.go<direction>()" that is null if
     * the move was valid, and a non-null String when the move results in the Player running into a wall. This method
     * actually creates the MessageBox object that gets displayed to the user if the move results in running into a
     * wall. */
    private void checkIsWall(String isWall) {

        if (!isWall.equals("")) {
            myBox = new MessageBox(messageBox, isWall);
            myBox.setVisible(true);
        }
    }

    // Implemented grab functionality and dialog box
    /** This method first checks whether the player can actually pick something up (they don't already have two items),
     * and then creates a GrabView dialog box to let the user pick an item to grab (potentially out of a number of
     * items).*/
   private void grab() {

       if (model.getPlayer().handsFull()) {
           myBox = new MessageBox(messageBox, "Your hands are full, you cannot pick up anything!");
           myBox.setVisible(true);
       }
       else if (model.getPlayer().getLoc().isDark()) {
    	   myBox = new MessageBox(messageBox, "You can't grab any items in a dark room!");
           myBox.setVisible(true);
       }
       else if (model.getPlayer().getLoc().roomEmpty()) {
           myBox = new MessageBox(messageBox, "There are no items in this room!");
           myBox.setVisible(true);
       }
       else {
           /** See GrabView class implementation.*/
           JFrame grabView = new GrabView(model, this);
           grabView.setSize(500, 300);
           grabView.setVisible(true);
       }

       displayCurrentInfo();
   }

    // Implemented drop functionality and dialog box
    /** This method first checks to see if the player actually has something to drop, and if so create a DropView
     * dialog box to let the user pick an item to drop (potentially out of at most two items). It also news up
     * MessageBox if the user has nothing to drop. */
    private void drop() {

        // Check if empty
        if (!model.getPlayer().handsEmpty()) {
            /** See DropView class implementation.*/
            JFrame dropView = new DropView(model, this);
            dropView.setSize(500, 300);
            dropView.setVisible(true);
        }
        // If hands are empty, alert user that they may not pick up anything else
        else {
            myBox = new MessageBox(messageBox, "Your hands are empty, you do not have anything to drop!");
            myBox.setVisible(true);
        }

        displayCurrentInfo();
   }
    
    private void useFlashlight() {
    	
    	if (model.getPlayer().hasFlashlight()) {
    		model.useFlashlightToSee();
    	} else {
    		myBox = new MessageBox(messageBox, "You do not have a flashlight to use!");
            myBox.setVisible(true);
    	}
    }
    
    private void useWarpDrive() {
    	
    	if (model.getPlayer().hasWarpDrive()) {
    		JFrame warpDriveView = new WarpDriveView(model, this);
    		warpDriveView.setSize(500, 500);
    		warpDriveView.setVisible(true);
    	} else {
    		myBox = new MessageBox(messageBox, "You do not have a WarpDrive to use!");
            myBox.setVisible(true);
    	}
    	
    	displayCurrentInfo();
    }
    
    private void save() {
    	
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Specify a file to save");
    	
    	int userSelection = fileChooser.showSaveDialog(AdventureGameView.this);
    	
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    		
    		File fileToSave = fileChooser.getSelectedFile();
    		
    		try {
				FileOutputStream fileOut = new FileOutputStream(fileToSave.getAbsolutePath());
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(model.getCaveConfigurationForSave());
				
				myBox = new MessageBox(messageBox, "Game saved to " + fileToSave.getAbsolutePath());
	            myBox.setVisible(true);
	            
				out.close();
				fileOut.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}    		
    	}
    }

}
