package damka;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * Represents a piece on the Checkers board.
 */
public abstract class Piece {
	protected CheckersColor color;
	protected Position position;

	Piece(CheckersColor color, Position position) {
		this.color = color;
		this.position = position;
	}
	/*
	protected Position getPosition () {
		return this.position;
	}
	*/
	public boolean inPosition(Position position) {
		return position.equals(this.position);
	}
	
	public CheckersColor getColor() {
		return this.color;
	}
	
	public void setPosition(Position position) {
		this.position = position; 
	}
	
	public abstract Position[] getAdjacentPositions();
	
	public Position getAfterJumpPosition(Piece other) {
		try {
			return this.position.getAdjacentPosition(other.position);
		} catch (RuntimeException e) {
			System.out.println("IllegalPosiotionAfterJump");//check
			return null;
		}
		
	}
	
	public Position getJumpedOverPosition(Position jumpDestination) {
		Position[] positions = getAdjacentPositions();
		
		boolean legalDestination = false;
		for(Position p : positions) {
			if(this.position.equals(p)) {
				legalDestination = true;
				break;
			}
		}
		
		if(! legalDestination)
			throw new RuntimeException("Given position is an illegal jump destination.");
		
		int col = jumpDestination.getColumn() - this.position.getColumn();
		int row = jumpDestination.getRow() - this.position.getRow();
		return new Position(this.position.getColumn()+col, this.position.getRow()+row);
	}
	
	public abstract boolean isKing();
	
	public boolean inRow(int row) {
		return this.position.getRow() == row;
	}
	
	
	
}
