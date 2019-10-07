package models.entities;

import java.util.ArrayList;

public class Round {
	private ArrayList<Player> playerList;
	private String name;
	private int experiencePoints;

	public Round(ArrayList<Player> playerList, String name) {
		this.playerList = playerList;
		this.name = name;
		getSingleWiner();
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	public String getName() {
		return name;
	}

	public Player getSingleWiner() {
		Player winer = null;
		for (Player player : playerList) {
			if (winer == null) {
				winer = player;
			}else {
				if (player.getPoints() > winer.getPoints()) {
					winer = player;
				}
			}
		}
		addExperiencepoints(2);
		winer.addExperience(2);
		return winer;
	}

	private void addExperiencepoints(int i) {
		experiencePoints += i;
	}

	public Team getTeamWin() {
		Team team1 = new Team("Equipo 1");
		Team team2 = new Team("Equipo 2");
		for (Player player : playerList) {
			if (player.getTeam().getName().equals("Equipo 1")) {
				team1.setPoints(player.getPoints());
			}else {
				team2.setPoints(player.getPoints());
			}
		}
		return team1.getPoints() > team2.getPoints() ? team1 : team2;
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public float getLuckPoints() {
		float count = 0;
		for (Player player : playerList) {
			count += player.getLuck();
		}
		return count;
	}
	
	public void getAddShoot() {
		Player p = null;
		for (Player player : playerList) {
			if (p == null) {
				p = player;
			}else {
				if (player.getLuck() > p.getLuck()) {
					p = player;
				}
			}
		}
		p.genrateShoot();
	}

}
