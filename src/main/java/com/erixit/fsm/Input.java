package com.erixit.fsm;

public class Input {
	private String input;
	private int current;

	public Input(String input) {
		this.input = input;
	}

	char read() {
		return input.charAt(current++);
	}
}
