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

/** In this class we just changed the enter method so that it would return a string rather than print it out to the
 * console. This way we could take the string and present it as a message box to the user. */
public class Wall implements CaveSite, java.io.Serializable {

	private static final long serialVersionUID = 2321978816627905196L;

	// Changed to return string. This makes it so we can handle the string on the GUI side instead of using the console.
    public String enter(Player p) {
        return "Ouch! That hurts.";
    }

}

