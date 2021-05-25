package damka;
public class Board {

	int size;
	public Piece[][] board;

	public Board (int size) {
		this.board= new Piece[size][size];
		int start =1;
		if ((size<4)||(size%2==1))
			throw new RuntimeException("An illegal size was given");
		this.size = size;
		int playerRowSize=size/2-1;
		//Black
		for(int rowIndex=0; rowIndex<playerRowSize; rowIndex++) {
			for(int colIndex=start; colIndex<size; colIndex+=2) {
				board[rowIndex][colIndex]=new Checker(Constants.COLOR_BLACK,new Position (colIndex, rowIndex));
			}
			if(start==1)
				start=0;
			else
				start=1;
		}
		//White
		start=0;
		for(int roIndex=size-1; roIndex>=this.size-playerRowSize; roIndex--) {
			for(int colIndex=start; colIndex<this.size; colIndex+=2) {
				board[roIndex][colIndex]=new Checker(Constants.COLOR_WHITE,new Position (colIndex, roIndex));
			}
			if(start==1)
				start=0;
			else
				start=1;
		}
	}

	public int getSize() {
		return size;
	}

	public boolean isValidPosition(Position position) {
		if((position.getColumn()>0)&&(position.getColumn()<this.size)&&(position.getRow()>0)&&(position.getRow()<this.size))
			return true;
		else
			return false;
	}

	public Piece[] getPieces(CheckersColor color) {
		Piece[] colorsPieces = new Piece[getPiecesNumber(color)];
		int counter=0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if((this.board[i][j]!=null)&&(this.board[i][j].getColor().equals(color))) {
					colorsPieces[counter]=this.board[i][j];
					counter++;
				}
			}
		}
		return colorsPieces;
	}
	public Piece getPieceAt(Position position) {
		if((this.board[position.getRow()][position.getColumn()]) instanceof Piece)
			return this.board[position.getRow()][position.getColumn()];
		else
			return null;
	}

	public void removePieceFromBoard(Piece piece) {
		//if (this.board[piece.position.getRow()][piece.position.getColumn()]==null)
		//	throw new RuntimeException("An illegal piece was given");
		this.board[piece.position.getRow()][piece.position.getColumn()]=null;
	}

	public void addPieceToBoard(Piece piece) {
		this.board[piece.position.getRow()][piece.position.getColumn()]=piece;
	}
	
	public int getPiecesNumber(CheckersColor color) {
		int counterColorPieces =0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if((board[i][j]!=null)&&(board[i][j].getColor().equals(color)))
					counterColorPieces++;
			}
		}
		return counterColorPieces;	
	}


	public void verifyCrown(Piece piece) {
		if(isValidPosition(piece.position)) {
			if(((piece.getColor()==Constants.COLOR_BLACK)&&(piece.inRow(this.size-1)))||(((piece.getColor()==Constants.COLOR_WHITE)&&(piece.inRow(0)))))
				board[piece.position.getRow()][piece.position.getColumn()]= new King(piece.getColor(),piece.position);
				
		}
	}

	public void printBoard() {
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				if(board[i][j]==null)
					System.out.print("| |");
				else
				{
					if(board[i][j].getColor().equals(Constants.COLOR_BLACK)){
						if(board[i][j].isKing()) {
							System.out.print("|K|");
						}
						else 
							System.out.print("|B|");
					}
					if(board[i][j].getColor().equals(Constants.COLOR_WHITE)) {
						if(board[i][j].isKing())
							System.out.print("|k|");
						else
							System.out.print("|w|");
					}
				}
			}
			System.out.println();
		}
	}
}