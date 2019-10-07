package models.dao;

import java.util.ArrayList;
import java.util.regex.Pattern;


import models.entities.Departure;
import models.entities.Genre;
import models.entities.Player;
import models.entities.Round;
import models.entities.Team;

public class Game {
	private boolean lluvia, viento;
	private ArrayList<Departure> departures;

	public Game(boolean viento, boolean lluvia) {
		departures = new ArrayList<Departure>();
		this.lluvia = lluvia;
		this.viento = viento;
	}

	/**
	 * este metodo genera rondas hasta que haya un equipo que gane 10 rondas y agrega una nueva partida a la lista de partidas, las cuales deben ser 500
	 */
	public void generateDeparture() {
		for (int i = 0; i < 500; i++) {
			int victorias1 = 0, victorias2 = 0;
			int count = 1;
			ArrayList<Round> roundList = new ArrayList<Round>();
			while (victorias1 < 11 || victorias2 < 11) {
				Round round = new Round(generatePlayerList(viento, lluvia), (count+1)+"");
				roundList.add(round);
				count ++;
				if (round.getTeamWin().getName().equals("Equipo 1")) {
					victorias1 ++;
				}else {
					victorias2 ++;
				}
			}
			System.out.println("Partida número " + (i+1) + " en juego...");
			departures.add(new Departure(roundList, i+""));
		}
	}


	/**
	 * genera una lista de 40 jugadores los cales 20 pertenecen al equipo 1 y los restantes al equipo 2
	 * @param lluvia 
	 * @param viento es un booleano para saber si el escenario contiene vientos 
	 * @return la lista de jugadores 
	 */
	private ArrayList<Player> generatePlayerList(boolean viento, boolean lluvia) {
		ArrayList<Player> playerList = new ArrayList<Player>();
		if (lluvia && viento) {
			for (int i = 0; i < 40; i++) {
				if (i<20) {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 1"), true, true));
				}else {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 2"), true, true));
				}
			}
		}else if (lluvia && !viento) {
			for (int i = 0; i < 40; i++) {
				if (i<20) {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 1"), true, false));
				}else {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 2"), true, false));
				}
			}
		}else if (!lluvia && !viento) {
			for (int i = 0; i < 40; i++) {
				if (i<20) {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 1"), false, false));
				}else {
					playerList.add(new Player(generateName(i), generateGenre(i), getResistence(), getLuck(), new Team("Equipo 2"), false, false));
				}
			}
		}
		return playerList;
	}


	/**
	 * el metodo que crea el genero dependiendo los nombres de los jugadores
	 * @param i la posiscion del vector de nombres
	 * @return el genero dependiendo del  nombre
	 */
	private Genre generateGenre(int i) {
		String regex = "[0123456789]";
		if(Pattern.matches(regex, i+"")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("13") || String.valueOf(i).equals("16")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("17") || String.valueOf(i).equals("19")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("20") || String.valueOf(i).equals("22")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("23") || String.valueOf(i).equals("24")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("25") || String.valueOf(i).equals("30")) {
			return Genre.MAN;
		}else if(String.valueOf(i).equals("36") || String.valueOf(i).equals("37")) {
			return Genre.MAN;
		}else {
			return Genre.WOMAN;
		}
	}

	private String generateName(int n) {
		String [] names = {"Lyle", "Edan", "Cooper", "Isaiah", "Ferris", "Ulric", "Abraham", "Joel", "Sebastian", "Arsenio", "Lydia",
				"Kelly", "Charissa", "Paul", "Kimberley", "Ori", "Duncan", "Boris", "Kiara", "Brian", "Gail", "Zelda", "Elton", "Hedy",
				"Kenyon", "Brooke", "Malcolm", "Jaden", "Sonya", "Audra", "Christine", "Howard", "Wade", "Tanisha", "Olga", "Melinda",
				"Carol", "Russell", "Ryder", "Jessica"};
		return names[n];
	}

	private float getLuck() {
		return (float) ((float) Math.random()*(4.1-1+1)+1);
	}

	private int getResistence() {
		return (int) Math.floor(Math.random()*(60-40+1)+40);
	}


	/**
	 * El metodo se encarga de llamar a cada metodo de los reportes pedidos
	 */
	public void printStatics() {
		getWinByDeparture();
		getPromedyLuck();
		getPromedyExperience();
		getPlayerByLuckGame();
		getPlayerByExpGame();
		getTeamByGame();
		getAbsolutteam();
		getGenreByGame();
		getAbsolutGenre();
	}


