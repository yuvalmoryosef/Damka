package damka;

public class GameState {

	public Board board;
	Player currentPlayer;
	Player whitePlayer;
	Player blackPlayer;
	int movesCounter=0;

	public GameState(Board board) {
		this.board = board;

		whitePlayer= new RandomPlayer("whitePlayer", new CheckersColor(1));
		blackPlayer = new RandomPlayer("blackPlayer", new CheckersColor(0));
		currentPlayer = blackPlayer;
	}

	public CheckersColor getCurrentPlayerColor() {
		return currentPlayer.getColor();
	}

	public MovesCollection getLegalMovesCollection() {
		MovesCollection movesCollection = new MovesCollection();

		for(Piece p : board.getPieces(getCurrentPlayerColor())) {
			for(Position pos : p.getAdjacentPositions()) {
				try {
					p.getJumpedOverPosition(pos);
					if(board.isValidPosition(pos))
						movesCollection.addMove(new JumpMove(p, pos));

				} catch (Exception e) {
					if(board.isValidPosition(pos))
						movesCollection.addMove(new SimpleMove(p, pos));
				}
			}
		}

		return movesCollection;
	}

	public void applyMove(Move move) {
		if(move.isJump()) {
			Position jumpOverPosition = move.getPiece().getJumpedOverPosition(move.getDestination());
			Piece pieceToRemove = board.getPieceAt(jumpOverPosition);
			board.removePieceFromBoard(pieceToRemove);

			//TODO: remove the piece from move.getPiece from baord.
			board.removePieceFromBoard(move.getPiece());
			//TODO: add piece from move.getPiece from baord to new destination.

			move.getPiece().setPosition(move.getDestination());
			board.addPieceToBoard(move.getPiece());
			if(((move.getPiece().inRow(0))&&(move.getPiece().getColor().equals(Constants.COLOR_WHITE)))
					||(((move.getPiece().inRow(board.getSize()-1))&&
							(move.getPiece().getColor().equals(Constants.COLOR_BLACK)))))
				if((!move.getPiece().isKing()))
					board.verifyCrown(move.getPiece());
			if(currentPlayer.getColor().equals(Constants.COLOR_BLACK))
				currentPlayer = whitePlayer;
			else
				currentPlayer = blackPlayer;

			movesCounter=1;
		}
		else
		{
			//TODO: remove the piece from move.getPiece from baord.

			Piece pieceToRemove = move.getPiece();
			board.removePieceFromBoard(pieceToRemove);
			//board.addPieceToBoard(move.getPiece().);
			move.getPiece().setPosition(move.getDestination());
			board.board[move.getDestination().getRow()][move.getDestination().getColumn()] = move.getPiece();
			

			if(((move.getPiece().inRow(0))&&(move.getPiece().getColor().equals(Constants.COLOR_WHITE)))
					||(((move.getPiece().inRow(board.getSize()-1))&&(move.getPiece().getColor().equals(Constants.COLOR_BLACK)))))
				if((!move.getPiece().isKing()))
					board.verifyCrown(move.getPiece());
			
			if(currentPlayer.getColor().equals(Constants.COLOR_BLACK))
				currentPlayer = whitePlayer;
			else
				currentPlayer = blackPlayer;


			movesCounter++;
		}
		board.printBoard();
	}

	public boolean isGameOver() {
		if((board.getPiecesNumber(Constants.COLOR_BLACK)==0)||(board.getPiecesNumber(Constants.COLOR_WHITE)==0)
				||(Constants.MAX_ROUNDS_WITHOUT_JUMPING< movesCounter))
			return true;
		else {
			boolean isOverWhitePosition = true;
			boolean isOverBlackPosition = true;
			for(int i=0; i< board.getSize();i++) {
				if((isOverBlackPosition==false)&&(isOverWhitePosition==false))
					return false;
				for(int j=0; j< board.getSize();j++) {
					Position posForCheck = new Position(j,i); 
					Piece pieceForCheck = this.board.getPieceAt(posForCheck);
					if(pieceForCheck != null) {
						if((pieceForCheck.getColor()==Constants.COLOR_WHITE)&&(isOverWhitePosition==true)){
							if(pieceForCheck.getAdjacentPositions().length>0)
								isOverWhitePosition = false;		
						}else if((pieceForCheck.getColor()==Constants.COLOR_BLACK)&&(isOverBlackPosition==true)) {
							if(pieceForCheck.getAdjacentPositions().length>0)
								isOverBlackPosition = false;	
						}
					}
					if((isOverBlackPosition==false)&&(isOverWhitePosition==false))
						return false;
				}
			}
		}
		return true;
	}

