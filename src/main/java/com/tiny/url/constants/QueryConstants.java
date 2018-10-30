package com.tiny.url.constants;

/**
 * This class is created for QUERY constants
 * @author Krishna Sure
 * @Version 1.0
 */
public class QueryConstants {

	private QueryConstants() {}
	
	public static final String CREATE_TABLE_TINY_URL = "CREATE TABLE TINY_URL (ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, LONG_URL VARCHAR(255), SHORT_URL VARCHAR(255), CREATED_TIME DATETIME, EXPIRED_TIME DATETIME)";
	
	public static final String SAVE_TINY_URL = "INSERT INTO TINY_URL (LONG_URL, SHORT_URL, CREATED_TIME, EXPIRED_TIME) VALUES(?,?,?,?)";
	
	public static final String LOAD_TINY_URL = "SELECT LONG_URL, EXPIRED_TIME FROM TINY_URL WHERE SHORT_URL = ?";
	
	public static final String IS_URL_EXISTS = "SELECT ID FROM TINY_URL WHERE LONG_URL = ?";
}
