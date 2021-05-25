package damka;

public class RandomPlayer extends Player {

	public RandomPlayer(String name, CheckersColor color) {
		super(name, color);
	}

	@Override
	public Move getMove(GameState state) {
		System.out.println(state.getJumpMoves(color).getMovesNumber());
		if(state.getJumpMoves(color).getMovesNumber()!=0)
			return state.getJumpMoves(color).getMove((int)(Math.random()*state.getJumpMoves(color).getMovesNumber()));
		else
			return state.getSimpleMoves(color).getMove((int)(Math.random()*state.getSimpleMoves(color).getMovesNumber()));
		
	}

}
