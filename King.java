package damka;

public class King extends Piece {

	public King(CheckersColor color, Position position) {
		super (color, position);
	}

	@Override
	public Position[] getAdjacentPositions() {
		Position[] posKing = new Position[4];
		posKing[0] = this.position.getAdjacnetPositions(1)[0];
		posKing[1] = this.position.getAdjacnetPositions(1)[1];
		posKing[2] = this.position.getAdjacnetPositions(-1)[0];
		posKing[3] = this.position.getAdjacnetPositions(-1)[1];
		return posKing;
		
	}


@Override
public boolean isKing() {
	return true;
}

public boolean equals(Object other) {
	if(other instanceof King)
	{
		King otherKing = King.class.cast(other);

		return this.position.equals(otherKing.position) && this.color.equals(otherKing.getColor());
	}else {
		return false;
	}		
}

public String toString() {
	return "King[" + this.position.getRow() +","+this.position.getColumn()+"]["+this.getColor().toString()+"]";
}

}
