package esof322.a4;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
**/

// Brandon Sladek

public class Player implements java.io.Serializable {

  private static final long serialVersionUID = 1796528696792700742L;

  private Room myLoc;
  private Item[] myThings = new Item[2];
  private int itemCount = 0;
  private int numBatteries = 0;
  private boolean hasWarpDrive = false;
  private boolean hasFlashlight = false;

  public void setRoom(Room r){
   myLoc = r;
   }

  public String look() {
   return myLoc.getDesc();
   }

  public String go(int direction) {
      return myLoc.exit(direction,this);
  }

  public void pickUp(Item i) {

	if (i.getClass() == Flashlight.class) {
		if (numBatteries == 1) {
			itemCount = 0;
			myThings[itemCount] = i;
			itemCount++;
			myLoc.removeItem(i);
			hasFlashlight = true;
		}
	}
	else if (itemCount < 2) {
        myThings[itemCount] = i;
        itemCount++;
        myLoc.removeItem(i);
        
        if (i.getClass() == Battery.class) {
        	numBatteries++;
        }
        
        if (i.getClass() == WarpDrive.class) {
        	hasWarpDrive = true;
        }
      }
  }

  public boolean haveItem(Item itemToFind) {

     for (int n = 0; n < itemCount ; n++) {
         if (myThings[n] == itemToFind) {
             return true;
         }
     }
      return false;
  }

  public void drop(int itemNum) {

   if (itemNum > 0 & itemNum <= itemCount) {

      switch(itemNum) {

        case 1: { 
        	if (myThings[0].getClass() == Battery.class) {
        		numBatteries--;
        	}
        	else if (myThings[0].getClass() == WarpDrive.class) {
        		hasWarpDrive = false;
        	}
        	else if (myThings[0].getClass() == Flashlight.class) {
        		hasFlashlight = false;
        	}
        	myLoc.addItem(myThings[0]);
	        myThings[0]=myThings[1];
	        myThings[1] = null;
	        itemCount--; 
	        break;
	      }
        case 2: { 
        	if (myThings[1].getClass() == Battery.class) {
        		numBatteries--;
        	}
        	else if (myThings[1].getClass() == WarpDrive.class) {
        		hasWarpDrive = false;
        	}
        	else if (myThings[1].getClass() == Flashlight.class) {
        		hasFlashlight = false;
        	}
        	myLoc.addItem(myThings[1]);
        	myThings[1] = null;
		    itemCount--;
		    break;
	      } 
      }
   }
  }
  
    // Getter and setter for player's Room location
  public void setLoc(Room r){myLoc = r;}
  public Room getLoc() {return myLoc;}

  public String showMyThings() {
	   String outString = "";
	
	   for (int n = 0; n < itemCount ; n++) {
	       outString = outString + Integer.toString(n + 1) + ": " + myThings[n].getDesc() + " ";
	   }
	
	   return outString;
  }

  public boolean handsFull(){return itemCount==2;}
  public boolean handsEmpty(){return itemCount==0;}
  public int numItemsCarried(){return itemCount;}
  public int getNumBatteries() { return numBatteries; }
  
  public void setNumBatteries(int numBats) {
	  numBatteries = numBats;
  }
  
  public boolean hasWarpDrive() {
	  return hasWarpDrive;
  }
  
  public boolean hasFlashlight() {
	  return hasFlashlight;
  }
  
  public Item[] getItems() {
	  return myThings;
  }
  
}

