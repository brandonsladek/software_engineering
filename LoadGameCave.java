package esof322.a4;

// Brandon Sladek

public class LoadGameCave implements AbstractCave, java.io.Serializable {

	private static final long serialVersionUID = 7425071329652518445L;
	
	private AdventureGameModelFacade model;
	
	public LoadGameCave(AdventureGameModelFacade thisModel) {
		model = thisModel;
	}
	
	// This method sets up the cave according to the deserialized CaveConfiguration object that was previously saved from another game
	  @Override
	  public Room[] createAdventure() {
	
		  CaveConfiguration caveConfiguration = model.getCaveConfigurationFromLoad();
		  Room[] rooms = caveConfiguration.getRooms();
		  return rooms;
		  
	  }
}
