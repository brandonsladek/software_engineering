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

// interface CaveSite
public interface CaveSite {
    //changed to return string. This makes it so we can handle the string on the GUI side instead of using console.
    String enter(Player p);

}

