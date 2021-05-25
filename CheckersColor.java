package damka;
/**
 * Reresents a color of a checker in the checkers game.
 */
public class CheckersColor {

	private static final String[] COLOR_NAMES = { "Black", "White" };
	private static final int[] COLOR_VALUES = { 0, 1 };
	private int colorId;
	
	/**
	 * Initializes a Checkers color with the given color id.
	 * If the given color is illegal, a RuntimeException will be thrown.
	 * 
	 * @param colorId - the id of the color
	 */
	public CheckersColor(int colorId) {
		if (!legalColor(colorId))
			throw new RuntimeException("An illegal color id was given");
		this.colorId = colorId;
	}
	
	/**
	 * Returns true if and only if the given colorId represents a legal color
	 * @param colorId - the id of the color to check
	 * @return true if and only if the given colorId represents a legal color
	 */
	private boolean legalColor(int colorId) {
		boolean found = false;
		
		for (int i = 0; (i < COLOR_VALUES.length) && (!found); i = i + 1) {
			if (colorId == COLOR_VALUES[i])
				found = true;
		}
		
		return found;
	}
	
	public boolean equals(Object other) {
		boolean ans = false;
		if (other instanceof CheckersColor) {
			CheckersColor otherColor = (CheckersColor)other;
			ans = (colorId == otherColor.colorId);
		}
		return ans;
	}
	
	public String toString() {
		return COLOR_NAMES[colorId];
	}
}
