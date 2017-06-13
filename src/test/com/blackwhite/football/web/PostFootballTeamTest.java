package test.com.blackwhite.football.service.web;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.provider.FootballTeamJSONHandler;

import test.com.blackwhite.football.testdata.TestConstants;


/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * JUnit tests for POST methods over HTTP
 *
 */
public class PostFootballTeamTest {
	
	@Test
	public void testAddTeamDetails() {

		HttpURLConnection connection = null;		
		StringBuffer urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20A%22}");
		
	    try
	    {
	    	URL url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    int responseCode = connection.getResponseCode();
		    assertEquals(200, responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
			
			urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/query/getTeamDetails?name=Team%20A");
			url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(12));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

			responseCode = connection.getResponseCode();
			assertEquals(200, responseCode);
		//	    System.out.println(responseCode);
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			FootballTeam team = FootballTeamJSONHandler.convertFromString(response.toString());	
			FootballTeam reference = TestConstants.getTestTeam();
			assertEquals(reference.getName(), team.getName());

		    
		 } catch (Exception e) {
		    e.printStackTrace();
		 } finally {
			 if (connection != null) {
				 connection.disconnect();
			 }
		 }	  
	}
	
}