	public CheckersColor getWinnerColor() {
		if((isGameOver()==false)||(Constants.MAX_ROUNDS_WITHOUT_JUMPING< movesCounter)) {
			return null;
		}else {
			if(board.getPiecesNumber(Constants.COLOR_BLACK)==0) {
				//white is the winner.
				CheckersColor colorWinner = new CheckersColor(1);
				return colorWinner;
			}
			else if(board.getPiecesNumber(Constants.COLOR_WHITE)==0) {
				//black is the winner.
				CheckersColor colorWinner = new CheckersColor(0);
				return colorWinner;
			}
		}
		return null;
	}

	public MovesCollection getJumpMoves(CheckersColor color) {
		MovesCollection jumpMoves = new MovesCollection();

		for(Piece p : board.getPieces(color)) {
			for(Position pos : p.getAdjacentPositions()) {
				try {
					if(p.isKing()) {
						Position newDestination = new Position(pos.getColumn()+1,pos.getRow()+1);
						p.getJumpedOverPosition(newDestination);
						if(board.isValidPosition(p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))))
							if(!board.getPieceAt(pos).getColor().equals(color))
								jumpMoves.addMove(new JumpMove(p, p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))));

						newDestination = new Position(pos.getColumn()-1,pos.getRow()-1);
						p.getJumpedOverPosition(newDestination);
						if(board.isValidPosition(p.getJumpedOverPosition( new Position(pos.getColumn()-1,pos.getRow()-1))))
							if(!board.getPieceAt(pos).getColor().equals(color))
								jumpMoves.addMove(new JumpMove(p, p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))));

					}
					if(color.equals(Constants.COLOR_BLACK)) {
						Position newDestination = new Position(pos.getColumn()+1,pos.getRow()+1);
						p.getJumpedOverPosition(newDestination);
						if(board.isValidPosition(p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))))
							if(!board.getPieceAt(pos).getColor().equals(color))
								jumpMoves.addMove(new JumpMove(p, p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))));

					}else if(color.equals(Constants.COLOR_WHITE)) {
						Position newDestination = new Position(pos.getColumn()-1,pos.getRow()-1);
						p.getJumpedOverPosition(newDestination);
						if(board.isValidPosition(p.getJumpedOverPosition( new Position(pos.getColumn()-1,pos.getRow()-1))))
							if(!board.getPieceAt(pos).getColor().equals(color))
								jumpMoves.addMove(new JumpMove(p, p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1))));

					}
					//p.getJumpedOverPosition( new Position(pos.getColumn()+1,pos.getRow()+1));
					
				} catch (Exception e) {
					//movesCollection.addMove(new SimpleMove(p, pos));
				}
			}
		}
		return jumpMoves;
	}
	public MovesCollection getSimpleMoves(CheckersColor color) {
		MovesCollection simpleMoves = new MovesCollection();

		for(Piece p : board.getPieces(color)) {
			for(Position pos : p.getAdjacentPositions()) {
				try {
					if(board.isValidPosition(pos)&&(board.board[pos.getRow()][pos.getColumn()]==null))
						simpleMoves.addMove(new SimpleMove(p, pos));
					
				} catch (Exception e) {
					//if(board.isValidPosition(pos)&&(board.board[pos.getRow()][pos.getColumn()]==null))
					//simpleMoves.addMove(new SimpleMove(p, pos));
				}
			}
		}

		return simpleMoves;
	}
}