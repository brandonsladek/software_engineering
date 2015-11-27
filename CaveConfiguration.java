package esof322.a4;

// Brandon Sladek

public class CaveConfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 5022718727509214166L;
	
	private Room[] rooms = new Room[12];
	private Room playerLoc;
	private Item[] playerItems = new Item[2];
	private int playerNumBatteries;
	
	public CaveConfiguration() {}
	
	public Room[] getRooms() {
		return rooms;
	}
	
	public void setRooms(Room[] roomArray) {
		rooms = roomArray;
	}
	
	public Room getPlayerLoc() {
		return playerLoc;
	}
	
	public void setPlayerLoc(Room loc) {
		playerLoc = loc;
	}
	
	public Item[] getPlayerItems() {
		return playerItems;
	}
	
	public void setPlayerItems(Item[] items) {
		playerItems = items;
	}
	
	public int getPlayerNumBatteries() {
		return playerNumBatteries;
	}
	
	public void setPlayerNumBatteries(int numBats) {
		playerNumBatteries = numBats;
	}
	
}
