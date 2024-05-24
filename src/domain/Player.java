package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable{
	private String name;
	private int nWalls;
	private int normales;
	private int largas;
	private int temporales;
	private int aliadas;
	private Token token;


	
	public Player(String name,Token token, int nWalls, int normales, int largas, int temporales, int aliadas) {
		this.name = name;
		this.token = token;
		this.nWalls = nWalls;
		this.normales = normales;
		this.largas = largas;
		this.temporales = temporales;
		this.aliadas = aliadas;

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
	
	public void minusNormales() {
		normales--;
	}
	
	public void minusAliadas() {
		aliadas--;
	}
	
	public void minusTemporales() {
		temporales--;
	}
	
	public void minusLargas() {
		largas--;
	}

	public int getNormales() {
		return normales;
	}

	public void setNormales(int normales) {
		this.normales = normales;
	}

	public int getLargas() {
		return largas;
	}

	public void setLargas(int largas) {
		this.largas = largas;
	}

	public int getTemporales() {
		return temporales;
	}

	public void setTemporales(int temporales) {
		this.temporales = temporales;
	}

	public int getAliadas() {
		return aliadas;
	}

	public void setAliadas(int aliadas) {
		this.aliadas = aliadas;
	}

	


}
