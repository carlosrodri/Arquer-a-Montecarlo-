package models.entities;

public class Shoot {
	private float velocity;
	private int point;
	private float position;

	public Shoot(float velocity, float angle, boolean lluvia, boolean viento) {

		this.position = (float) (velocity*Math.sin(Math.toRadians(angle))*2-0.5*(9.8*Math.pow(2, 2)));
		this.point = calculatePoints(lluvia, viento);
	}

	public float getPosition() {
		return position;
	}

	/**
	 * calcula los puntos del lanzamiento respecto a la posicion de caida de la flecha dependiendo de los factores externos
	 * @param lluvia booleano de la lluvia
	 * @param viento booleano del viento
	 * @return los puntos dependiendo la posicion
	 */
	public int calculatePoints(boolean lluvia, boolean viento) {
		if (lluvia && viento) {
			if (position >= 75.8 && position <= 86.8) {
				return 10;
			}else if (position+0.55 >= 70.3 && position+0.55 <= 75.79 || position+0.55 >= 86.81 && position+0.55 <= 92.3) {
				return 8;
			}else if (position+0.99 >= 64.81 && position+0.99 <= 70.29 || position+0.99 >= 92.31 && position+0.99 <= 97.8) {
				return 6;
			}else if (position >= 59.3 && position <= 64.79 || position >= 97.81 && position <= 103.4) {
				return 0;
			}
		}else if (lluvia && !viento) {
			if (position >= 75.8 && position <= 86.8) {
				return 10;
			}else if (position+0.55 >= 70.3 && position+0.55 <= 75.79 || position+0.55 >= 86.81 && position+0.55 <= 92.3) {
				return 8;
			}else if (position >= 64.81 && position <= 70.29 || position >= 92.31 && position <= 97.8) {
				return 6;
			}else if (position >= 59.3 && position <= 64.79 || position >= 97.81 && position <= 103.4) {
				return 0;
			}
		}else if (!lluvia && !viento) {
			if (position >= 75.8 && position <= 86.8) {
				return 10;
			}else if (position >= 70.3 && position <= 75.79 || position >= 86.81 && position <= 92.3) {
				return 8;
			}else if (position >= 64.81 && position <= 70.29 || position >= 92.31 && position <= 97.8) {
				return 6;
			}else if (position >= 59.3 && position <= 64.79 || position >= 97.81 && position <= 103.4) {
				return 0;
			}
		}
		return 0;
	}

	public float getVelocity() {
		return velocity;
	}
	
	public int getPoint() {
		return point;
	}
}
