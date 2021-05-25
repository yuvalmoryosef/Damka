package damka;

public class SimpleMove extends Move {

	public SimpleMove(Piece piece, Position destination) {
		super(piece, destination);
	}
	

	@Override
	public boolean isJump() {
		return false;
	}
	
	@Override
	public boolean equals(Object other) {	
		if(other instanceof SimpleMove)
		{
			return super.equals(other);
			
		}else {
			return false;
		}	
	}
	
	public String toString() {
		return "Simple:_" + 
			this.getPiece().toString() +
			"_to_destination_[" + 
			this.getDestination().getRow() +
			"," +
			this.getDestination().getColumn() +
			"]";
	}
}
