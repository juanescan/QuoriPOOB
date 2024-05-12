package domain;

import java.io.Serializable;

public class Wall implements Serializable {
    private int size;
    private boolean horizontal;
    private int xPosStart;
    private int xPosEnd;
    private int yPosStart;
    private int yPosEnd;

    public Wall(boolean horizontal,int xPosStart, int yPosStart, int xPosEnd, int yPosEnd) {
        this.size = 2;
        this.horizontal = horizontal;
        this.xPosStart = xPosStart;
        this.yPosStart = yPosStart;
        this.xPosEnd = xPosEnd;
        this.yPosEnd = yPosEnd;
    }

	public int getSize() {
		return size;
	}

	public boolean getHorizontal() {
		return horizontal;
	}

	public int getxPosStart() {
		return xPosStart;
	}

	public int getxPosEnd() {
		return xPosEnd;
	}

	public int getyPosStart() {
		return yPosStart;
	}

	public int getyPosEnd() {
		return yPosEnd;
	}

	
    

    
    
    
    
}