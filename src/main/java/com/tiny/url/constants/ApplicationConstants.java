package com.tiny.url.constants;

/**
 * This class is created for Application constants
 * @author Krishna Sure
 *
 */
public class ApplicationConstants {
	
	private ApplicationConstants() {}
	
	//Data base connection properties
	public static final String DB_MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_MYSQL_CONNECTION = "jdbc:mysql://localhost:3306/URLSHORTNER?useSSL=false";
	public static final String DB_MYSQL_USER = "root";
	public static final String DB_MYSQL_PASSWORD = "root";

	//Random key using BASE - 62
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int BASE = ALPHABET.length();
	
	public static final String HOME_URL = "https://www.google.co.in/";
	
	//Table constants
	public static final String TINY_URL = "TINY_URL";
	public static final String TABLE = "TABLE";
	public static final String ID = "ID";
	public static final String LONG_URL = "LONG_URL";
	public static final String EXPIRED_TIME = "EXPIRED_TIME";
}
