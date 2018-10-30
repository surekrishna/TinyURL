package com.tiny.url.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.tiny.url.utils.Utils;

public class TinyURLNegativeTest {	
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";		
	private static final String ALPHABET_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String result = "";	

	@Test
	public void testRandomKeyNotEqual() {	
		result = Utils.generateKey(ALPHABET, ALPHABET.length());
		assertNotEquals("!ksw$rw8", result);
	}

	@Test
	public void testRandomKeyNull() {
		result = Utils.generateKey(ALPHABET_ONE, ALPHABET_ONE.length());
		assertEquals("", result);
	}
	
	@Test
	public void testRandomKeyLengthisNotEight() {	
		int result = Utils.generateKey(ALPHABET_ONE, ALPHABET_ONE.length()).length(); 
		assertFalse(result == 8);
	}
}
