package models.entities;


public class Team {
	private String name;
	private int points;

	public Team(String name) {
		this.name = name;
		this.points = 0;
	}
	
	public void setPoints(int points) {
		this.points += points;
	}

	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}
}

