package data;

public class ShinyCharmTemp implements IShinyCharmTemp {

	private int time;
	
	public ShinyCharmTemp() {
		this.time = 0;
	}
	
	@Override
	public void addTime(int time) { 
		this.time += time;
	}

	@Override
	public void removeTime() {
		this.time = 0;
	}

	@Override
	public int getTime() {
		return this.time;
	}

	@Override
	public void setTime(int time) {
		this.time = time;
	}
}
