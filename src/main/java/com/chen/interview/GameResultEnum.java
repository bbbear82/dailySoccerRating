package com.chen.interview;

public enum GameResultEnum {
	WIN(3),
	TIE(1),
	LOSS(0);

	private final int score;

	GameResultEnum(int score) {
		this.score = score;
	}

	public int value() {
		return score;
	}

}