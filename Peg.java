
import java.util.List;

public class Peg {
	
	public class Jump {
		
		private Peg toGoTo;
		private Peg toJump;
		
		public Jump(Peg toGoTo, Peg toJump) {
			this.toGoTo = toGoTo;
			this.toJump = toJump;
		}

		public void setToGoTo(Peg toGoTo) { this.toGoTo = toGoTo; }

		public Peg getToJump() { return this.toJump; }

		public void setToJump(Peg toJump) { this.toJump = toJump; }

		public Peg getToGoTo() { return this.toGoTo; }
		
		public boolean canJump() {
			return !this.toGoTo.isFilled() && this.toJump.isFilled();
		}
	}

	private boolean status;
	
	private List<Jump> jumps;

	public Peg(boolean status) {
		this.status = status;
		this.jumps = new List<>();
	}

	public Peg duplicate() {
		Peg newPeg = new Peg(this.status);
	
	public boolean isFilled() { return this.status; }

	public void setStatus(boolean status) { this.status = status; }


	public void addJump(Peg toGoTo, Peg toJump) {
		this.jumps.add(new Peg(toGoTo, toJump));
	}

	public List<Jump> getJumps() {
		List<Jump> canJump = new List<>();

		for (int i = 0; i < this.jumps.size(); i++) {
			if (this.jumps.get(i).canJump()) {
				canJump.add(this.jumps.get(i));
			}
		}
		
		return canJump;
	}

	public boolean hasJumps() {
		return this.getJumps().size() > 0;
	}
	
	public void performJump(Jump theJump) {
		if (theJump.getToGoTo().isFilled()) return;

		if (!theJump.getToJump().isFilled()) return;

		this.setStatus(false);
		theJump.getToGoTo().setStatus(true);
		theJump.getToJump().setStatus(false);
	}
}
