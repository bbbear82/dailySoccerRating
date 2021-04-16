package com.chen.interview;

/**
class that hold soccer team , name, score. etc..more..
*/
public class SoccerTeam implements Comparable<SoccerTeam> {
	private String teamName;
	private int totalScore;

	public SoccerTeam(String teamName) {
		this.teamName = teamName;
		this.totalScore = 0;
	}

	public SoccerTeam(String teamName, int totalScore) {
		this.teamName = teamName;
		this.totalScore = totalScore;
	}

	public SoccerTeam(SoccerTeam anotherTeam) {
		this.teamName = anotherTeam.getTeamName();
		this.totalScore = anotherTeam.getTotalScore();
	}

	public String getTeamName() {
		return this.teamName;
	}

	public int getTotalScore() {
		return this.totalScore;
	}

	public void updateScore(GameResultEnum gameResultEnum) {
		this.totalScore += gameResultEnum.value();
	}

	public void updateScore(int score) {
		this.totalScore += score;
	}

	@Override
    public int compareTo(SoccerTeam otherTeam) {
        return Integer.compare(getTotalScore(), otherTeam.getTotalScore());
    }

    public String toString() {
    	if(this.totalScore == 1) {
    		return this.teamName + ", " + this.totalScore + " pt";
    	} else {
    		return this.teamName + ", " + this.totalScore + " pts";
    	}
    }

}