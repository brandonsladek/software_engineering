package esof322.a4;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import BreezySwing.MessageBox;

// Brandon Sladek

public class AdventureGameModelFacade implements java.io.Serializable {

	private static final long serialVersionUID = 5439346412383013036L;
	
    private Player thePlayer;
    private AbstractCaveFactory factory;
    private Room[] rooms;
    private CaveConfiguration caveConfiguration = null;
    
    private JFrame messageBox = new JFrame();
    private MessageBox myBox;

  public AdventureGameModelFacade(int level, File file) {

      thePlayer = new Player();
      
      if (file != null) {
    	  caveConfiguration = loadCaveConfigurationFromSavedFile(file);
      }
      
      this.factory = determineRequiredFactoryType(level);
      
      setUpCaveAndPutPlayerInIt();
  }
  
  private AbstractCaveFactory determineRequiredFactoryType(int level) {
	  
	  AbstractCaveFactory factory;
	  
	  switch (level) {
	  	case -1:
	  		factory = new LoadGameCaveFactory(AdventureGameModelFacade.this);
	  		break;
		case 0:
			factory = new LevelZeroCaveFactory();
			break;
		case 1:
			factory = new LevelOneCaveFactory();
			break;
		default:
			factory = null;
			break;
	}
	return factory;
  }
  
  public CaveConfiguration getCaveConfigurationForSave() {
	  
	  CaveConfiguration caveConfiguration = new CaveConfiguration();
	  
	  caveConfiguration.setRooms(rooms);
	  caveConfiguration.setPlayerLoc(thePlayer.getLoc());
	  caveConfiguration.setPlayerItems(thePlayer.getItems());
	  caveConfiguration.setPlayerNumBatteries(thePlayer.getNumBatteries());
	  
	  return caveConfiguration;
  }
  
  public CaveConfiguration getCaveConfigurationFromLoad() {
	  return caveConfiguration;
  }
  
  private CaveConfiguration loadCaveConfigurationFromSavedFile(File file) {
	  
	  CaveConfiguration caveConfiguration = null;
	  
	  try {
		FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		caveConfiguration = (CaveConfiguration) objectIn.readObject();
		
		objectIn.close();
		fileIn.close();
		
		return caveConfiguration;
		
	} catch (Exception e) {
		myBox = new MessageBox(messageBox, "This is an invalid file! Please try again.");
		myBox.setVisible(true);
		e.printStackTrace();
	}
	 return null;
  }
  
  private void setUpDefaultCave() {
	  
	  Room playerLoc = caveConfiguration.getPlayerLoc();
	  Item[] playerItems = caveConfiguration.getPlayerItems();
	  int playerNumBatteries = caveConfiguration.getPlayerNumBatteries();
	  
	  thePlayer.setLoc(playerLoc);
	  thePlayer.setNumBatteries(playerNumBatteries);
	  
	  if (playerItems[0] != null) {
		  thePlayer.pickUp(playerItems[0]);
	  }
	  
	  if (playerItems[1] != null) {
		  thePlayer.pickUp(playerItems[1]);
	  }
  }
  
  private void setUpCaveAndPutPlayerInIt() {
	  
	  AbstractCave caveObject = factory.createCaveObject();
	  rooms = caveObject.createAdventure();
	  
	  if (caveObject.getClass() == LoadGameCave.class) {
		  setUpDefaultCave();
	  } else {
		  Room startRm = rooms[0];
		  thePlayer.setRoom(startRm);
	  }
  }
  
  public int getNumRooms() {
	  return rooms.length;
  }
  
  public void putPlayerInRoom(int i) {
	  thePlayer.setLoc(rooms[i]);
  }
  
    /** Direction integers:
     *
     * Up = 4
     * Down = 5
     * North = 0
     * South = 1
     * East = 2
     * West = 3
     *
     * */
  public String goUp() {
      return thePlayer.go(4);
  }

  public String goDown() {
      return thePlayer.go(5);
  }

  public String goNorth() {
      return thePlayer.go(0);
  }
      
  public String goSouth() {
      return thePlayer.go(1);
  }

  public String goEast() {
      return thePlayer.go(2);
  }
      
  public String goWest() {
      return thePlayer.go(3);
  }

  public String getView() {
     return thePlayer.getLoc().getDesc();
  }

  public String getItems() {
     return thePlayer.showMyThings();
  }

  public void grabItem(Item item) { 
	  
	  if (item.getClass() == Flashlight.class) {
		  if (thePlayer.getNumBatteries() == 1) {
			  thePlayer.pickUp(item);
		  } else {
			  myBox = new MessageBox(messageBox, "You must have a battery to pick up a flashlight!");
			  myBox.setVisible(true);
		  }
	  } else {
		  thePlayer.pickUp(item); 
	  }
  }

  public void dropItem(int item) {
        thePlayer.drop(item);
    }

  public void useFlashlightToSee() {
	  	thePlayer.getLoc().setDark(false);
  	}
  
  public Player getPlayer() {
	  return thePlayer;
  }
	
}
