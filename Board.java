
import java.util.*;

public class Board {

	
    private ArrayList<Peg> pegs;
	
    public void addJump(int i, int jumpTo, int toJump) {
    	this.pegs.get(i).addJump(this.pegs.get(jumpTo), this.pegs.get(toJump));
    	this.pegs.get(jumpTo).addJump(this.pegs.get(i), this.pegs.get(toJump));
    }

    public Board duplicate() {
    	Board newBoard = new Board().build();

        for (int i = 0; i < 15; i++) {
            newBoard.pegs.get(i).setStatus(this.pegs.get(i).isFilled());
        }

	return newBoard;
    }

    public Board build() {
    	this.pegs = new ArrayList<>();
		
        for (int i = 0; i < 15; i++) {
            this.pegs.add(new Peg(true));
        }
    
        this.pegs.get(0).setStatus(false);
        
        int numInRow = 1;
	int rowStart = 0;

    	for (int i = 0; i < 6; i++) {
            if (i >= (rowStart + numInRow)) {
                rowStart += numInRow;
                numInRow++;
            }
            
            int toJump = i + numInRow;
            int jumpTo = toJump + numInRow + 1;
            this.addJump(i, jumpTo, toJump);
            
            toJump += 1;
            jumpTo += 2;
	    this.addJump(i, jumpTo, toJump);
	}

	numInRow = 1;
	rowStart = 0;

	for (int i = 0; i < 15; i++) {
	    if (i >= (rowStart + numInRow)) {
    		rowStart += numInRow;
	    	numInRow++;
	    }

	    if ((i + 2) < (rowStart + numInRow)) {
		int toJump = i + 1;
		int jumpTo = i + 2;
		this.addJump(i, jumpTo, toJump);
	    }
	}

        return this;
    }

    private String getString() {
        String boardString = "";

        int numInRow = 0;
        int rowStart = 0;

        for (int i = 0; i < 15; i++) {
            if (i >= (rowStart + numInRow)) {
                rowStart += numInRow;
                numInRow++;
                boardString += System.getProperty("line.separator");
                if (numInRow < 5)
                    boardString += String.format("%1$" + (2 * (5 - numInRow)) + "s", "");
            }
            if (this.pegs.get(i).isFilled())
                boardString += String.format("%1$-2s  ", String.valueOf(i));
            else
                boardString += "__";
        }

        return boardString;
    }

    public void draw() {
        System.out.print(this.getString() + System.getProperty("line.separator"));
    }
}
