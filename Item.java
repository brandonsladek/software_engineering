package esof322.a4;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
**/

// Brandon Sladek

/** We did not make any changes to this class besides adding the unnecessary constructor. */
public class Item implements java.io.Serializable {

	private static final long serialVersionUID = 2449870011892653357L;
	
	private String description;

	public Item() {}

	public void setDesc(String d){
		description = d;
	}

	public String getDesc(){
	    return description;
	}

}

