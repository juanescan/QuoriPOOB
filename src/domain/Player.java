package domain;

public class Player {
	private String name;
	private int walls;
	private Token token;
	
	public Player(String name,Token token, int walls) {
		this.name = name;
		this.token = token;
		this.walls = walls;
	}

	public String getName() {
		return name;
	}

	public int getWalls() {
		return walls;
	}

	public Token getToken() {
		return token;
	}


}
