package util;

public class Bounds {
	private Integer index;
	private int size;

	public Bounds() {
		index = null;
		size = 0;
	}

	public Integer getIndex() {
		return index;
	}

	public int getSize() {
		return size;
	}

	public void mergePoint(int i) {
		if (index == null) {
			index = i;
			size = 1;
		} else {
			if (i < index) {
				int dif = index - i;
				index = i;
				size += dif;
			}
		}

		int ox = index + size;
		if (i >= ox) {
			size += 1 + ox - i;
		}

	}
}