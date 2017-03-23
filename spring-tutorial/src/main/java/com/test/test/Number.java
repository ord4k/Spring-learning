package com.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class Number {
	
	private int randomNumber;

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public int getRandomNumber() {
		return randomNumber;
	}



}
