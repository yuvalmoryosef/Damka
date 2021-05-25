package damka;
/**
 * A collection of constants used in the Checkers assignment.
 */
public class Constants {
	/**
	 * A constant that represents the black pieces color.
	 */
	public static CheckersColor COLOR_BLACK = new CheckersColor(0);

	/**
	 * A constant that represents the white pieces color.
	 */
	public static CheckersColor COLOR_WHITE = new CheckersColor(1);


	/**
	 * A constant that represents the consecutive number of rounds,
	 * that if no jump move is done in these rounds, the game should
	 * end and called a tie.
	 */
	public static int MAX_ROUNDS_WITHOUT_JUMPING = 100;
}
