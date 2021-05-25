package damka;

public class Checker extends Piece {

	public Checker(CheckersColor color, Position position) {
		super (color, position);
	}
	
	
	
	@Override
	public Position[] getAdjacentPositions() {
		if(this.color.equals(Constants.COLOR_BLACK)){
			return this.position.getAdjacnetPositions(1);
		}
		else {
			return this.position.getAdjacnetPositions(-1);
		}
	}

	@Override
	public boolean isKing() {
		return false;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Checker)
		{
			Checker otherChecker = Checker.class.cast(other);

			return this.position.equals(otherChecker.position) && this.color.equals(otherChecker.position);
		}else {
			return false;
		}		
	}

	public String toString() {
		return "Checker[" + this.position.getRow() +","+this.position.getColumn()+"]["+this.getColor().toString()+"]";
	}
}
