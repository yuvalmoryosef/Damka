package damka;
/**
 * Represents a single position on the Checkers board.
 */
public class Position {

	private int column;
	private int row;

	/**
	 * Creates a new position in the given row and column.
	 *
	 * @param column - the column of the position.
	 * @param row -  the row of the position.
	 */
	public Position(int column, int row) {
		this.column = column;
		this.row = row;
	}

	/**
	 * Copy constructor that copies a give position.
	 *
	 * @param other - the position to copy.
	 */
	public Position(Position other) {
		this(other.column, other.row);
	}

	/**
	 * Returns the column of the position.
	 *
	 * @return Returns the column of the position.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the row of the position.
	 *
	 * @return Returns the row of the position.
	 */
	public int getRow() {
		return row;
	}

	public boolean equals(Object other) {
		boolean ans = false;
		if (other instanceof Position) {
			Position pos = (Position)other;
			ans = ((column == pos.column) && (row == pos.row));
		}
		return ans;
	}

	public String toString() {
		return "[" + row + "," + column + "]";
	}

	/**
	 * Returns the adjacent diagonal positions in the given row delta.
	 * The row delta must be either 1 or -1. An exception will be thrown otherwise.
	 *
	 * @param deltaRow - the delta of the row where we want the positions from.
	 * @return Two diagonal adjacent positions in the given delta.
	 */
	public Position[] getAdjacnetPositions(int deltaRow) {
		if ((deltaRow != 1) && (deltaRow != -1)) {
			throw new RuntimeException("Given delta row is illeagl. Should be either 1 or -1.");
		}

		Position[] ans = new Position[2];

		ans[0] = new Position(column + 1, row + deltaRow);
		ans[1] = new Position(column - 1, row + deltaRow);

		return ans;
	}

	/**
	 * Returns the jump diagonal position over the given adjacent diagonal position.
	 * The given adjacent position must be in a row and column that are diagonally adjacent
	 * to the current position. An exception will be thrown otherwise.
	 *
	 * @param adjacentPosition - the adjacent position we wish to jump over.
	 * @return The position over the given diagonal adjacent position.
	 */
	public Position getJumpPosition(Position adjacentPosition) {
		Position ans = null;

		int deltaRow = adjacentPosition.row - row;
		int deltaCol = adjacentPosition.column - column;

		if (((deltaRow == 1) || (deltaRow == -1)) &&
				((deltaCol == 1) || (deltaCol == -1))) {
			ans = new Position(column + (deltaCol * 2), row + (deltaRow * 2));
		} else {
			throw new RuntimeException("Given position is an illegal adjacent position.");
		}

		return ans;
	}

	/**
	 * Returns the adjacent diagonal position that is between the current position and the
	 * given jump position.
	 * The given jump position must be in a row and column that can be reached by jumping
	 * over an adjacent diagonal position. An exception will be thrown otherwise.
	 *
	 * @param jumpPosition - the jump position we wish to find the adjacent diagonal position to.
	 * @return The adjacent diagonal position between the current position and the given jump position.
	 */
	public Position getAdjacentPosition(Position jumpPosition) {
		Position ans = null;

		int deltaRow = jumpPosition.row - row;
		int deltaCol = jumpPosition.column - column;

		if (((deltaRow == 2) || (deltaRow == -2)) &&
				((deltaCol == 2) || (deltaCol == -2))) {
			ans = new Position(column + (deltaCol / 2), row + (deltaRow / 2));
		} else {
			throw new RuntimeException("Given position is an illegal jump position.");
		}

		return ans;
	}
}
