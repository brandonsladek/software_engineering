package esof322.a4;

// Brandon Sladek

public class LoadGameCaveFactory implements AbstractCaveFactory {
	
	private AdventureGameModelFacade model;
	
	public LoadGameCaveFactory(AdventureGameModelFacade thisModel) {
		model = thisModel;
	}

	@Override
	public AbstractCave createCaveObject() {
		return new LoadGameCave(model);
	}
	
}
