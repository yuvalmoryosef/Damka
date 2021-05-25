package damka;

public class MovesCollection {
	Move[] moves;
	
	public MovesCollection() {
		moves = new Move[0]; 
	}
	
	public void addMove(Move move) {
		Move[] newMoves = new Move[moves.length+1];
		for(int i=0; i<moves.length; i++) {
			newMoves[i]=moves[i];
		}
		newMoves[newMoves.length-1]=move;
		moves=newMoves;
	}
	
	public int getMovesNumber() {
		return moves.length;
	}
	
	public Move getMove(int index) {
		return moves[index];
	}
}
