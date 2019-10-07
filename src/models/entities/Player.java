package models.entities;

import java.util.ArrayList;

public class Player implements Runnable{
	private String name;
	private Genre genre;
	private int resistance;
	private int experience;
	private float luck;
	private ArrayList<Shoot> shoot;
	private Thread thread;
	private Team team;
	private int lucky;
	private boolean lluvia , viento;

	public Player(String name, Genre genre, int resistance, float luck, Team team, boolean b, boolean c) {
		this.name = name;
		this.genre = genre;
		this.resistance = resistance;
		this.experience = 10;
		this.luck = luck;
		this.thread = new Thread(this);
		this.team = team;
		this.shoot = new ArrayList<Shoot>();
		this.luck = 0;
		this.lluvia = b;
		this.viento = c;
		start();
	}

	public void start() {
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		thread.stop();
	}

	@Override
	public void run() {
		while (true) {
			try{
				Thread.sleep(10);
			}catch (InterruptedException e) { }
			shoot.add(new Shoot(getVelocity(), getAngle(), lluvia, viento));
			resistence();
			validate();
		}
	}

	private float getAngle() {
		return (float) Math.random()*(26-34+1)+34;
	}

	private float getVelocity() {
		return (float) Math.random()*(90-110+1)+110;
	}

	private void validate() {
		if (resistance < 4 ) {
			team.setPoints(this.getPoints());
			stop();
		}
	}

	public void resistence() {
		resistance -= 4; 
	}

	public int getExperience() {
		return experience;
	}

	public void addExperience(int exp) {
		this.experience += exp;
	}

	public Genre getGenre() {
		return genre;
	}
	public float getLuck() {
		return luck;
	}
	public String getName() {
		return name;
	}
	public int getResistance() {
		return resistance;
	}

	public void addLuck(float luck) {
		this.luck += luck;
	}

	public int getPoints() {
		int points = 0;
		for (Shoot shoot2 : shoot) {
			points += shoot2.getPoint();
		}
		return points;
	}

	public Team getTeam() {
		return team;
	}

	public void genrateShoot() {
		if (lucky == 3) {
			lucky = 0;
			addLuck((float) 0.05);
		}
		shoot.add(new Shoot(getVelocity(), getAngle(), lluvia, viento));
		this.lucky ++;
	}
}
