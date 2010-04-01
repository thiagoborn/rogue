package util;

import map.builder.MapRandom;

public class GlobalRandom {
	private static MapRandom rnd=new MapRandom();

	public static boolean nextBoolean() {
		return rnd.nextBoolean();
	}

	public static int nextInt(int n) {
		return rnd.nextInt(n);
	}

	public static int nextInt(int min, int max) {
		return rnd.nextInt(min, max);
	}

	public static boolean nextKick(int prob) {
		return rnd.nextKick(prob);
	}
	
}
