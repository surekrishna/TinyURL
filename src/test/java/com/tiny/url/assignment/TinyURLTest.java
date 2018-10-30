package com.tiny.url.assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tiny.url.utils.Utils;

public class TinyURLTest {
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int BASE = ALPHABET.length();

	@Test
	public void testRandomKeyLengthisEight() {		
		assertTrue(Utils.generateKey(ALPHABET, BASE).length() == 8);
	}
}
