package domain;

import java.util.HashMap;
import java.util.Map;

public class Player {
	private String name;
	private int nWalls;
	private Token token;

	
	public Player(String name,Token token, int nWalls) {
		this.name = name;
		this.token = token;
		this.nWalls = nWalls;
		
	}

	public String getName() {
		return name;
	}

	public int getNWalls() {
		return nWalls;
	}

	public Token getToken() {
		return token;
	}
	
	public void minusNWalls() {
		nWalls--;
	}




}