	/**
	 * El metodo se encarga de obtener el genero que mas victorias ha tenido en todos los escenarios de juego
	 */
	private void getAbsolutGenre() {
		int m = 0; int w = 0;
		for (Departure departure : departures) {
			if (departure.getGenreVictory().equals("man")) {
				m++;
			}else {
				w++;
			}
		}
		if (m>w) {
			System.out.println("El genero con mas victorias totales es el masculino " );
		}else {
			System.out.println("El genero con mas victorias totales es el femenino " );
		}
	}


	/**
	 * El metodo se encarga de obtener el genero que mas victorias ha tenido por cada escenario de juego
	 */
	private void getGenreByGame() {
		int m = 0; int w = 0;
		for (Departure departure : departures) {
			if (departure.getGenreVictory().equals("man")) {
				m++;
			}else {
				w++;
			}
		}
		if (m>w) {
			System.out.println("El genero con mas victorias en el escenario es el masculino " );
		}else {
			System.out.println("El genero con mas victorias en el escenario es el femenino " );
		}
	}

	/**
	 * El metodo se encarga de obtener el equipo que mas victorias ha tenido en todos los escenarios de juego
	 */
	private void getAbsolutteam() {
		Team t = null;
		for (Departure departure : departures) {
			if (t == null) {
				t = departure.getWinOfDeparture();
			}else {
				if (departure.getWinOfDeparture().getPoints() > t.getPoints()) {
					t = departure.getWinOfDeparture();
				}
			}
		}
		System.out.println("El equipo en con más victorias totales es : " + t.getName() + " con un total de: " + t.getPoints() + " puntos");
	}

	/**
	 * El metodo se encarga de obtener el equipo que mas victorias ha tenido en cada escenario de juego
	 */
	private void getTeamByGame() {
		Team t = null;
		for (Departure departure : departures) {
			if (t == null) {
				t = departure.getWinOfDeparture();
			}else {
				if (departure.getWinOfDeparture().getPoints() > t.getPoints()) {
					t = departure.getWinOfDeparture();
				}
			}
		}
		System.out.println("El equipo en con más victorias en el escenario es : " + t.getName() + " con un total de: " + t.getPoints() + " puntos");
	}


	/**
	 * El metodo se encarga de obtener el jugador por escenario que mas puntos de experiencia ha ganado
	 */
	private void getPlayerByExpGame() {
		for (Departure departure : departures) {
			System.out.println("El jugador con mas experiencia del escenario es : " + departure.getPlayerWithMoreLuck().getName() + "  con un total de: "
					+ departure.getPlayerWithMoreLuck().getExperience() + " puntos");
		}
	}

	/**
	 * El metodo se encarga de obtener el jugador por escenario que mas puntos de suerte ha ganado
	 */
	private void getPlayerByLuckGame() {
		for (Departure departure : departures) {
			System.out.println("El jugador con mas suerte del escenario es : " + departure.getPlayerWithMoreLuck().getName() + "  con un total de: "
					+ departure.getPlayerWithMoreLuck().getLuck() + " puntos");
		}
	}

	/**
	 * El metodo se encarga de obtener el promedio de puntos de experiencia ganado en cada partida
	 */
	private void getPromedyExperience() {
		for (Departure departure : departures) {
			System.out.println("El promedio de puntos de experiencia ganados por partida es de: " + departure.getPromedyExperiencepoints()+ " puntos");
		}
	}

	/**
	 * El metodo se encarga de obtener el promedio de puntos de suerte ganado en cada partida
	 */
	private void getPromedyLuck() {
		for (Departure departure : departures) {
			System.out.println("El promedio de puntos de suerte ganados por partida es de: " + departure.getPromedyLuckpoints() + " puntos");
		}
	}

	/**
	 * El metodo se encarga de obtener el ganador por cada partida
	 */
	private void getWinByDeparture() {
		for (Departure departure : departures) {
			System.out.println("El equipo ganador de la partida " + departure.getName() +  " es el equipo: " + departure.getWinOfDeparture().getName() + " con un total de: " + 
					departure.getWinOfDeparture().getPoints() + "  Puntos");
		}
	}

}
