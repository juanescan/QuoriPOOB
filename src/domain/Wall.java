package domain;

import java.io.Serializable;

public abstract class Wall implements Serializable {
	
    protected boolean horizontal;
    protected int xPos;
    protected int yPos;
    protected Player player;

    public Wall(boolean horizontal,int xPos, int yPos, Player player) {
        this.horizontal = horizontal;
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player;

    }
    
    public abstract void put() throws QuoriPOOBException;
    
    public abstract void act() throws QuoriPOOBException;

	public boolean getHorizontal() {
		return horizontal;
	}

	public int getxPos() {
		return xPos;
	}



	public int getyPos() {
		return yPos;
	}

	

	
    

    
    
    
    
}