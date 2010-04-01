package map.builder;

import java.util.Random;

public class MapRandom extends Random {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1667118067155364475L;

	public MapRandom() {

	}

	public MapRandom(long seed) {
		super(seed);
	}

	public int nextInt(int min, int max) {
		return min + nextInt(max - min + 1);
	}

	public boolean nextKick(int prob) {
		return nextInt(1000) < prob;
	}
}