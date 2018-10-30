package com.tiny.url.assignment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.tiny.url.constants.ApplicationConstants;
import com.tiny.url.constants.QueryConstants;
import com.tiny.url.domain.UrlShortner;
import com.tiny.url.utils.Utils;

/**
 * This class is used to create tiny url by giving long url through console input and get the long url by entering short url console
 * @author Krishna Sure
 * @Version 1.0
 *
 */
public class URLShortnerMain {

	/**
	 * This is the main class where execution starts here
	 * @param argv command line arguments
	 */
	public static void main(String[] argv) {
		LocalDateTime timeNow = LocalDateTime.now();
		UrlShortner urlShortner = new UrlShortner();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);		
		
		try {
			createTable();
			
			System.out.println("Please Enter Long URL to create Tiny URL");
			String longUrl = input.nextLine();
			urlShortner.setLongUrl(longUrl);
			urlShortner.setShortUrl(ApplicationConstants.HOME_URL+Utils.generateKey(ApplicationConstants.ALPHABET, ApplicationConstants.BASE));
			urlShortner.setCreatedTime(timeNow);
			urlShortner.setExpiredTime(timeNow.plusDays(1));
			
			boolean isSavedURL = saveTinyURL(urlShortner);
			if(isSavedURL) {
				System.out.println("Please Enter Tiny URL to get Long URL");
				String shortUrl = input.nextLine();
				
				getLongURL(shortUrl);	
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * This method is used for creating the table called TINY_URL to store UrlShortner Entity
	 * @throws SQLException
	 */
	private static void createTable() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String createTableSQL = null;

		try {
			dbConnection = getDBConnection();
			DatabaseMetaData meta = dbConnection.getMetaData();
		    ResultSet res = meta.getTables(null, null, ApplicationConstants.TINY_URL, new String[] {ApplicationConstants.TABLE});
		    
		    if(!res.next()) {
		    	//If there is table exist i can read res.getString method -> TABLE_CAT,TABLE_SCHEM,TABLE_NAME,TABLE_TYPE,REMARKS
		    	createTableSQL = QueryConstants.CREATE_TABLE_TINY_URL;
		    	preparedStatement = dbConnection.prepareStatement(createTableSQL);
		    	preparedStatement.executeUpdate();
		    	System.out.println("TINY_URL table Created Successfully.");
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("Failed to Create TINY_URL table.");
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}
	
	/**
	 * This method is used for saving the Tiny url after creating it by using base-62 approach
	 * @param urlShortner It is domain to save data into database
	 * @return
	 * @throws SQLException
	 */
	private static boolean saveTinyURL(UrlShortner urlShortner) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		boolean isURLExists = false;
		boolean isSaved = false;

		String insertDataTableSQL = QueryConstants.SAVE_TINY_URL;

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertDataTableSQL);			
			preparedStatement.setString(1, urlShortner.getLongUrl());
			preparedStatement.setString(2, urlShortner.getShortUrl());
			preparedStatement.setString(3, urlShortner.getCreatedTime().toString());
			preparedStatement.setString(4, urlShortner.getExpiredTime().toString());
			
			// execute create SQL stetement
			isURLExists = isUrlExists(urlShortner.getLongUrl());
			if(!isURLExists) {
				preparedStatement.executeUpdate();
				isSaved = true;
				System.out.println("Successfully saved tinyurl Entity in TINY_URL table = " + urlShortner.getShortUrl());
			}else {
				System.err.println("\nDuplicate URL, URL already exist in Data base.\n");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return isSaved;
	}
	
	/**
	 * This method is used for the getting the long URL by passing short url
	 * @param shortURL It is short url of type String
	 * @throws SQLException
	 */
	private static void getLongURL(String shortURL) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String longURL = null;
		String expiredDate = null;

		String selectSQL = QueryConstants.LOAD_TINY_URL;
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, shortURL);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				longURL = rs.getString(ApplicationConstants.LONG_URL);
				expiredDate = rs.getString(ApplicationConstants.EXPIRED_TIME);
			}
			
			if(null != longURL && null != expiredDate) {
				System.out.println("Long URL : " + longURL + "\nURL Expiry Date : " + expiredDate);
			}else {
				System.err.println("\nShort URL is not exist or expired.");
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}			

	}
	
	/**
	 * This method is created for checking the long url is exist in the databse before saving it.
	 * @param longUrl It is long url of type String
	 * @return It returns the boolean value if url exists true else false
	 * @throws SQLException
	 */
	private static boolean isUrlExists(String longUrl) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		boolean isUrlExist = false;
		int id = 0;

		String selectSQL = QueryConstants.IS_URL_EXISTS;
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, longUrl);
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(ApplicationConstants.ID);
			}
			
			if(id > 0) {
				isUrlExist = true;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		return isUrlExist;
	}

	/**
	 * This method is created for getting the database connection
	 * @return It returns the connection object
	 */
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(ApplicationConstants.DB_MYSQL_DRIVER);
			dbConnection = DriverManager.getConnection(ApplicationConstants.DB_MYSQL_CONNECTION, ApplicationConstants.DB_MYSQL_USER, ApplicationConstants.DB_MYSQL_PASSWORD);	
			if (null == dbConnection) {
				System.err.println("Failed to make connection!");
			} 
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return dbConnection;
	}

}
