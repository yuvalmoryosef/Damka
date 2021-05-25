package damka;

public abstract class Player {
	String name;
	CheckersColor color;
	
	public Player(String name, CheckersColor color){
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public CheckersColor getColor() {
		return color;
	}
	
	public abstract Move getMove(GameState state); 
}

