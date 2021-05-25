package damka;

public abstract class Move {
	Piece piece;
	Position destination;

	public Move(Piece piece, Position destination) {
		this.piece = piece;
		this.destination = destination;
		Position [] positionAns = this.piece.getAdjacentPositions();
		boolean isMove=false;
		for(int i=0; i<positionAns.length; i++) {
			if(this.destination.equals(positionAns[i])) {
				isMove=true;
				return;
			}
		}
		if(!isMove)
			throw new RuntimeException("An illegal destination id was given");
	}
	
	public Piece getPiece() {
		return this.piece;
	}

	public Position getDestination() {
		return this.destination;
	}

	public abstract boolean isJump();

	public boolean equals(Object other) {	
		if(other instanceof Move)
		{
			Move otherMove = Move.class.cast(other);

			return this.piece.equals(otherMove.getPiece()) && this.destination.equals(otherMove.getDestination());
		}else {
			return false;
		}	
	}
}

