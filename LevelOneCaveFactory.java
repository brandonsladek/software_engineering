package esof322.a4;

// Brandon Sladek

public class LevelOneCaveFactory implements AbstractCaveFactory {

	@Override
	public AbstractCave createCaveObject() {
		return new LevelOneCave();
	}
	
}
