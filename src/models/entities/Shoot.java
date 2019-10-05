package models.entities;

public class Shoot {
	private float velocity;
	private float angle;
	private int point;
	
	public Shoot(float velocity, float angle, int point) {
		this.velocity = velocity;
		this.angle = angle;
		this.point = point;
	}

	public void shoot(Player player) {
		player.resistence();
	}
	
	

}
