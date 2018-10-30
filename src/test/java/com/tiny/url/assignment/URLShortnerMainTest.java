package com.tiny.url.assignment;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

public class URLShortnerMainTest {
	
	@Test
	public void testGetDBConnection() {
		Connection dbConnection = URLShortnerMain.getDBConnection();
		assertNotNull(dbConnection);
	}

}
