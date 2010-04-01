package util;

public class EnumUtil {
	public static String formatEnum(String txt) {
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (char c : txt.toCharArray()) {
			if (first) {
				c = Character.toUpperCase(c);
				first = false;
			} else {
				if (c == '_') {
					c = ' ';
				} else {
					c = Character.toLowerCase(c);
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
