package damka;
/**
 * The main Checkers game engine.
 * Allows graphical simulation of a game between two players.
 */
public class Game {

	private static final String WIN_MESSAGE = " won the game!";
	private static final String TIE_MESSAGE = "The game is tied!";

	private GameState state;
	private CheckersGraphics graphics;
	private Player player1;
	private Player player2;

	/**
	 * Constructor - initializes the Game.
	 *
	 * @param boardSize - the requested board size.
	 * @param player1 - The first player.
	 * @param player2 - The second player.
	 */
	public Game(int boardSize, Player player1, Player player2) {
		// Verify that the two players have distinct colors
		if (player1.getColor().equals(player2.getColor()))
			throw new RuntimeException("Cannot create a checkers game where the two players have the same color.");

		this.player1 = player1;
		this.player2 = player2;
		Board board = new Board(boardSize);
		state = new GameState(board);
		graphics = new CheckersGraphics(board);
	}

	/**
	 * Simulate the checkers game between the two players.
	 */
	public void simulateGame() {
		// While the game is not over, let the current player choose his move, and apply that move
		while (!state.isGameOver()) {
			Move chosenMove = null;
			if (state.getCurrentPlayerColor().equals(player1.getColor())) {
				chosenMove = player1.getMove(state);
			} else if (state.getCurrentPlayerColor().equals(player2.getColor())) {
				chosenMove = player2.getMove(state);
			}

			state.applyMove(chosenMove);
			graphics.paintBoard();
		}
		// The game is over, check which player has won, and show an appropriate message
		if (state.getWinnerColor().equals(player1.getColor())) {
			graphics.showGameOver(player1.getName() + WIN_MESSAGE);
		} else if (state.getWinnerColor().equals(player2.getColor())) {
			graphics.showGameOver(player2.getName() + WIN_MESSAGE);
		} else {
			graphics.showGameOver(TIE_MESSAGE);
		}
	}

	public static void main(String[] args) {
		Player p1 = new RandomPlayer("Alice", Constants.COLOR_BLACK);
		Player p2 = new RandomPlayer("Bob", Constants.COLOR_WHITE);
		Game g = new Game(8, p1, p2);

		// Simulate a game between Alice and Bob
		g.simulateGame();
	}
}
