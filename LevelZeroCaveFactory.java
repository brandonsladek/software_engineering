package esof322.a4;

// Brandon Sladek

public class LevelZeroCaveFactory implements AbstractCaveFactory {

	@Override
	public AbstractCave createCaveObject() {
		return new LevelZeroCave();
	}
	
}
