package esof322.a4;

/**
 * Adventure Game  Program Code
 * Copyright (c) 1999 James M. Bieman
 * <p/>
 * To compile: javac AdventureGame.java
 * To run:     java AdventureGame
 * <p/>
 * The main routine is AdventureGame.main
 * <p/>
 * Update August 2010: refactored Vector contents into ArrayList<Item> contents.
 * This gets rid of the use of obsolete Vector and makes it type safe.
 **/

// Paul Burton, Robert Putnam, Brandon Sladek and Samuel Kern are responsible for the excellent GUI representation
// of this game once only played via text representation

import java.util.ArrayList;
import java.util.ListIterator;

// Brandon Sladek

public class Room implements CaveSite, java.io.Serializable {

	private static final long serialVersionUID = 4877373452359400189L;
	
	private String description;
    private boolean isDark = false;
    
    private CaveSite[] side = new CaveSite[6];

    private ArrayList<Item> contents = new ArrayList<Item>();

    public Room() {
        side[0] = new Wall();
        side[1] = new Wall();
        side[2] = new Wall();
        side[3] = new Wall();
        side[4] = new Wall();
        side[5] = new Wall();
    }

  public void setDesc(String d) {
    description = d;
    }

  public void setSide(int direction, CaveSite m) {
   side[direction] = m;
   }

  public void addItem(Item theItem) {
   contents.add(theItem); 
   }

  public void removeItem(Item theItem) {
   contents.remove(theItem);
   }

  public boolean roomEmpty() {
	 return contents.isEmpty();
  }

   public Item[] getRoomContents() {
       Item[] contentsArray = new Item[contents.size()];
       contentsArray = contents.toArray(contentsArray);
       return contentsArray;
   }

   //changed to return string. This makes it so we can handle the string on the GUI side instead of using console.
   public String enter(Player p) {
       p.setLoc(this);
       return "";
   }

   public String exit(int direction, Player p) {
       return side[direction].enter(p);
   }

   public String getDesc() {

	   if (!isDark) {
	       ListIterator<Item> roomContents = contents.listIterator();
	       String contentString = "";
	
	       while (roomContents.hasNext()) {
	           contentString = contentString + (roomContents.next()).getDesc() + "\n";
	       }
	
	       return description + '\n' + '\n' + "Room Contents: " + contentString + '\n';
	   } else {
		   return "It's too dark in here to see! You need a flashlight.\n";
	   }
   }

   /** We added this method just so we could get the contents of a room in string format without also having the
    * description of the room be part of the string (notice how the "getDesc()" method above does almost the exact
    * same thing except it has the room description as part of the string. */
   public String getContentsDesc() {
	   
	   if (!isDark) {
	       ListIterator<Item> roomContents = contents.listIterator();
	       String contentString = "";
	
	       while(roomContents.hasNext()) {
	           contentString = contentString + (roomContents.next()).getDesc() + "\n";
	       }
	       return contentString;
	   } else {
		   return "";
	   }
   }
   
   public boolean isDark() {
	   return isDark;
   }
   
   public void setDark(boolean dark) {
	   isDark = dark;
   }
   
}

