package models.entities;

public class Player implements Runnable{
	private String name;
	private Genre genre;
	private int resistance;
	private int experience;
	private float luck;
	private Shoot shoot;
	
	public Player(String name, Genre genre, int resistance, int experience, float luck, Shoot shoot) {
		super();
		this.name = name;
		this.genre = genre;
		this.resistance = resistance;
		this.experience = experience;
		this.luck = luck;
		this.shoot = shoot;
	}

	@Override
	public void run() {
		shoot.shoot(this);
	}

	public void resistence() {
		resistance -= 4; 
	}
	
	
}
