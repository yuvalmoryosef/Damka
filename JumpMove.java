package damka;

public class JumpMove extends Move {

	public JumpMove(Piece piece, Position destination) {
		super(piece, destination);
	}

	@Override
	public boolean isJump() {
		return true;
	}
	
	@Override
	public boolean equals(Object other) {	
		if(other instanceof JumpMove)
		{
			return super.equals(other);
			
		}else {
			return false;
		}	
	}

	public String toString() {
		return "Jump:_" + 
			this.getPiece().toString() +
			"_to_destination_[" + 
			this.getDestination().getRow() +
			"," +
			this.getDestination().getColumn() +
			"]";
	}
}
