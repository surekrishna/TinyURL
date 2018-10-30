package com.tiny.url.utils;

import java.util.function.Supplier;

/**
 * This is Utility class to generate random key using base - 62 approach
 * @author Krishna Sure
 * @Version 1.0
 */
public class Utils {
	
	/**
	 * This method creates the Random key every time
	 * @param ALPHABET It consists of alphabets small/caps case and numbers
	 * @param BASE It is length of the ALPHABET = 62
	 * @return
	 */
	public static String generateKey(String ALPHABET, int BASE) {
		if(BASE == 62) {
			Supplier<String> randomKey = () -> {
				String id = "";
				for (int i = 1; i <= 8; i++) {
					id = id + ALPHABET.charAt((int) (Math.random() * BASE));
				}
				return id;
			};
			return randomKey.get();
		}else {
			return "";
		}
		
	}

}
