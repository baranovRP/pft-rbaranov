package com.baranov.pft.tests;

import java.util.Random;

public class DataGenerator {

    public static String generateRandomString() {
	Random rnd = new Random();
	String rString;
	if (rnd.nextInt(3) == 0) {
	    rString = "";
	} else {
	    /*
	     * time to time is generated white spaces at the begin or at the end
	     * of random string. Web apps cuts this white spaces and it's the
	     * reason of error during comparison
	     */
	    rString = generateRandomSequence().trim();
	}
	return rString;
    }

    private static String generateRandomSequence() {
	Random rnd = new Random();
	// according to ASCII 32 is space and 126 is ~
	int max = 90;
	int min = 65;
	int length = rnd.nextInt(20); // specify desire length of string
	char buf[] = new char[length];
	for (int i = 0; i < buf.length; i++) {
	    char symbol = (char) (rnd.nextInt((max + 1) - min) + min);
	    // symbols ' and \ is lead to error during write to DB, symbol
	    // doesn't write into group name
	    if (isValidCharacter(symbol)) {
		buf[i] = symbol;
	    }
	}
	return new String(buf);
    }

    private static boolean isValidCharacter(char symbol) {
	char[] invalidChars = { '\'', '\\', '<', '>', '_', '`' };
	boolean isValid = true;
	for (char c : invalidChars) {
	    if (c == symbol) {
		isValid = false;
	    }
	}
	return isValid;
    }
}
