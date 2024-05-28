package domain;

import java.io.Serializable;

public abstract class Wall implements Serializable {
	
    protected boolean horizontal;
    protected int xPos;
    protected int yPos;
    protected Player player;

    /**
     * Constructor for the walls
     * @param horizontal
     * @param xPos
     * @param yPos
     * @param player
     */
    public Wall(boolean horizontal,int xPos, int yPos, Player player) {
        this.horizontal = horizontal;
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player;

    }
    
    /**
     * abstract method put for the walls
     * @throws QuoriPOOBException
     */
    public abstract void put() throws QuoriPOOBException;
    
    /**
     * abstract method act for the walls
     * @throws QuoriPOOBException
     */
    public abstract void act() throws QuoriPOOBException;
    
    public abstract boolean canPass();

	public boolean getHorizontal() {
		return horizontal;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public Player getPlayer() {
		return player;
	}

	

	
    

    
    
    
    
}