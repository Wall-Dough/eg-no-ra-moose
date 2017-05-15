
import java.util.list;

public class Board {

	
	private List<Peg> pegs;
	
	public void addJump(int jumpTo, int toJump) {
		this.pegs.get(i).addJump(this.pegs.get(jumpTo), this.pegs.get(toJump));
		this.pegs.get(jumpTo).addJump(this.pegs.get(i), this.pegs.get(toJump));
	}

	public Board duplicate() {
		Board newBoard = new Board();

		for (int i = 0; i < 15; i++) {
			newBoard.pegs.add(this.pegs.get(i).duplicate());
		}

		return newBoard;
	}

	public void build() {
		this.pegs = new List<>();
		
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
			this.addJump(jumpTo, toJump);
			
			toJump += 1;
			jumpTo += 2;
			this.addJump(jumpTo, toJump);
		}

		numInRow = 1;
		rowStart = 0;

		for (int i = 0; i < 15; i++) {
			if (i >= (rowStart + numInRow)) {
				rowStart += numInRow;
				numInRow++;
			}

			if ((i + 2) < (rowStart + numInRow)) {
				toJump = i + 1;
				jumpTo = i + 2;
				this.addJump(jumpTo, toJump);
			}
		}
	}
}
