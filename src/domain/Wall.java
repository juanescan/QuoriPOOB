package domain;

public class Wall {
    private int size;
    private boolean direction;
    private int xPosStart;
    private int xPosEnd;
    private int yPosStart;
    private int yPosEnd;

    public Wall(boolean direction,int xPosStart, int yPosStart, int xPosEnd, int yPosEnd) {
        this.size = 2;
        this.direction = direction;
        this.xPosStart = xPosStart;
        this.yPosStart = yPosStart;
        this.xPosEnd = xPosEnd;
        this.yPosEnd = yPosEnd;
    }
    
    
    
    
}