package domain;



import java.awt.Color;
import java.util.Random;

public class principianteMachine extends Player {
	private Random random;
	
    public principianteMachine(String name,Token token, int nWalls, int normales, int largas, int temporales, int aliadas) {
        super(name, token, nWalls, normales, largas,temporales,aliadas);
        random = new Random();
    }
    
    public void makeAction() {
    	int action = random.nextInt(2); 
        if (action == 0) {
            makeMove();
        } else {
            putWall();
        }
    }
    		

    private void makeMove() {
        int size = QuoriPOOB.getInstance().getSize();
        boolean moveSuccessful = false;

        while (!moveSuccessful) {
            int evenRow = generateRandomEvenNumber(size);
            int evenCol = generateRandomEvenNumber(size);
            try {
                moveSuccessful = QuoriPOOB.getInstance().move(evenRow, evenCol);
            } catch (QuoriPOOBException e) {
               makeMove();
            }
        }
    }
    
    private void putWall() {
    	int action = random.nextInt(2);
    	int size = QuoriPOOB.getInstance().getSize(); 
    	String[] wallTypes = {"NormalWall", "AllyWall", "LargeWall", "TemporalWall"};
        String selectedWall = wallTypes[random.nextInt(wallTypes.length)];
    	if(action == 0) {
    		//pared horizontal
    		int oddRow = generateRandomOddNumber(size);
    		int evenCol = generateRandomEvenNumber(size);
    		QuoriPOOB.getInstance().putWall(true, oddRow, evenCol, selectedWall);
    	}else {
    		int evenRow = generateRandomEvenNumber(size);
    		int oddCall = generateRandomOddNumber(size);
    		QuoriPOOB.getInstance().putWall(false, evenRow, oddCall, selectedWall);
    	}
    }
    
    private int generateRandomEvenNumber(int maxSize) {
        int number;
        do {
            number = random.nextInt(maxSize);
        } while (number % 2 != 0);
        return number;
    }
    
    private int generateRandomOddNumber(int maxSize) {
        int number;
        do {
            number = random.nextInt(maxSize);
        } while (number % 2 == 0); 
        return number;
    }
}

