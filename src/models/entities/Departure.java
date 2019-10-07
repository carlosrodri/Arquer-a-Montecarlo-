package models.entities;

import java.util.ArrayList;

public class Departure {
	private ArrayList<Round> roundList;
	private String name;

	public Departure(ArrayList<Round> roundList, String name) {
		this.roundList = roundList;
		this.name = name;
	}
	
	/**
	 * obtiene el ganador por partida
	 * @return el ganador de la partida
	 */

	public Team getWinOfDeparture() {
		int t1 = 0; int t2 = 0;
		for (Round round : roundList) {
			if (round.getTeamWin().getName().equals("Equipo 1")) {
				t1 += round.getTeamWin().getPoints();
			}else {
				t2 += round.getTeamWin().getPoints();
			}
		}
		if (t1 > t2) {
			roundList.get(0).getTeamWin().setPoints(t1);
			return roundList.get(0).getTeamWin();
		}else {
			roundList.get(0).getTeamWin().setPoints(t2);
			return roundList.get(0).getTeamWin();
		}
	}

	public ArrayList<Round> getRoundList() {
		return roundList;
	}

	public String getName() {
		return name;
	}

	public float getPromedyExperiencepoints() {
		int points = 0;
		for (Round round : roundList) {
			points += round.getExperiencePoints();
		}
		return points/roundList.size();
	}

	public float getPromedyLuckpoints() {
		int points = 0;
		for (Round round : roundList) {
			points += round.getLuckPoints();
		}
		return points/roundList.size();
	}

	public Player getPlayerWithMoreLuck() {
		Player p = null;
		for (Round round : roundList) {
			for (Player player : round.getPlayerList()) {
				if (p == null) {
					p = player;
				}else {
					if (player.getLuck() > p.getLuck()) {
						p = player;
					}
				}

			}
		}
		return p;
	}

	public Team getWinerTeam() {
		Team winer = null;
		for (Round round : roundList) {
			if (winer == null) {
				winer = round.getTeamWin();
			}else {
				if (round.getTeamWin().getPoints() > winer.getPoints()) {
					winer = round.getTeamWin();
				}
			}
		}
		return winer;
	}

	public String getGenreVictory() {
		int m = 0; int w = 0;
		for (Round round : roundList) {
			if (round.getSingleWiner().getGenre().equals(Genre.MAN)) {
				m ++;
			}else {
				w++;
			}
		}
		if (m > w) {
			return "man";
		}else {
			return "woman";
		}
	}

}
