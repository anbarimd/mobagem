package net.h2.web.mob.file.page.enums;

public enum MainPageType {

	SLIDER(0), HOTGAME(1), SERVICE(2), RESULT(3);
	private final int value;

	private MainPageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
