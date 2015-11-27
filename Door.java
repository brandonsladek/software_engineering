package esof322.a4;

/**
 * Adventure Game  Program Code
 * Copyright (c) 1999 James M. Bieman
 * <p/>
 * To compile: javac AdventureGame.java
 * To run:     java AdventureGame
 * <p/>
 * The main routine is AdventureGame.main
 **/

// Brandon Sladek

// class Door
public class Door implements CaveSite, java.io.Serializable {

	private static final long serialVersionUID = 6449090881466680241L;

	/**
     * In this implementation doors are always locked.
     * A player must have the correct key to get through
     * a door.  Doors automatically lock after a player
     * passes through.
     */

    private Key myKey;

    /**
     * The door's location.
     */
    private CaveSite outSite;
    private CaveSite inSite;

    /**
     * We can construct a door at the site.
     */
    Door(CaveSite out, CaveSite in, Key k) {

        outSite = out;
        inSite = in;
        myKey = k;
    }
    
    public void setKey(Key k) {
    	myKey = k;
    }

    /**
     * A player will need the correct key to enter.
     */

    //changed to return string. This makes it so we can handle the string on the GUI side instead of using console.
    public String enter(Player p) {
        String toReturn;

        if (p.haveItem(myKey)) {

            toReturn = "Your key works! The door creaks open, and slams behind you after you pass through.";

            if (p.getLoc() == outSite) {
                toReturn += inSite.enter(p);

            } else if (p.getLoc() == inSite) {
                toReturn += outSite.enter(p);
            }
        } else {
            return "You don't have the key for this door! Sorry.";
        }

        return toReturn;
    }

}

